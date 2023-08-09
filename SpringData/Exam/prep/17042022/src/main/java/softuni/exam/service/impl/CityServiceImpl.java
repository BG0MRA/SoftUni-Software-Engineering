package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CityImportDto;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CityService;
import softuni.exam.util.ValidationUtils;

import static softuni.exam.models.Constants.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class CityServiceImpl implements CityService {
    private static String PATH = "src/main/resources/files/json/cities.json";
    private CityRepository cityRepository;
    private CountryRepository countryRepository;
    private ModelMapper modelMapper;
    private Gson gson;
    private ValidationUtils validationUtils;


    public CityServiceImpl(CityRepository cityRepository, CountryRepository countryRepository, ModelMapper modelMapper, Gson gson, ValidationUtils validationUtils) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtils = validationUtils;
    }


    @Override
    public boolean areImported() {
        return this.cityRepository.count() > 0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        return Files.readString(Path.of(PATH));
    }

    @Override
    public String importCities() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        CityImportDto[] cities = this.gson.fromJson(readCitiesFileContent(), CityImportDto[].class);

        for (CityImportDto city : cities) {
            stringBuilder.append(System.lineSeparator());

            Optional<Country> country = countryRepository.findById(city.getCountry());

            if (this.cityRepository.findFirstByCityName(city.getCityName()).isPresent() ||
                    !validationUtils.isValid(city) ||
                    country.isEmpty()) {
                stringBuilder.append(String.format(INVALID_IMPORT, CITY));
                continue;
            }

            City entity = this.modelMapper.map(city, City.class);
            entity.setCountry(country.get());

            this.cityRepository.save(entity);

            //Successfully imported city Gomian - 325899
            stringBuilder.append(String.format(
                    SUCCESSFUL_IMPORT,
                    CITY,
                    city.getCityName(),
                    city.getPopulation()));

        }

        return stringBuilder.toString().trim();
    }
}
