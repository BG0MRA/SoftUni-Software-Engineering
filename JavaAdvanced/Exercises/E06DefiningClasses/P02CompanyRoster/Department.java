package E06DefiningClasses.P02CompanyRoster;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Department {
    private String name;
    private List<Employee> employeeList;

    public Department(String name) {
        this.name = name;
        this.employeeList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return employeeList.stream()
                .sorted(Comparator.comparing(Employee::getSalary).reversed())
                .map(Employee::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public double getAverageSalary() {

        int numberOfEmployees = this.employeeList.size();
        if (numberOfEmployees == 0) {
            return 0;
        }

        return this.employeeList.stream()
                .map(Employee::getSalary)
                .mapToDouble(Double::doubleValue)
                .average().getAsDouble();

    }


    public String getName() {
        return name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }
}
