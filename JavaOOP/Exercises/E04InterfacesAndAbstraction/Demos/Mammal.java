package E04InterfacesAndAbstraction.Demos;

public abstract class Mammal implements Animal{

    private int requiredMilk;

    public int getRequiredMilk() {
        return requiredMilk;
    }

    public void setRequiredMilk(int requiredMilk) {
        this.requiredMilk = requiredMilk;
    }

    public void drinkMilk() {
        System.out.println("Drinking milk ...");
    }
}


