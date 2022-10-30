package E06ObjectsAndClasses.P06OrderByAge;

public class Person {
    //полета -> характеристики -> име, ID, възраст
    private String name;
    private String id;
    private int age;

    //конструктор -> създавам обекти от класа
    public Person(String name, String id, int age) {
        //нов празен обект (name = null, id = null, age = 0)
        this.name = name;
        this.id = id;
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        //"{name} with ID: {id} is {age} years old."
        return String.format("%s with ID: %s is %d years old.", this.name, this.id, this.age);
    }
}
