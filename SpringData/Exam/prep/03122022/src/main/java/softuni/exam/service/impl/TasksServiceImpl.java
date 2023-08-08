package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.TaskDto;
import softuni.exam.models.dto.TaskImportDto;
import softuni.exam.models.dto.TaskWrapperDto;
import softuni.exam.models.entity.Car;
import softuni.exam.models.entity.Mechanic;
import softuni.exam.models.entity.Part;
import softuni.exam.models.entity.Task;
import softuni.exam.models.entity.enums.CarType;
import softuni.exam.repository.CarsRepository;
import softuni.exam.repository.MechanicsRepository;
import softuni.exam.repository.PartsRepository;
import softuni.exam.repository.TasksRepository;
import softuni.exam.service.TasksService;
import softuni.exam.util.ValidationUtils;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static softuni.exam.models.Constants.*;

@Service
public class TasksServiceImpl implements TasksService {

    private static String TASKS_FILE_PATH = "src/main/resources/files/xml/tasks.xml";
    private TasksRepository tasksRepository;
    private MechanicsRepository mechanicsRepository;
    private CarsRepository carsRepository;
    private PartsRepository partsRepository;
    private ModelMapper modelMapper;
    private XmlParser xmlParser;
    private ValidationUtils validationUtils;


    @Autowired
    public TasksServiceImpl(TasksRepository tasksRepository, MechanicsRepository mechanicsRepository, CarsRepository carsRepository, PartsRepository partsRepository, ModelMapper modelMapper, XmlParser xmlParser, ValidationUtils validationUtils) {
        this.tasksRepository = tasksRepository;
        this.mechanicsRepository = mechanicsRepository;
        this.carsRepository = carsRepository;
        this.partsRepository = partsRepository;
        this.modelMapper = modelMapper;
        this.xmlParser = xmlParser;
        this.validationUtils = validationUtils;
    }

    @Override
    public boolean areImported() {
        return this.tasksRepository.count() > 0;
    }

    @Override
    public String readTasksFileContent() throws IOException {
        return Files.readString(Path.of(TASKS_FILE_PATH));
    }

    @Override
    public String importTasks() throws IOException, JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        List<TaskImportDto> tasks = this.xmlParser.fromFile(Path.of(TASKS_FILE_PATH).toFile(), TaskWrapperDto.class).getTaskImportDto();

        for (TaskImportDto task : tasks) {
            stringBuilder.append(System.lineSeparator());

            Optional<Mechanic> mechanic = this.mechanicsRepository.findFirstByFirstName(task.getMechanic().getFirstName());
            Optional<Car> car = this.carsRepository.findById(task.getCar().getId());
            Optional<Part> part = this.partsRepository.findById(task.getPart().getId());

            if (mechanic.isEmpty() || !validationUtils.isValid(task)) {
                stringBuilder.append(String.format(INVALID_FORMAT,TASK));
                continue;
            }


            Task taskToSave = this.modelMapper.map(task, Task.class);
            taskToSave.setMechanic(mechanic.get());
            taskToSave.setCar(car.get());
            taskToSave.setPart(part.get());

            this.tasksRepository.save(taskToSave);
            //Successfully imported task 240.04
            stringBuilder.append(String.format(
                    SUCCESSFUL_FORMAT,
                    TASK,
                    task.getPrice().setScale(2), "").trim());


        }
        return stringBuilder.toString().trim();
    }

    @Override
    public String getCoupeCarTasksOrderByPrice() {
        return this.tasksRepository.findAllByCarCarTypeOrderByPriceDesc(CarType.coupe)
                .stream()
                .map(task -> this.modelMapper.map(task, TaskDto.class))
                .map(TaskDto::toString)
                .collect(Collectors.joining())
                .trim();

    }
}
