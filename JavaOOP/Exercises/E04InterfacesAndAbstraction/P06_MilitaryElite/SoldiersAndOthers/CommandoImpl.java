package E04InterfacesAndAbstraction.P06_MilitaryElite.SoldiersAndOthers;


import E04InterfacesAndAbstraction.P06_MilitaryElite.Interface.Commando;

import java.util.*;
import java.util.stream.Collectors;

public class CommandoImpl extends SpecialisedSoldierImpl implements Commando {

    private List<Mission> missions;

    public CommandoImpl(int id, String firstName, String lastName, double salary, String corp) {
        super(id, firstName, lastName, salary, corp);
        missions = new ArrayList<>();
    }

    @Override
    public void addMission(Mission mission) {
        this.missions.add(mission);
    }

    @Override
    public List<Mission> getMissions() {
        return Collections.unmodifiableList(this.missions);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();

        out.append(super.toString())
                .append(System.lineSeparator())
                .append("Missions:");
        if (!missions.isEmpty()) {
            out.append(System.lineSeparator())
                    .append(missions.stream()
                            .map(m -> "  " + m)
                            .collect(Collectors.joining(System.lineSeparator())));
        }

        return out.toString();
    }
}
