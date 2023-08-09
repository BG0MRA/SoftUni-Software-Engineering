package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.ForecastExportDto;
import softuni.exam.models.dto.ForecastImportDto;
import softuni.exam.models.dto.ForecastWrapperDto;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Forecast;
import softuni.exam.models.entity.enums.DayOfWeek;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.ForecastService;
import softuni.exam.util.ValidationUtils;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static softuni.exam.models.Constants.*;

@Service
public class ForecastServiceImpl implements ForecastService {

    private static String PATH = "src/main/resources/files/xml/forecasts.xml";

    private ForecastRepository forecastRepository;
    private CityRepository cityRepository;
    private XmlParser xmlParser;
    private ModelMapper modelMapper;
    private ValidationUtils validationUtils;


    public ForecastServiceImpl(ForecastRepository forecastRepository, CityRepository cityRepository, XmlParser xmlParser, ModelMapper modelMapper, ValidationUtils validationUtils) {
        this.forecastRepository = forecastRepository;
        this.cityRepository = cityRepository;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtils = validationUtils;
    }

    @Override
    public boolean areImported() {
        return this.forecastRepository.count() > 0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files.readString(Path.of(PATH));
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        StringBuilder stringBuilder = new StringBuilder();

        List<ForecastImportDto> forecasts = this.xmlParser.fromFile(Path.of(PATH).toFile(), ForecastWrapperDto.class).getForecasts();

        for (ForecastImportDto forecast : forecasts) {
            stringBuilder.append(System.lineSeparator());

            DayOfWeek dayOfWeek;
            List<City> currentCity;


            if (validationUtils.isValid(forecast)) {
                currentCity = cityRepository.findAllById(forecast.getCity());
                dayOfWeek = forecast.getDayOfWeek();
            } else {
                stringBuilder.append(String.format(INVALID_IMPORT, FORECAST));
                continue;

            }
            if (currentCity.isEmpty()) {
                stringBuilder.append(String.format(INVALID_IMPORT, FORECAST));
                continue;
            }

            Optional<Forecast> firstByCityAndAndDayOfWeek = this.forecastRepository.findByCityAndDayOfWeek(currentCity.get(0), dayOfWeek);

            if (firstByCityAndAndDayOfWeek.isPresent() || !validationUtils.isValid(forecast)) {
                stringBuilder.append(String.format(INVALID_IMPORT, FORECAST));
                continue;
            }

            Forecast entity = this.modelMapper.map(forecast, Forecast.class);
            entity.setCity(currentCity.get(0));

            this.forecastRepository.save(entity);

            //Successfully import forecast FRIDAY - 25.00
            stringBuilder.append(String.format(
                    SUCCESSFUL_IMPORT,
                    FORECAST,
                    forecast.getDayOfWeek(),
                    forecast.getMaxTemperature()));

        }

        return stringBuilder.toString().trim();
    }

    @Override
    public String exportForecasts() {
        List<Forecast> forecasts = this.forecastRepository.findAllByDayOfWeekAndCityPopulationLessThanOrderByMaxTemperatureDescIdAsc(DayOfWeek.SUNDAY, 150000);

        return forecasts.stream()
                .map(forecast -> this.modelMapper.map(forecast, ForecastExportDto.class))
                .map(ForecastExportDto::toString)
                .collect(Collectors.joining())
                .trim();

    }
}
