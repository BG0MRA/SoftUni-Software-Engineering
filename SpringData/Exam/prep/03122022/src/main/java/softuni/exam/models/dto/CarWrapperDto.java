package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarWrapperDto {

    @XmlElement(name = "car")
    private List<CarImportDto> carImportDto;

    public CarWrapperDto() {
    }

    public List<CarImportDto> getCarImportDto() {
        return carImportDto;
    }

    public void setCarImportDto(List<CarImportDto> carImportDto) {
        this.carImportDto = carImportDto;
    }
}
