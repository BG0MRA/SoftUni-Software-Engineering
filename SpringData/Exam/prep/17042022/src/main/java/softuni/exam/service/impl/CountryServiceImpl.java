package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.CountryImportDto;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;
import softuni.exam.util.ValidationUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static softuni.exam.models.Constants.*;

@Service
public class CountryServiceImpl implements CountryService {

    private static String PATH = "src/main/resources/files/json/countries.json";
    private CountryRepository countryRepository;
    private ModelMapper modelMapper;
    private Gson gson;
    private ValidationUtils validationUtils;

    public CountryServiceImpl(CountryRepository countryRepository, ModelMapper modelMapper, Gson gson, ValidationUtils validationUtils) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validationUtils = validationUtils;
    }

    @Override
    public boolean areImported() {
        return this.countryRepository.count() > 0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return Files.readString(Path.of(PATH));
    }

    @Override
    public String importCountries() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        CountryImportDto[] countries = this.gson.fromJson(readCountriesFromFile(), CountryImportDto[].class);

        for (CountryImportDto country : countries) {
            stringBuilder.append(System.lineSeparator());

            if (this.countryRepository.findFirstByCountryName(country.getCountryName()).isPresent() ||
            !this.validationUtils.isValid(country)) {
                stringBuilder.append(String.format(INVALID_IMPORT, COUNTRY));
                continue;
            }

            this.countryRepository.save(this.modelMapper.map(country, Country.class));

            //Successfully imported country Philippines - Peso
            stringBuilder.append(String.format(
                    SUCCESSFUL_IMPORT,
                    COUNTRY,
                    country.getCountryName(),
                    country.getCurrency()));
        }

        return stringBuilder.toString().trim();
    }
}
