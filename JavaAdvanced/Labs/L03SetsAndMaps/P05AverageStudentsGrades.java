package L03SetsAndMaps;

import java.util.*;

public class P05AverageStudentsGrades {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        Map<String, List<Double>> studentsMap = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            String[] input = scanner.nextLine().split("\\s+");
            String student = input[0];
            double grade = Double.parseDouble(input[1]);
            studentsMap.putIfAbsent(student, new ArrayList<>());
            studentsMap.get(student).add(grade);
        }

        for (var entry : studentsMap.entrySet()) {
            String student = entry.getKey();
            List<Double> grades = entry.getValue();

            double avgGrade = 0;
            StringBuilder sb = new StringBuilder();
            for (Double grade : grades) {
                avgGrade += grade;
                sb.append(String.format("%.2f ", grade));
            }

            if (grades.size() > 0) {
                avgGrade /= grades.size();
            }

            String gradesString = sb.toString();
            String avgGradeString = String.format("(avg: %.2f)", avgGrade);
            System.out.println(student + " -> " + gradesString + avgGradeString);

        }

    }
}
