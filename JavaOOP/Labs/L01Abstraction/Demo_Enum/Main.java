package L01Abstraction.Demo_Enum;

public class Main {
    public static void main(String[] args) {

//        DayOfWeek = new DayOfWeek();
        DayOfWeek dayOfWeek = DayOfWeek.TUESDAY;

        System.out.println(dayOfWeek.getDayNumber());
    }
}
