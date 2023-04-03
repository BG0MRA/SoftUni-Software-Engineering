package E07ReflectionsAndAnotations.barracksWars;


import E07ReflectionsAndAnotations.barracksWars.core.Engine;
import E07ReflectionsAndAnotations.barracksWars.core.commands.CommandInterpreterImpl;
import E07ReflectionsAndAnotations.barracksWars.core.factories.UnitFactoryImpl;
import E07ReflectionsAndAnotations.barracksWars.data.UnitRepository;
import E07ReflectionsAndAnotations.barracksWars.interfaces.CommandInterpreter;
import E07ReflectionsAndAnotations.barracksWars.interfaces.Repository;
import E07ReflectionsAndAnotations.barracksWars.interfaces.UnitFactory;

public class Main {

    public static void main(String[] args) {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImpl();
        CommandInterpreter commandInterpreter = new CommandInterpreterImpl(repository, unitFactory);

        Runnable engine = new Engine(commandInterpreter);
        engine.run();
    }
}
