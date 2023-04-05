package football.entities.field;

import football.entities.player.Player;
import football.entities.supplement.Supplement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static football.common.ConstantMessages.NOT_ENOUGH_CAPACITY;
import static football.common.ExceptionMessages.FIELD_NAME_NULL_OR_EMPTY;

public abstract class BaseField implements Field {

    private String name;
    private int capacity;
    private Collection<Supplement> supplements;
    private Collection<Player> players;

    public BaseField(String name, int capacity) {
        this.setName(name);
        this.capacity = capacity;
        supplements = new ArrayList<>();
        players = new ArrayList<>();
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(FIELD_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int sumEnergy() {
//        int result = 0;
//        for (Supplement supplement : supplements) {
//            result += supplement.getEnergy();
//        }
//        return result;
        return supplements
                .stream()
                .mapToInt(s -> s.getEnergy())
                .sum();
    }

    @Override
    public void addPlayer(Player player) {
        if (players.size() >= capacity) {
            throw new IllegalStateException(NOT_ENOUGH_CAPACITY);
        }

        players.add(player);
    }

    @Override
    public void removePlayer(Player player) {
        players.remove(player);
    }

    @Override
    public void addSupplement(Supplement supplement) {
        supplements.add(supplement);
    }

    @Override
    public void drag() {
//        for (Player player : players) {
//            player.stimulation();
//        }
//
        players.stream().forEach(player -> player.stimulation());
    }


    @Override
    public Collection<Player> getPlayers() {
        return players;
    }

    @Override
    public Collection<Supplement> getSupplements() {
        return supplements;
    }

    @Override
    public String getInfo() {
        //StadeDeFrance (ArtificialTurf):
        //Player: Sara Mila
        //Supplement: 1
        //Energy: 90

        String playerReport = players.isEmpty()
                ? "none"
                : players.stream().map(p -> p.getName())
                .collect(Collectors.joining(" "));

        return String.format("%s (%s):" + System.lineSeparator() +
                        "Player: %s" +System.lineSeparator()  +
                        "Supplement: %d" + System.lineSeparator()  +
                        "Energy: %d",
                name, getClass().getSimpleName(),
                playerReport, supplements.size(), sumEnergy()).trim();
    }

}
