package L01Abstraction.P04_HotelReservation;

public enum Season {
    AUTUMN(1),
    SPRING(2),
    WINTER(3),
    SUMMER(4);

    private int multiplier;

    //•	1 during Autumn
    //•	2 during Spring
    //•	3 during Winter
    //•	4 during Summer

    Season(int multiplier) {
        this.multiplier = multiplier;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public static Season parse(String str) {
       return Season.valueOf(str.toUpperCase());
    }
}
