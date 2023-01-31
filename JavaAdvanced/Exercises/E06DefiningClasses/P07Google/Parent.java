package E06DefiningClasses.P07Google;

public class Parent {
    String name;
    String birthday;

    public Parent (String name, String birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.name, this.birthday);
    }
}
