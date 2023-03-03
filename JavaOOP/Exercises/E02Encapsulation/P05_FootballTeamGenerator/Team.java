package E02Encapsulation.P05_FootballTeamGenerator;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Player> players;

    public Team(String name) {
        setName(name);
        players = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalStateException("A name should not be empty.");
        }

        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(String playerName) {
        boolean isRemoved = players.removeIf(p -> p.getName().equals(playerName));

        if (!isRemoved) {
            throw new IllegalStateException(String.format("Player %s is not in %s team.", playerName, name));
        }
    }

    public double getRating() {
        return players.stream()
                .mapToDouble(Player::overallSkillLevel)
                .average().orElse(0.0);
    }
}
