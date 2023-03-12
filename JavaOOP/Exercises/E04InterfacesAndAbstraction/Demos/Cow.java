package E04InterfacesAndAbstraction.Demos;

public class Cow extends Mammal implements Animal {

    private String name;
    private int requiredMilk;

    public Cow(String name) {
        this.name = name;
    }

    @Override
    public void walk() {
        System.out.println("Cow " + name + " walking ...");
    }

    public void drinkMilk () {
        System.out.println();
    }

}
