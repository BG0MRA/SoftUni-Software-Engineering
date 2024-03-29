package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "tasks")
@XmlAccessorType(XmlAccessType.FIELD)
public class TaskWrapperDto {
    @XmlElement(name = "task")
    private List<TaskImportDto> taskImportDto;

    public TaskWrapperDto() {
    }

    public List<TaskImportDto> getTaskImportDto() {
        return taskImportDto;
    }

    public void setTaskImportDto(List<TaskImportDto> taskImportDto) {
        this.taskImportDto = taskImportDto;
    }
}
