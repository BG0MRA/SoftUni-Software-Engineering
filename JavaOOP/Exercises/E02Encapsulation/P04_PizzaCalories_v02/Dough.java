package E02Encapsulation.P04_PizzaCalories_v02;

import java.util.Arrays;

public class Dough {

    private FlourType flourType;
    private BakingTechnique bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        setFlourType(flourType);
        setBakingTechnique(bakingTechnique);
        setWeight(weight);
    }

    private void setFlourType(String flourType) {
        boolean flourTypeIsNotExists = Arrays.stream(FlourType.values())
                .noneMatch(e -> e.name().equals(flourType));

        if (flourTypeIsNotExists) {
            throw new IllegalStateException("Invalid type of dough.");
        }

        this.flourType = FlourType.valueOf(flourType);
    }

    private void setBakingTechnique(String bakingTechnique) {
        boolean bakingTechniqueIsNotExists = Arrays.stream(BakingTechnique.values())
                .noneMatch(e -> e.name().equals(bakingTechnique));

        if (bakingTechniqueIsNotExists) {
            throw new IllegalStateException("Invalid type of dough.");
        }

        this.bakingTechnique = BakingTechnique.valueOf(bakingTechnique);

    }

    private void setWeight(double weight) {
        if (weight >= 1 && weight <= 200) {
            this.weight = weight;
        } else {
            throw new IllegalStateException("Dough weight should be in the range [1..200].");
        }
    }

    public double calculateCalories() {
        return 2 * weight * flourType.getModifier() * bakingTechnique.getModifier();
    }

}
