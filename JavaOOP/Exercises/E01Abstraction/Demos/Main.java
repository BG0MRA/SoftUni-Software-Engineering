package E01Abstraction.Demos;

import java.util.ArrayList;
import java.util.List;

public class Main {
    static {
        System.out.println("Hello I'm Main static block");
    }

    public static void main(String[] args) {

        //1. Abstraction -> to describe only things that we need

//        Season summer = new Season("Summer", 24, "July", "August");
//        Season winter = new Season("Winter", 5, "December", "January");
//        Season spring = new Season("Spring", 12, "April", "May");
//        Season autumn = new Season("Autumn", 12, "September", "October");

//        Season[] seasons = new Season[4];
//
//        seasons[0] = summer;
//        seasons[1] = winter;
//        seasons[2] = autumn;
//        seasons[3] = spring;

        System.out.println(Seasons.SUMMER.ordinal());
        System.out.println("================");

        Person pesho = new Person("Pesho", "Petrov");
        pesho.age = 12;
        Person gosho = new Person("Gosho", "Goshov");
        gosho.age = 15;

        pesho.getFirstName();
        gosho.getFirstName(); //15

        System.out.println(pesho.age); //15 -> age is static
        System.out.println(gosho.age); //15
        System.out.println("================");

        //non-static method:
        SumOperation sumOperation = new SumOperation();
        System.out.println(sumOperation.calculate(1, 5));

        //static method (there is no need to create an instance) :
        System.out.println(Sum.calculate(1, 5));

        System.out.println("================");


        //non-static method
        Car car = new Car("Mercedes", 1, 100);
        System.out.println(car.getBrand(car));

        //static method
        System.out.println(Car.convertKmToMiles(12));

        System.out.println("================");

        Person person = new Person("Ivan", "Georgiev");
        System.out.println(person.getFirstName());

        Person person2 = new Person("Petar", "Dimitrov");
        System.out.println(person2.getFirstName());

        Person.jump();

    }
}
