package L02Encapsulation.P04_FirstAndReserveTeam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Team {
    private String name;
    private List<Person> firstTeam;
    private List<Person> reserveTeam;

    public Team(String name) {
        setName(name);
        firstTeam = new ArrayList<>();
        reserveTeam = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public List<Person> getFirstTeam() {

        List <Person> otherList = new ArrayList<>();
        otherList.addAll(firstTeam);

        return Collections.unmodifiableList(otherList);
    }

    public List<Person> getReserveTeam() {

        List <Person> otherList = new ArrayList<>();
        otherList.addAll(reserveTeam);

        return Collections.unmodifiableList(reserveTeam);

    }

    public void addPlayer (Person person) {
        if (person.getAge() < 40) {
            firstTeam.add(person);
        } else {
            reserveTeam.add(person);
        }
    }
}
