package E04InterfacesAndAbstraction.P06_MilitaryElite.SoldiersAndOthers;


import E04InterfacesAndAbstraction.P06_MilitaryElite.Enums.State;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Mission {

    private String codeName;
    private State state;

    public Mission(String codeName, String state) {
        this.codeName = codeName;
        setState(state);
    }

    public String getCodeName() {
        return codeName;
    }

    public State getState() {
        return state;
    }

    private void setState(String state) {

        List<String> values = Arrays.stream(State.values()).
                map(Enum::name)
                .collect(Collectors.toList());

        if (!values.contains(state)) {
            throw new IllegalStateException("Not a valid state: " + state);
        }

        this.state = State.valueOf(state);
    }

    public void completeMission() {
        this.state = State.finished;
    }

    @Override
    public String toString() {
        return String.format("Code Name: %s State: %s", codeName, state.name());
    }
}
