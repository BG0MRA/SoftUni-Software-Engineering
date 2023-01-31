package E06DefiningClasses.P02CompanyRoster;

public class Employee {
    private String name;
    private double salary;
    private String position;
    private Department department;
    private String email;
    private int age;

    //The name, salary, position, and department are mandatory
    public Employee(String name, double salary, String position, Department department, String email, int age) {
        this.name = name;
        this.salary = salary;
        this.position = position;
        this.department = department;

        this.email = email;
        this.age = age;
    }

    public Employee(String name, double salary, String position, Department department) {
        this(name, salary, position, department, "n/a", -1);
    }

    public Employee(String name, double salary, String position, Department department, String email) {
        this(name, salary, position, department, email, -1);
    }

    public Employee(String name, double salary, String position, Department department, int age) {
        this(name, salary, position, department, "n/a", age);
    }

    @Override
    public String toString() {
        return String.format("%s %.2f %s %d", this.name, this.salary, this.email, this.age);
    }

//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }

    public double getSalary() {
        return salary;
    }
}
