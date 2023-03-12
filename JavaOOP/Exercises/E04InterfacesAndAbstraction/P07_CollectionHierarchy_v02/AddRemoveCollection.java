package E04InterfacesAndAbstraction.P07_CollectionHierarchy_v02;

public class AddRemoveCollection extends Collection implements AddRemovable {

    @Override
    public int add(String item) {
        super.items.add(0, item);
        return 0;
    }

    @Override
    public String remove() {
        return super.items.remove(super.items.size()-1);
    }
}
