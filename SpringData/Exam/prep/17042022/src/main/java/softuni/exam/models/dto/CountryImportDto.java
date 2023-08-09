package softuni.exam.models.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CountryImportDto {
    //    "countryName": "Philippines",
    //    "currency": "Peso"
    //•	country name – (between 2 and 60 inclusive). Cannot be null.
    //•	currency – (between 2 and 20 inclusive). Cannot be null.
    @NotNull
    @Size(min = 2, max = 60)
    private String countryName;
    @NotNull
    @Size(min = 2, max = 60)
    private String currency;

    public CountryImportDto() {
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
