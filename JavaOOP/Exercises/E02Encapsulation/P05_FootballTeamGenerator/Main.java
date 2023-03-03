package E02Encapsulation.P05_FootballTeamGenerator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        Map<String, Team> teams = new LinkedHashMap<>();

        while (!"END".equals(line)) {
            String[] tokens = line.split(";");
            String command = tokens[0];
            String teamName = tokens[1];
            String playerName;

            try {
                switch (command) {
                    case "Team":
                        Team team = new Team(teamName);
                        teams.put(teamName, team);
                        break;
                    case "Add":
                        playerName = tokens[2];
                        int endurance = Integer.parseInt(tokens[3]);
                        int sprint = Integer.parseInt(tokens[4]);
                        int dribble = Integer.parseInt(tokens[5]);
                        int passing = Integer.parseInt(tokens[6]);
                        int shooting = Integer.parseInt(tokens[7]);

                        if (!teams.containsKey(teamName)) {
                            System.out.printf("Team %s does not exist.\n", teamName);
                        } else {
                            Player player = new Player(playerName, endurance, sprint, dribble, passing, shooting);
                            teams.get(teamName).addPlayer(player);
                        }
                        break;
                    case "Remove":
                        playerName = tokens[2];
                        teams.get(teamName).removePlayer(playerName);
                        break;
                    case "Rating":
                        if (!teams.containsKey(teamName)) {
                            System.out.printf("Team %s does not exist.\n", teamName);
                        } else {
                            double rating = teams.get(teamName).getRating();
                            System.out.printf("%s - %d%n", teamName, Math.round(teams.get(teamName).getRating()));
                        }
                        break;
                }

            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }

            line = scanner.nextLine();
        }
    }
}
