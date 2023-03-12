package E04InterfacesAndAbstraction.P06_MilitaryElite.SoldiersAndOthers;



import E04InterfacesAndAbstraction.P06_MilitaryElite.Interface.Engineer;

import java.util.*;
import java.util.stream.Collectors;

public class EngineerImpl extends SpecialisedSoldierImpl implements Engineer {

    private List<Repair> repairs;

    public EngineerImpl(int id, String firstName, String lastName, double salary, String corps) {
        super(id, firstName, lastName, salary, corps);
        this.repairs = new ArrayList<>();
    }

    @Override
    public void addRepair(Repair repair) {
        this.repairs.add(repair);
    }

    @Override
    public List<Repair> getRepairs() {
        return Collections.unmodifiableList(this.repairs);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        out.append(super.toString())
                .append(System.lineSeparator())
                .append("Repairs:")
                .append(System.lineSeparator())
                .append(repairs.stream()
                        .map(r -> "  " + r)
                        .collect(Collectors.joining(System.lineSeparator())));

        return out.toString();
    }
}
