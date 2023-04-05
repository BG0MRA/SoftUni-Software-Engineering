package football.entities.player;

public class Men extends BasePlayer {

    public Men(String name, String nationality, int strength) {
        super(name, nationality, 85.5, strength);
        setStrength(strength);
    }

    @Override
    public void stimulation() {
        setStrength(getStrength() + 145);
    }
}
