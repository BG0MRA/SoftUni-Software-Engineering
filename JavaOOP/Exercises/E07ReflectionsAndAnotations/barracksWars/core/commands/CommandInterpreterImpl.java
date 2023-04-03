package E07ReflectionsAndAnotations.barracksWars.core.commands;


import E07ReflectionsAndAnotations.barracksWars.annotations.Inject;
import E07ReflectionsAndAnotations.barracksWars.interfaces.CommandInterpreter;
import E07ReflectionsAndAnotations.barracksWars.interfaces.Executable;
import E07ReflectionsAndAnotations.barracksWars.interfaces.Repository;
import E07ReflectionsAndAnotations.barracksWars.interfaces.UnitFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CommandInterpreterImpl implements CommandInterpreter {

    private static final String COMMANDS_PACKAGE_NAME =
            "theme_07_ReflectionAndAnnotation.Exercises.barracksWars.core.commands.";
    private Repository repository;
    private UnitFactory unitFactory;

    public CommandInterpreterImpl(Repository repository, UnitFactory unitFactory) {
        this.repository = repository;
        this.unitFactory = unitFactory;
    }

    @Override
    public Executable interpretCommand(String[] data, String commandName) {
        String className = parseCommandToClassName(commandName);
        Executable command = null;

        try {
            Class clazz = Class.forName(className);
            Constructor <Command>constructor = clazz.getDeclaredConstructors()[0];
            command = constructor.newInstance(new Object[]{data});//???
            //INJECT
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Inject.class)) {
                    //We must inject dependency
                    if (field.getType().equals(Repository.class)) {
                        field.setAccessible(true);
                        field.set(command, repository);
                    } else if (field.getType().equals(UnitFactory.class)) {
                        field.setAccessible(true);
                        field.set(command, unitFactory);
                    }
                }
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return command;

//        switch (commandName) {
//            case "add":
//                return new AddCommand(data, repository, unitFactory);
//            case "report":
//                return new ReportCommand(data, repository, unitFactory);
//            case "fight":
//                return new FightCommand(data, repository, unitFactory);
//            case "retire":
//                return new RetireCommand(data, repository, unitFactory);
//            default:
//                throw new RuntimeException("Invalid command!");
//        }

    }

    private String parseCommandToClassName(String commandName) {
        String firstLetterUpperCase = commandName.substring(0, 1).toUpperCase();
        String restOfTheCommand = commandName.substring(1);
        String className = COMMANDS_PACKAGE_NAME + firstLetterUpperCase + restOfTheCommand + "Command";

        return className;
    }

}
