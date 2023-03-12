package L05Polymorphism.P03_Animals;


public class Cat extends Animal {


    public Cat(String name, String favouriteFood) {
        super(name, favouriteFood);
    }

    @Override
    public String explainSelf() {
        return String.format("%s\n%s",super.explainSelf(),"MEEOW");
    }
}
