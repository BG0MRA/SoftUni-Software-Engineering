package E07ReflectionsAndAnotations.barracksWars.models.units;

public class Horseman extends AbstractUnit {
    private static final int HORSEMAN_HEALTH = 50;
    private static final int HORSEMAN_HEALTH_DAMAGE = 10;

    public Horseman() {
        super(HORSEMAN_HEALTH, HORSEMAN_HEALTH_DAMAGE);
    }
}
