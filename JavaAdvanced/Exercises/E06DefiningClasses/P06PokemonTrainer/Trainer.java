package E06DefiningClasses.P06PokemonTrainer;

import java.util.ArrayList;
import java.util.List;

public class Trainer {
    private String name;
    private int numberOfBadges;
    private List<Pokemon> pokemonsCollection;

    public Trainer(String name) {
        this.name = name;
        this.numberOfBadges = 0;
        this.pokemonsCollection = new ArrayList<>();
    }

    public void addPokemonToPokemonsCollection(Pokemon pokemon) {
        this.pokemonsCollection.add(pokemon);
    }

    public void startTournament(String element) {
        if (this.containsPokemonWithElement(element)) {
            numberOfBadges++;
        } else {
            pokemonsCollection.forEach(Pokemon::loseHealth);
            pokemonsCollection.removeIf(pokemon -> pokemon.getHealth() <= 0);
        }
    }

    public boolean containsPokemonWithElement(String element) {
        return this.pokemonsCollection.stream()
                .anyMatch(pokemon -> pokemon.getElement().equals(element));
    }

    @Override
    public String toString () {
        return this.name+" "+this.numberOfBadges+" "+this.pokemonsCollection.size();
    }

    public int getNumberOfBadges() {
        return this.numberOfBadges;
    }

    public List<Pokemon> getPokemonsCollection() {
        return this.pokemonsCollection;
    }
}
