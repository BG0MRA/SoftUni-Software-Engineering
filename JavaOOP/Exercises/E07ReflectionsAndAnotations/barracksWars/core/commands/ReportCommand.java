package E07ReflectionsAndAnotations.barracksWars.core.commands;


import E07ReflectionsAndAnotations.barracksWars.annotations.Inject;
import E07ReflectionsAndAnotations.barracksWars.interfaces.Repository;

public class ReportCommand extends Command {
    @Inject
    private Repository repository;

    public ReportCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        return this.repository.getStatistics();
    }

    public Repository getRepository() {
        return this.repository;
    }
}
