package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ConstellationImportDto;
import softuni.exam.models.dto.StarDto;
import softuni.exam.models.dto.StarImportDto;
import softuni.exam.models.entity.Constellation;
import softuni.exam.models.entity.Star;
import softuni.exam.models.entity.StarType;
import softuni.exam.repository.StarRepository;
import softuni.exam.service.StarService;
import softuni.exam.util.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static softuni.exam.models.Constants.*;

// TODO: Implement all methods
@Service
public class StarServiceImpl implements StarService {
    private static String FILE_PATH = "src/main/resources/files/json/stars.json";

    private StarRepository starRepository;
    private ModelMapper modelMapper;
    private Gson gson;
    private ValidationUtils validationUtils;

    @Autowired
    public StarServiceImpl(StarRepository starRepository, ModelMapper modelMapper, Gson gson, ValidationUtils validationUtils) {
        this.starRepository = starRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtils = validationUtils;
    }

    @Override
    public boolean areImported() {
        return this.starRepository.count() > 0;
    }

    @Override
    public String readStarsFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importStars() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        List<StarImportDto> stars = Arrays.stream(this.gson.fromJson(readStarsFileContent(), StarImportDto[].class))
                .collect(Collectors.toList());

        for (StarImportDto star : stars) {
            stringBuilder.append(System.lineSeparator());

            if (this.starRepository.findFirstByName(star.getName()).isPresent() ||
                    !this.validationUtils.isValid(star)) {
                stringBuilder.append(String.format(INVALID_FORMAT, STAR));
                continue;
            }

            //Successfully imported constellation name - light year.
            this.starRepository.save(this.modelMapper.map(star, Star.class));

            stringBuilder.append(String.format(SUCCESSFUL_FORMAT2,
                    STAR,
                    star.getName() + " -",
                    star.getLightYears()));

        }

        return stringBuilder.toString().trim();
    }

    @Override
    public String exportStars() {
//        return this.starRepository.findAllByStarTypeOrderByLightYearsAsc(StarType.RED_GIANT)
//                .stream()
//                .filter(star -> star.getObservers().isEmpty())
//                .map( star -> this.modelMapper.map(star, StarDto.class))
//                .map(StarDto::toString)
//                .collect(Collectors.joining())
//                .trim();
        return this.starRepository.findAllByStarTypeAndObserversIsEmptyOrderByLightYearsAsc(StarType.RED_GIANT)
                .stream()
                .map(star -> this.modelMapper.map(star, StarDto.class))
                .map(StarDto::toString)
                .collect(Collectors.joining())
                .trim();


    }
}
