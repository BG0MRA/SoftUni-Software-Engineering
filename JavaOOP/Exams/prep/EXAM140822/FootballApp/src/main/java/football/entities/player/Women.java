package football.entities.player;

public class Women extends BasePlayer{

    public Women(String name, String nationality, int strength) {
        super(name, nationality, 60, strength);
        setStrength(strength);
    }

    @Override
    public void stimulation() {
        setStrength(getStrength() + 115);
    }
}
