package E07ReflectionsAndAnotations.barracksWars.models.units;

public class Gunner extends AbstractUnit {
    private static final int GUNNER_HEALTH = 20;
    private static final int GUNNER_HEALTH_DAMAGE = 20;

    public Gunner() {
        super(GUNNER_HEALTH, GUNNER_HEALTH_DAMAGE);
    }
}
