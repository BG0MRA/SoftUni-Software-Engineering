package L03Inheritance.Demos;

public class Main {

    public static class Vehicle {

        private double speed;

        public Vehicle(double speed) {
            setSpeed(speed);
        }

        protected void setSpeed(double speed) {
            this.speed = speed;
        }

        public void stop() {
            speed = 0;
        }
    }

    public static class Car extends Vehicle {
        boolean parkingBrakeOn;

        public Car(double speed) {
            super(speed);
        }

        public Car() {
            this(0);
        }

        @Override
        public void stop () {
            super.stop();
            parkingBrakeOn = true;
        }
    }

    public static class Airplane extends Vehicle {
        double altitude;

        public Airplane(double speed) {
            super(speed);
        }
    }

    public static class Lada extends Car {

    }

    public static void main(String[] args) {

//        Car car = new Car();
        Vehicle car = new Car(); // this is polymorphism
        Vehicle vehicle = new Vehicle(20);
        //car.parkingBrakeOn = true; // doesn't work with polymorphism we have to Override the method stop ()

        vehicle.speed = 212;
        car.speed = 10.0;

        System.out.println(vehicle.speed);
        System.out.println(car.speed);

        car.stop();

        System.out.println(car.speed);
    }
}
