import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ExamUtils {
    //Matrix
    public static int[][] readMatrix(Scanner scanner) {
        String[] dimensions = scanner.nextLine().split(" ");
        int rows = Integer.parseInt(dimensions[0]);
        int cols = Integer.parseInt(dimensions[1]);
        int[][] matrix = new int[rows][cols];
        for (int row = 0; row < rows; row++) {
            int[] arr = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            matrix[row] = arr;
        }

        return matrix;
    }

    public static boolean isInBounds(int row, int col, int[][] matrix) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    public static boolean isOutBounds(int row, int col, int[][] matrix) {
        return !isInBounds(row, col, matrix);
    }
    private static void fillMatrix(int[][] matrix, Scanner scanner) {
        for (int row = 0; row < matrix.length ; row++) {
            for (int col = 0; col < matrix.length; col++) {
                matrix[row][col] = scanner.nextInt();
            }
        }
    }

    private static void fillMatrix(double[][] matrix, Scanner scanner) {
        for (int row = 0; row < matrix.length ; row++) {
            for (int col = 0; col < matrix.length; col++) {
                matrix[row][col] = scanner.nextDouble();
            }
        }
    }

    private static void fillMatrix(String[][] matrix, Scanner scanner) {
        for (int row = 0; row < matrix.length ; row++) {
            //scanner.nextLine() -> "1 2 3"
            //scanner.nextLine().split(" ") -> ["1", "2", "3"]
            matrix[row] = scanner.nextLine().split("\\s+");
        }
    }
    //2. извеждане на данни от матрица
    private static void printMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println(); //свали курсора на следващия ред
        }
    }
    private static void printMatrix(String[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println(); //свали курсора на следващия ред
        }
    }
    public static void printMatrix(char[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[r].length; c++) {
                System.out.print(matrix[r][c] + " ");
            }
            System.out.println();
        }
    }

// 3. диагонали в квадратна матрица
//  - главен диагонал -> индекс на ред = индекс на колона
//  - второстепенния диагонал -> индекс на колона = размер на матрица - индекс на ред - 1
// 4. валидираме индекси при правоъгълна матрица:
//  - брой редове -> matrix.lenght
//	- брой на колони -> matrix[0].lenght
//
// при квадратна матрица:
//  - брой редове -> matrix.lenght
//	- брой на колони -> matrix.lenght
//
//  - валиден ред: ред >= 0 и ред < брой на редове
//  - невалиден ред: ред < 0 или ред >= брой на редове
//
//  - валидна колона: колона >= 0 и колона < брой на колони
//  - валидна колона: колона < 0 или колона >= брой на колона
//
// 5. движения в матрица
//	- ляво с 1 позиция -> колона - 1
//  - дясно с 1 позиция -> колона + 1
//  - надолу с 1 позиция -> ред + 1
//  - нагоре с 1 позиция -> ред - 1

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //first Method of input Deque
        Deque<Integer> milligramsCaffeine = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split(",\\s+"))
                .mapToInt(n -> Integer.parseInt(n))
                .forEach(e -> milligramsCaffeine.push(e));

        //Queue
        Deque<Integer> energyDrinks = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split(",\\s+"))
                .mapToInt(n -> Integer.parseInt(n))
                .forEach(e -> energyDrinks.offer(e));
        //Second Method of input Deque
        String[] inputDailyFood = scanner.nextLine().split(",\\s+");
        Deque<Integer> dailyFoodStack = new ArrayDeque<>();
        for (String dailyFood : inputDailyFood) {
            dailyFoodStack.push(Integer.parseInt(dailyFood));
        }

        //Third Method if input Deque
        ArrayDeque<Integer> energyDrinksQueue = Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        //Printing Deque
        if (!energyDrinksQueue.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Drinks left: ");
            sb.append(energyDrinksQueue.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", ")));
            System.out.println(sb.toString());
        }
        //Another Print
        System.out.print("Liquids left: ");
        if (liquids.isEmpty()) {
            System.out.println("none");
        } else {
            System.out.println(liquids.toString().replaceAll("[\\[\\]]", ""));
        }

        System.out.print("Ingredients left: ");
        if (ingredients.isEmpty()) {
            System.out.println("none");
        } else {
            System.out.println(ingredients.toString().replaceAll("[\\[\\]]", ""));
        }
        //Map setup
//        Map<String, Integer> dishCount = new LinkedHashMap<>() {{
//            put("Biscuit", 0);
//            put("Cake", 0);
//            put("Pie", 0);
//            put("Pastry", 0);
//        }};

        //useful Stream
        // int sumOfIngredients = ingredientsQueue.stream()
        //          .mapToInt(Integer::intValue).sum();

        //     System.out.println(cocktails.entrySet().stream()
        //           .map(entry -> String.format(" # %s --> %d", entry.getKey(), entry.getValue()))
        //           .collect(Collectors.joining(System.lineSeparator())));
    }

    //When .toString Method is as it require to Print
    public String getStatistics() {
        return String.format("Hall size: %d", this.getCount())
                + String.format("\n%s", this.data.stream()
                .map(student -> student.toString())
                .collect(Collectors.joining(System.lineSeparator())));
    }

    //Another print Method
    public String getStatistics() {
        String animalPrint = this.data
                .stream()
                .map(a -> String.format("%s %s", a.getName(), a.getCaretaker()))
                .collect(Collectors.joining(System.lineSeparator()));

        return String.format("The shelter has the following animals:\n%s", animalPrint);

//        StringBuilder statistics = new StringBuilder();
//        statistics.append("The shelter has the following animals:");
//        statistics.append(System.lineSeparator());
//        statistics.append(data.stream()
//                .map(a -> String.format("%s %s", a.getName(), a.getCaretaker()))
//                .collect(Collectors.joining(System.lineSeparator())));
//
//        return statistics.toString();

    }
}
