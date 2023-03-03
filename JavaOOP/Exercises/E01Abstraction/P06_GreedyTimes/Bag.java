package E01Abstraction.P06_GreedyTimes;

import java.util.*;
import java.util.stream.Collectors;

public class Bag {
    private Map<String, Long> gold;
    private Map<String, Long> gems;
    private Map<String, Long> cash;
    private long capacity;

    public Bag(long capacity) {
        this.capacity = capacity;
        gold = new HashMap<>();
        gems = new HashMap<>();
        cash = new HashMap<>();
    }

    private long totalTreasures() {

        long gemsCollected = sumOfMapValues(gems);
        long cashCollected = sumOfMapValues(cash);
        long goldCollected = sumOfMapValues(gold);

        return goldCollected + gemsCollected + cashCollected;
    }

    private long sumOfMapValues(Map<String, Long> map) {
        if (map.isEmpty()) {
            return 0;
        } else {
            return map.values().stream().mapToLong(Long::longValue).sum();
        }
    }

    private Map<String, Long> getSortedMap(Map<String, Long> map) {
        LinkedHashMap<String, Long> sortedMap = new LinkedHashMap<>();

        map.entrySet().stream().sorted((entry1, entry2) -> {
                    int result = entry2.getKey().compareTo(entry1.getKey());
                    if (result == 0) {
                        return entry1.getValue().compareTo(entry2.getValue());
                    } else {
                        return result;
                    }
                })
                .forEach(entry -> sortedMap.put(entry.getKey(), entry.getValue()));
        return sortedMap;
    }

    private String getTypeOfTreasure(String item) {
        if (item.length() == 3) {
            return "Cash";
        } else if (item.toLowerCase().endsWith("gem")) {
            return "Gem";
        } else if (item.toLowerCase().equals("gold")) {
            return "Gold";
        } else {
            return "Other";
        }
    }

    public void collectTreasure(String item, long quantity) {
        if (totalTreasures() + quantity <= capacity) {

            String typeOfTreasure = getTypeOfTreasure(item);

            switch (typeOfTreasure) {

                case "Cash":
                    if (sumOfMapValues(cash) + quantity <= sumOfMapValues(gems)) {
                        cash.putIfAbsent(item, 0L);
                        cash.put(item, cash.get(item) + quantity);
                    }
                    break;
                case "Gem":
                    if (sumOfMapValues(gems)+quantity <= sumOfMapValues(gold)) {
                        gems.putIfAbsent(item, 0L);
                        gems.put(item, gems.get(item) + quantity);
                    }
                    break;
                case "Gold":
                    gold.putIfAbsent(item, 0L);
                    gold.put(item, gold.get(item) + quantity);
                    break;

            }
        }
    }

    public String mapInfo(Map<String, Long> map) {
        map = getSortedMap(map);
        return map.entrySet().stream()
                .map(entry -> String.format("##%s - %d", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    public String info() {
        StringBuilder sb = new StringBuilder();
        if (!gold.isEmpty()) {
            sb.append(String.format("<Gold> $%d", sumOfMapValues(gold)));
            sb.append(System.lineSeparator());
            sb.append(mapInfo(gold)).append(System.lineSeparator());
        }

        if (!gems.isEmpty()) {
            sb.append(String.format("<Gem> $%d", sumOfMapValues(gems)));
            sb.append(System.lineSeparator());
            sb.append(mapInfo(gems)).append(System.lineSeparator());
        }

        if (!cash.isEmpty()) {
            sb.append(String.format("<Cash> $%d", sumOfMapValues(cash)));
            sb.append(System.lineSeparator());
            sb.append(mapInfo(cash));
        }

        return sb.toString();
    }
}
