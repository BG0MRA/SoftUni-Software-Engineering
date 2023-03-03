package E01Abstraction.Demos;

public class Car {
    private String brand;
    private int age;
    private int speed;

    public Car(String brand, int age, int speed) {
        this.brand = brand;
        this.age = age;
        this.speed = speed;
    }

    public String getBrand(Car car) {
        return brand;
    }

    public int getAge() {
        return age;
    }

    public int getSpeed() {
        return speed;
    }

    //this method doesn't need any instance
    public static double convertKmToMiles(double km) {
        return km * 0.621371;
    }
}
