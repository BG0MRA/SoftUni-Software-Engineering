package E04InterfacesAndAbstraction.Demos;

public class Goat extends Mammal implements Animal,Swimmer {

    private  String name;
    private int requiredMilk;

    public Goat(String name) {
        this.name = name;
    }

    @Override
    public void walk() {
        System.out.println("Goat " + name + " walking ...");
    }

    @Override
    public void swim() {
        System.out.println("Goat " + name + " swimming ...");
    }

    public void drinkMilk () {
        System.out.println(name + " drinking milk ...");
    }
}
