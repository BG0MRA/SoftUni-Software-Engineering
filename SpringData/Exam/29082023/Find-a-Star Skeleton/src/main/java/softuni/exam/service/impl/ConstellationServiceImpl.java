package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ConstellationImportDto;
import softuni.exam.models.entity.Constellation;
import softuni.exam.repository.ConstellationRepository;
import softuni.exam.service.ConstellationService;
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
public class ConstellationServiceImpl implements ConstellationService {
    private static String FILE_PATH = "src/main/resources/files/json/constellations.json";

    private ConstellationRepository constellationRepository;
    private ModelMapper modelMapper;
    private Gson gson;
    private ValidationUtils validationUtils;

    @Autowired
    public ConstellationServiceImpl(ConstellationRepository constellationRepository, ModelMapper modelMapper, Gson gson, ValidationUtils validationUtils) {
        this.constellationRepository = constellationRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtils = validationUtils;
    }

    @Override
    public boolean areImported() {
        return this.constellationRepository.count() > 0;
    }

    @Override
    public String readConstellationsFromFile() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importConstellations() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        List<ConstellationImportDto> constellations = Arrays.stream(this.gson.fromJson(readConstellationsFromFile(), ConstellationImportDto[].class))
                .collect(Collectors.toList());

        for (ConstellationImportDto constellation : constellations) {
            stringBuilder.append(System.lineSeparator());

            if (this.constellationRepository.findFirstByName(constellation.getName()).isPresent() ||
                    !this.validationUtils.isValid(constellation)) {
                stringBuilder.append(String.format(INVALID_FORMAT, CONSTELLATION));
                continue;
            }


            //Successfully imported constellation name - description.
            this.constellationRepository.save(this.modelMapper.map(constellation, Constellation.class));

            stringBuilder.append(String.format(SUCCESSFUL_FORMAT,
                    CONSTELLATION,
                    constellation.getName() + " -",
                    constellation.getDescription()));
        }


        return stringBuilder.toString().trim();
    }
}