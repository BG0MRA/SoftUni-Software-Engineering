package softuni.exam.models.entity;
import javax.persistence.*;

@Entity
@Table(name = "cities")
public class City extends BaseEntity {
    //•	city name – a char sequence (between 2 to 60 inclusive). The values are unique in the database. Cannot be null.
    //•	description – accepts very long char sequence (min 2 symbols).
    //•	population – accepts number values that are more than or equal to 500. Cannot be null.
    //•	Country
    @Column(name = "city_name", unique = true, nullable = false)
    private String cityName;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private Integer population;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
