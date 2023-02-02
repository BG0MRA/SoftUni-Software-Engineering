package Prep.JA_14122022.softUni;

import java.util.ArrayList;
import java.util.List;


import java.util.stream.Collectors;

public class SoftUni {
    private List<Student> data;
    private int capacity;

    public SoftUni(int capacity) {
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public int getCapacity() {
        return this.capacity;
    }

    public int getCount() {
        return this.data.size();
    }

    public String insert(Student student) {
        if (this.data.size() >= this.capacity) {
            return "The hall is full.";
        } else if (this.data.contains(student)) {
            return "Student is already in the hall.";
        } else {
            this.data.add(student);
            return "Added student " + student.getFirstName() + " " + student.getLastName() + ".";
        }
    }

    public String remove(Student student) {
        if (!this.data.contains(student)) {
            return "Student not found.";
        }

        boolean isRemoved = this.data.remove(student);
        if (isRemoved) {
            return "Removed student " + student.getFirstName() + " " + student.getLastName() + ".";
        }
        return "Operation Remove Failed!";
    }

    public Student getStudent(String firstName, String lastName) {
//        List<Student> students = this.data.stream()
//                .filter(s -> s.getFirstName().equals(firstName) && s.getLastName().equals(lastName))
//                .toList();
//        return students.get(0);
        for (Student student : this.data) {
            if (student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)) {
                return student;
            }
        }
        return null;
    }

    public String getStatistics() {
        return String.format("Hall size: %d", this.getCount())
                + String.format("\n%s", this.data.stream()
                .map(student -> student.toString())
                .collect(Collectors.joining(System.lineSeparator())));
    }
}
