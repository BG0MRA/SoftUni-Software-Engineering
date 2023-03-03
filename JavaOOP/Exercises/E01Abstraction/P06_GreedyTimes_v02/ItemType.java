package E01Abstraction.P06_GreedyTimes_v02;

public enum ItemType {
    GOLD, GEM, CASH;

    public static ItemType getType(String itemName) {
        if (itemName.length() == 3) {
            return CASH;
        } else if (itemName.toLowerCase().endsWith("gem")) {
            return GEM;
        } else if (itemName.toLowerCase().equals("gold")) {
            return GOLD;
        } else {
            return null;
        }
    }

    public String formattedName() {
        return this.name().charAt(0) + this.name().substring(1).toLowerCase();
    }

}
