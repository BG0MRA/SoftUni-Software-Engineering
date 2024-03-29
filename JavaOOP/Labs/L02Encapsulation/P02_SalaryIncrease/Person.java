package L02Encapsulation.P02_SalaryIncrease;

public class Person {
    private String firstName;
    private String lastName;
    private int age;

    private double salary;

    public Person(String firstName, String lastName, int age, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void increaseSalary(double bonus) {

        double bonusReduced = age < 30 ? bonus / 2 : bonus;

        double factor = 1.00 + bonusReduced / 100;

        salary *= factor;
    }

    @Override
    public String toString() {
        return String.format("%s %s is %d %f", firstName, lastName, age, salary);
    }


}
