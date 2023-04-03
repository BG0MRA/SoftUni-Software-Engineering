package E07ReflectionsAndAnotations.barracksWars.core.commands;


import E07ReflectionsAndAnotations.barracksWars.annotations.Inject;
import E07ReflectionsAndAnotations.barracksWars.interfaces.Repository;
import E07ReflectionsAndAnotations.barracksWars.interfaces.Unit;
import E07ReflectionsAndAnotations.barracksWars.interfaces.UnitFactory;

public class AddCommand extends Command {

    @Inject
    private Repository repository;

    @Inject
    private UnitFactory unitFactory;

    public AddCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        String unitType = this.getData()[1];
        Unit unitToAdd = this.unitFactory.createUnit(unitType);
        this.repository.addUnit(unitToAdd);
        String output = unitType + " added!";
        return output;
    }

    public Repository getRepository() {
        return repository;
    }

    public UnitFactory getUnitFactory() {
        return unitFactory;
    }
}
