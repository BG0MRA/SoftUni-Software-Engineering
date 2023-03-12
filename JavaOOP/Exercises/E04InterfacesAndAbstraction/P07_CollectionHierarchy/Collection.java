package E04InterfacesAndAbstraction.P07_CollectionHierarchy;

import java.util.ArrayList;
import java.util.List;

public abstract class Collection {

    private int maxSize = 100;

    private List<String> items;

    public Collection() {
        items = new ArrayList<>();
    }

    protected int add(String string) {
        validateSize();
        items.add(string);
        return getUsed() - 1;
    }

    protected int add(int index, String string) {
        validateSize();
        items.add(index, string);
        return index;
    }

    protected String remove() {
        return items.remove(getUsed()-1);
    }

    protected String remove(int index) {
        return items.remove(index);
    }

    protected int getUsed() {
        return items.size();
    }

    private void validateSize() {
        if (getUsed() == maxSize) {
            throw new IllegalStateException("The max size is has already been reached!!!");
        }
    }


}
