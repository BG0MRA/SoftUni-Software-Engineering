package softuni.exam.models.dto;

import javax.persistence.Column;
import javax.validation.constraints.*;

public class PartImportDto {
    //    "partName": "Alternator",
    //    "price": 320.13,
    //    "quantity": 40
    //•	part name – accepts char sequence (between 2 to 19 inclusive).
    //•	price – Must be between 10 and 2000 (both numbers are INCLUSIVE).
    //•	quantity – accepts a positive number.
    @NotNull
    @Size(min = 2, max = 19)
    private String partName;
    @NotNull
    @DecimalMin(value = "10.00")
    @DecimalMax(value = "2000.00")
    private Double price;
    @NotNull
    @Positive
    private Integer quantity;

    public PartImportDto() {
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
