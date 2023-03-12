package E04InterfacesAndAbstraction.P07_CollectionHierarchy;

public class MyListImpl extends Collection implements MyList {

    @Override
    public int add(String string) {
        return super.add(0, string);
    }

    @Override
    public String remove() {
        return super.remove(0);
    }

    @Override
    public int getUsed() {
        return super.getUsed();
    }
}
