package E07ReflectionsAndAnotations.barracksWars.core.commands;


import E07ReflectionsAndAnotations.barracksWars.annotations.Inject;
import E07ReflectionsAndAnotations.barracksWars.interfaces.Repository;

public class RetireCommand extends Command {
    @Inject
    private Repository repository;

    public RetireCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        String unitType = this.getData()[1];

        try {
            this.repository.removeUnit(unitType);
            return unitType + " retired!";

        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }

    public Repository getRepository() {
        return this.repository;
    }
}
