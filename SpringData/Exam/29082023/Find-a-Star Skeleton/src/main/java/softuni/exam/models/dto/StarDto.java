package softuni.exam.models.dto;

import softuni.exam.models.entity.Constellation;

public class StarDto {
    //•	Extract from the database,
    // the star name, distance in light years (to second digit after decimal point),
    // description and the constellation name.

    private String name;
    private double lightYears;
    private String description;
    private Constellation constellation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLightYears() {
        return lightYears;
    }

    public void setLightYears(double lightYears) {
        this.lightYears = lightYears;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Constellation getConstellation() {
        return constellation;
    }

    public void setConstellation(Constellation constellation) {
        this.constellation = constellation;
    }

    @Override
    public String toString() {
        //"Star: {starName}
        //"   *Distance: {lightYears} light years
        //"   **Description: {description}
        //"   ***Constellation: {constellationName}
        String FORMAT = "Star: %s\n" +
                "   *Distance: %0.f light years\n" +
                "   **Description: %s\n" +
                "   ***Constellation: %s\n";

        return String.format(FORMAT,
                this.name,
                this.lightYears,
                this.description,
                this.constellation.getName()
                );
    }
}
