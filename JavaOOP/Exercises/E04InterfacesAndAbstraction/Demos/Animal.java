package E04InterfacesAndAbstraction.Demos;

public interface Animal {

    void walk();

    default void breathe() {
        System.out.println("Animal breathe ...");
    }

}
