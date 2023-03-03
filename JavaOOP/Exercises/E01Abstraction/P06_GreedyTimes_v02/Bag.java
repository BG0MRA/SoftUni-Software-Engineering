package E01Abstraction.P06_GreedyTimes_v02;


import java.util.*;

public class Bag {

    private final long capacity;
    private ArrayList<Item> collectedItems;

    public Bag(long capacity) {
        this.capacity = capacity;
        collectedItems = new ArrayList<>();
    }

    public void collectTreasure(String itemName, long quantity) {
        if (totalQuantity() + quantity <= capacity) {

            ItemType itemType = ItemType.getType(itemName);

            if (itemType != null && isItAccordingTheRules(itemType, quantity)) {
                addItem(itemType, itemName, quantity);
            }
        }
    }

    private long totalQuantity() {
        long totalQuantity = 0;

        for (Item item : collectedItems) {
            totalQuantity += item.getQuantity();
        }

        return totalQuantity;
    }

    private long totalQuantityOfType(ItemType itemType) {
        long totalQuantityOfType = 0;

        for (Item item : collectedItems) {
            if (item.getType() == itemType) {
                totalQuantityOfType += item.getQuantity();
            }
        }

        return totalQuantityOfType;
    }

    private boolean isItAccordingTheRules(ItemType itemType, long quantity) {
        if (itemType == ItemType.GOLD) {
            return true;
        }

        long totalQuantityOfCurrentType = totalQuantityOfType(itemType) + quantity;

        if (itemType == ItemType.CASH
                && (totalQuantityOfCurrentType <= totalQuantityOfType(ItemType.GEM))) {
            return true;
        }

        return itemType == ItemType.GEM
                && totalQuantityOfCurrentType <= totalQuantityOfType(ItemType.GOLD);

    }

    private void addItem(ItemType itemType, String itemName, long itemQuantity) {
        for (Item item : collectedItems) {
            if (item.getName().equals(itemName)) {
                item.addQuantity(itemQuantity);
                return;
            }
        }

        Item item = new Item(itemType, itemName, itemQuantity);
        this.collectedItems.add(item);
    }

    public String bagInfo() {
        StringBuilder sb = new StringBuilder();

        Collections.sort(collectedItems);

        ItemType currentType = null;

        for (Item item : collectedItems) {
            if (item.getType() != currentType) {
                currentType = item.getType();
                sb.append(String.format("<%s> $%d\n", currentType.formattedName(), totalQuantityOfType(currentType)));
            }
            sb.append(item.toString()).append(System.lineSeparator());
        }

        return sb.toString();
    }

}
