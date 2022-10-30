package E06ObjectsAndClasses.P04Students;

public class Student {
    //полета -> характеристики -> име, фамилия, оценка
    private String name;
    private String lastName;
    private double grade;

    //конструктор -> създаваме обекти от класа
    public Student(String name, String lastName, double grade) {
        //нов обект
        this.name = name;
        this.lastName = lastName;
        this.grade = grade;
    }

    //getter

    public double getGrade() {
        return this.grade;
    }

    @Override
    public String toString() {
        //"{first name} {second name}: {grade}"
        return String.format("%s %s: %.2f", this.name, this.lastName, this.grade);
    }


}
