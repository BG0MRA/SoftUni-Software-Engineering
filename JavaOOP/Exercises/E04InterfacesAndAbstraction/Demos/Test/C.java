package E04InterfacesAndAbstraction.Demos.Test;

public class C implements A, B {

    //Пренаписва и двата метода А
    @Override
    public void A() {
        System.out.println("Method A");
    }

}
