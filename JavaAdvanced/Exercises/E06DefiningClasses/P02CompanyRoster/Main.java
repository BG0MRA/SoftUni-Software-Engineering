package E06DefiningClasses.P02CompanyRoster;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int employeesNumber = Integer.parseInt(scanner.nextLine());
        Map<String, Department> departmentsMap = new HashMap<>();

        while (employeesNumber-- > 0) {
            String[] input = scanner.nextLine().split("\\s+");

            String name = input[0];
            double salary = Double.parseDouble(input[1]);
            String position = input[2];
            String departmentName = input[3];
            departmentsMap.putIfAbsent(departmentName, new Department(departmentName));
            Department department = departmentsMap.get(departmentName);
            String email;
            int age;
            Employee employee = null;

            switch (input.length) {
                case 4: //Employee has NO email, has NO age
                    employee = new Employee(name, salary, position, department);
                    break;

                case 6: //Employee has email, has age
                    email = input[4];
                    age = Integer.parseInt(input[5]);
                    employee = new Employee(name, salary, position, department, email, age);
                    break;

                case 5: //has NO email or has NO age
                    if (input[4].matches("\\d+")) {
                        age = Integer.parseInt(input[4]);
                        employee = new Employee(name, salary, position, department, age);
                    } else {
                        email = input[4];
                        employee = new Employee(name, salary, position, department, email);
                    }
                    break;
            }

            department.getEmployeeList().add(employee);
        }

        Department departmentWithMaxAvgSal = (Department) departmentsMap.values().stream()
                .max(Comparator.comparing(Department::getAverageSalary)).get();
        System.out.println("Highest Average Salary: " + departmentWithMaxAvgSal.getName());
        System.out.println(departmentWithMaxAvgSal);

    }

}
