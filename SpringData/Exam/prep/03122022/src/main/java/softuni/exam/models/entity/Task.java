package softuni.exam.models.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {
    //•	price – accepts a very big positive number.
    //•	date – a date and time of registering the task in the "yyyy-MM-dd HH:mm:ss" format.
    //•	Constraint: The task table has a relation with the parts table.
    //•	Constraint: The task table has a relation with the mechanics table.
    //•	Constraint: The task table has a relation with the cars table.
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "parts_id")
    private Part part;

    @ManyToOne
    @JoinColumn(name = "mechanic_id")
    private Mechanic mechanic;

    @ManyToOne
    @JoinColumn(name = "cars_id")
    private Car car;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
