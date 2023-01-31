package E06DefiningClasses.P07Google;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Person {
    private String name;
    private Company company;
    private List<Pokemon> pokemons;
    private List<Parent> parents;
    private List<Children> children;
    private Car car;

    public Person(String name) {
        this.name = name;
        this.company = null;
        this.pokemons = new ArrayList<>();
        this.parents = new ArrayList<>();
        this.children = new ArrayList<>();
        this.car = null;
    }

    public void addPokemon(Pokemon pokemon) {
        this.pokemons.add(pokemon);
    }

    public void addParent(Parent parent) {
        this.parents.add(parent);
    }

    public void addChild(Children child) {
        this.children.add(child);
    }

    public String pokemonsToString() {
        if (pokemons.size() > 0) {
            return String.format("Pokemon:%n" +
                    "%s", this.pokemons.stream()
                    .map(Pokemon::toString)
                    .collect(Collectors.joining(System.lineSeparator())));
        }
        return "Pokemon:";
    }

    public String parentsToString() {
        if (this.parents.size() > 0) {
            return String.format("Parents:%n" +
                    "%s", this.parents.stream()
                    .map(parent -> parent.toString())
                    .collect(Collectors.joining(System.lineSeparator())));
        }
        return "Parents:";
    }

    public String childrenToString() {
        if (children.size() > 0) {
            return String.format("Children:%n" +
                    "%s", this.children.stream()
                    .map(child -> child.toString())
                    .collect(Collectors.joining(System.lineSeparator())));
        }
        return "Children:";
    }

    public String personCompanyToString() {
        if (this.company != null) {
            return company.toString();
        }
        return "Company:";
    }

    public String personCarToString() {
        if (this.car != null) {
            return car.toString();
        }
        return "Car:";
    }

    @Override
    public String toString() {
        return String.format("%s%n" +
                        "%s%n" +
                        "%s%n" +
                        "%s%n" +
                        "%s%n" +
                        "%s",
                this.name, this.personCompanyToString(), this.personCarToString(),
                this.pokemonsToString(),
                this.parentsToString(), this.childrenToString());
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getName() {
        return this.name;
    }


}
