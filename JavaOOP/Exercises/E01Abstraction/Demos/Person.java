package E01Abstraction.Demos;

public class Person {

    public static int age;

    private String firstName;
    private String secondName;

    public Person(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public static void jump() {
        System.out.println("I'm jumping");
    }


}
