package softuni.exam.models.dto;

import java.time.LocalTime;

public class ForecastExportDto {
    //•	Extract from the database,
    // city name,
    // min temperature (to second digit after decimal point),
    // max temperature (to second digit after decimal point)
    // sunrise and
    // sunset of the forecast.
    private Double minTemperature;
    private Double maxTemperature;
    private LocalTime sunrise;
    private LocalTime sunset;
    private ExportCityDTO city;

    public ForecastExportDto() {
    }

    public Double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public LocalTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalTime sunrise) {
        this.sunrise = sunrise;
    }

    public LocalTime getSunset() {
        return sunset;
    }

    public void setSunset(LocalTime sunset) {
        this.sunset = sunset;
    }

    public ExportCityDTO getCity() {
        return city;
    }

    public void setCity(ExportCityDTO city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format("City: %s:\n" +
                "-min temperature: %.2f\n" +
                "--max temperature: %.2f\n" +
                "---sunrise: %s\n" +
                "----sunset: %s\n",
                this.getCity().getCityName(),
                this.minTemperature,
                this.maxTemperature,
                this.sunrise,
                this.sunset);

    }
}
