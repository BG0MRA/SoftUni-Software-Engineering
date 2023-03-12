package E04InterfacesAndAbstraction.P06_MilitaryElite.SoldiersAndOthers;


import E04InterfacesAndAbstraction.P06_MilitaryElite.Enums.Corps;
import E04InterfacesAndAbstraction.P06_MilitaryElite.Interface.SpecialisedSoldier;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class SpecialisedSoldierImpl extends PrivateImpl implements SpecialisedSoldier {

    private Corps corps;


    public SpecialisedSoldierImpl(int id, String firstName, String lastName, double salary, String corps) {
        super(id, firstName, lastName, salary);
        setCorps(corps);
    }

    private void setCorps(String corps) {
        List<String> values = Arrays.stream(Corps.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        if (!values.contains(corps)) {
            throw new IllegalStateException("Not a valid Corp: " + corps);
        }

        this.corps = Corps.valueOf(corps);
    }

    @Override
    public Corps getCorps() {
        return this.corps;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        out.append(super.toString())
                .append(System.lineSeparator())
                .append("Corps: ").append(corps.name());

        return out.toString();
    }
}
