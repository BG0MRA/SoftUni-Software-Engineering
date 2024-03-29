package softuni.exam.models.dto;

import softuni.exam.models.entity.enums.CarType;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarImportDto {
//•	car type – the enumeration, one of the following – SUV, coupe, sport
    //•	car make – accepts char sequence (between 2 to 30 inclusive).
    //•	car model – accepts char sequence (between 2 to 30 inclusive).
    //•	year – accepts a positive number.
    //•	plate number – accepts char sequence (between 2 to 30 inclusive). The values are unique in the database.
    //•	kilometers – accepts a positive number.
    //•	engine – accepts number values that are more than or equal to 1.00.

    @XmlElement
    @NotNull
    @Size(min = 2, max = 30)
    private String carMake;
    @XmlElement
    @NotNull
    @Size(min = 2, max = 30)
    private String carModel;
    @XmlElement
    @NotNull
    @Positive
    private Integer year;
    @XmlElement
    @NotNull
    @Size(min = 2, max = 30)
    private String plateNumber;
    @XmlElement
    @NotNull
    @Positive
    private Integer kilometers;
    @XmlElement
    @NotNull
    @DecimalMin(value = "1.00")
    private Double engine;
    @XmlElement
    @NotNull
    private CarType carType;

    public CarImportDto() {
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Integer getKilometers() {
        return kilometers;
    }

    public void setKilometers(Integer kilometers) {
        this.kilometers = kilometers;
    }

    public Double getEngine() {
        return engine;
    }

    public void setEngine(Double engine) {
        this.engine = engine;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }
}
