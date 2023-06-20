package main.java.entities;

import main.java.orm.annotations.Column;
import main.java.orm.annotations.Entity;
import main.java.orm.annotations.Id;

@Entity(name = "students")
public class Student {

    @Id
    private int id;

    @Column(name = "name")
    private String name;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
