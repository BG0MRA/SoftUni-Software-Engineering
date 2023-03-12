package E04InterfacesAndAbstraction.P07_CollectionHierarchy_v02;

public class AddCollection extends Collection implements Addable {


    @Override
    public int add(String item) {
        //add to the end of the list
        super.items.add(item);
        return super.items.size() - 1;
    }
}
