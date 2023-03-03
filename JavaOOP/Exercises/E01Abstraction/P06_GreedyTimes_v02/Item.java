package E01Abstraction.P06_GreedyTimes_v02;

public class Item implements Comparable {
    private ItemType type;
    private String name;
    private long quantity;

    public Item(ItemType type, String itemName, long quantity) {
        this.type = type;
        this.name = itemName;
        this.quantity = quantity;
    }

//    public static ItemType findItemType (String itemName) {
//        if (itemName.length() == 3) {
//            return ItemType.CASH;
//        } else if (itemName.toLowerCase().endsWith("gem")) {
//            return ItemType.GEM;
//        } else if (itemName.toLowerCase().equals("gold")) {
//            return ItemType.GOLD;
//        } else {
//            return null;
//        }
//    }

    public long getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

    public void addQuantity(long quantity) {
        this.quantity += quantity;
    }

    @Override
    public String toString() {
        return String.format("##%s - %d", name, quantity);
    }

    @Override
    public int compareTo(Object item) {
        Item otherItem = (Item) item;
        int result = Integer.compare(this.getType().ordinal(), otherItem.getType().ordinal());
        if (result == 0) {
            result = otherItem.getName().compareTo(this.getName());
            if (result == 0) {
                result = Long.compare(this.getQuantity(), otherItem.getQuantity());
            }
        }
        return result;
    }
}
