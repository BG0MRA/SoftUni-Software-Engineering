package L02Encapsulation.P04_FirstAndReserveTeam;

public class Person {
    private String firstName;
    private String lastName;
    private int age;

    private double salary;

    public Person(String firstName, String lastName, int age, double salary) {
        setFirstName(firstName);
        setLastName(lastName);
        setAge(age);
        setSalary(salary);
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

    public void setFirstName(String firstName) {
        ensureNameLength(firstName, "First");
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        ensureNameLength(lastName, "Last");
        this.lastName = lastName;
    }

    private void ensureNameLength(String name, String messagePrefix) {
        if (name.length() < 3) {
            throw new IllegalStateException(messagePrefix + " name cannot be less than 3 symbols");
        }
    }

    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalStateException("Age cannot be zero or negative integer");
        }
        this.age = age;
    }

    private void setSalary(double salary) {
        if (salary < 460) {
            throw new IllegalStateException("Salary cannot be less than 460 leva");
        }

        this.salary = salary;
    }


    public void increaseSalary(double bonus) {

        double bonusReduced = age < 30 ? bonus / 2 : bonus;

        double factor = 1.00 + bonusReduced / 100;

        setSalary(salary * factor);
    }

    @Override
    public String toString() {
        return String.format("%s %s gets %.1f leva", firstName, lastName, salary);
    }


}
