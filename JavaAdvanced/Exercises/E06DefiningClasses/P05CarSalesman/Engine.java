package E06DefiningClasses.P05CarSalesman;

public class Engine {
    private String model;
    private String power;
    private String displacement;
    private String efficiency;

    public Engine(String[] engineInput) {
        this.model = engineInput[0];
        this.power = engineInput[1];
        this.displacement = "n/a";
        this.efficiency = "n/a";

        if (engineInput.length == 3) {
            if (engineInput[2].matches("\\d+")) {
                this.displacement = engineInput[2];
            } else {
                this.efficiency = engineInput[2];
            }
        }

        if (engineInput.length == 4) {
            this.displacement = engineInput[2];
            this.efficiency = engineInput[3];
        }
    }

    @Override
    public String toString() {
        return String.format("%s:%n" +
                "Power: %s%n" +
                "Displacement: %s%n" +
                "Efficiency: %s",
                this.model,this.power,this.displacement, this.efficiency);
    }
}
