package softuni.exam.models.dto;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CityImportDto {
    //    "cityName": "g",
    //    "description": "ut dolor morbi vel lectus in quam fringilla rhoncus mauris",
    //    "population": 325899,
    //    "country": 1
    //•	city name – a char sequence (between 2 to 60 inclusive). Cannot be null.
    //•	description – accepts very long char sequence (min 2 symbols).
    //•	population – accepts number values that are more than or equal to 500. Cannot be null.

    @NotNull
    @Size(min = 2, max = 60)
    private String cityName;
    @Size(min = 2)
    private String description;
    @NotNull
    @DecimalMin(value = "500")
    private Integer population;
    private Long country;

    public CityImportDto() {
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }
}
