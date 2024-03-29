package L01Abstraction.P04_HotelReservation;

public enum Discount {

    VIP(0.80),
    SECOND_VISIT(0.90),
    NONE(1.0);

    private double priceReductionFactor;

    Discount(double priceReductionFactor) {
        this.priceReductionFactor = priceReductionFactor;
    }

    public static Discount parse(String str) {
        switch (str) {
            case "VIP":
                return VIP;
            case "SecondVisit":
                return SECOND_VISIT;
            case "None":
                return NONE;
            default:
                throw new IllegalArgumentException("Unknown enum value: " + str);
        }
    }

    public double getPriceReductionFactor() {
        return priceReductionFactor;
    }
}
