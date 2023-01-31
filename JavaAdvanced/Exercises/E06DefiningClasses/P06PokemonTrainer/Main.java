package E06DefiningClasses.P06PokemonTrainer;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String inputLine = scanner.nextLine();
        Map<String, Trainer> trainersMap = new LinkedHashMap<>();

        while (!inputLine.equals("Tournament")) {
            String[] trainerInfo = inputLine.split("\\s+");
            String trainerName = trainerInfo[0];
            String pokemonName = trainerInfo[1];
            String pokemonElement = trainerInfo[2];
            int pokemonHealth = Integer.parseInt(trainerInfo[3]);

            trainersMap.putIfAbsent(trainerName, new Trainer(trainerName));
            Trainer trainer = trainersMap.get(trainerName);
            Pokemon pokemon = new Pokemon(pokemonName, pokemonElement, pokemonHealth);
            trainer.addPokemonToPokemonsCollection(pokemon);

            inputLine = scanner.nextLine();
        }

        String command = scanner.nextLine();
        while (!command.equals("End")) {
            String element = command;
            trainersMap.values().forEach(trainer -> trainer.startTournament(element));
            command = scanner.nextLine();
        }

        trainersMap.values().stream()
                .sorted(Comparator.comparing(Trainer::getNumberOfBadges).reversed())
                .forEach(System.out::println);

    }
}
