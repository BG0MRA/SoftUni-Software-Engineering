package softuni.exam.models.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlRootElement(name = "task")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskImportDto {
    //<task>
    //        <date>2021-01-28 06:43:21</date>
    //        <price>-150.04</price>
    //        <car>
    //            <id>88</id>
    //        </car>
    //        <mechanic>
    //            <firstName>Roshelle</firstName>
    //        </mechanic>
    //        <part>
    //            <id>7</id>
    //        </part>
    //</task>
    @XmlElement
    @NotNull
    @Positive
    private BigDecimal price;
    @XmlElement
    @NotNull
    private String date;
    @XmlElement(name = "car")
    @NotNull
    private CarBase car;
    @XmlElement(name = "mechanic")
    @NotNull
    private MechanicBase mechanic;
    @XmlElement(name = "part")
    @NotNull
    private PartBase part;

    public TaskImportDto() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CarBase getCar() {
        return car;
    }

    public void setCar(CarBase car) {
        this.car = car;
    }

    public MechanicBase getMechanic() {
        return mechanic;
    }

    public void setMechanic(MechanicBase mechanic) {
        this.mechanic = mechanic;
    }

    public PartBase getPart() {
        return part;
    }

    public void setPart(PartBase part) {
        this.part = part;
    }
}
