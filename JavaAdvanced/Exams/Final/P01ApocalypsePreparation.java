package Final;

import java.util.*;
import java.util.stream.Collectors;

public class P01ApocalypsePreparation {
    public static final int PATCH = 30;
    public static final int BANDAGE = 40;
    public static final int MED_KIT = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deque<Integer> textilesQueue = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(n -> Integer.parseInt(n))
                .forEach(e -> textilesQueue.offer(e));

        Deque<Integer> medicamentsStack = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(n -> Integer.parseInt(n))
                .forEach(e -> medicamentsStack.push(e));

        //input
        // 20 10 40 70 20
        //50 10 30 20 80

        Map<String, Integer> createdItems = new HashMap<>();

        int sumOfElements = 0;
        int additionalResources = 0;
        int patchCount = 0;
        int bandageCount = 0;
        int medKitCount = 0;

        while (!textilesQueue.isEmpty() && !medicamentsStack.isEmpty()) {
            Integer textile = textilesQueue.peek();
            Integer medicaments = medicamentsStack.peek();

            sumOfElements = textile + medicaments;

            if (sumOfElements == PATCH) {
                patchCount++;
                createdItems.put("Patch", patchCount);
                textilesQueue.poll();
                medicamentsStack.pop();

            } else if (sumOfElements == BANDAGE) {
                bandageCount++;
                createdItems.put("Bandage", bandageCount);
                textilesQueue.poll();
                medicamentsStack.pop();

            } else if (sumOfElements == MED_KIT) {
                medKitCount++;
                createdItems.put("MedKit", medKitCount);
                textilesQueue.poll();
                medicamentsStack.pop();

            } else if (sumOfElements > MED_KIT){
                medKitCount++;
                createdItems.put("MedKit", medKitCount);
                additionalResources = sumOfElements - MED_KIT;
                textilesQueue.poll();
                medicamentsStack.pop();
                Integer updatedMedicaments = medicamentsStack.pop();
                medicamentsStack.push(updatedMedicaments + additionalResources);
            } else {
                //can't create anything
                textilesQueue.poll();
                Integer updateMedicament = medicamentsStack.pop();
                medicamentsStack.push(updateMedicament + 10);
            }


        }
        if (textilesQueue.isEmpty() && medicamentsStack.isEmpty()) {
            System.out.println("Textiles and medicaments are both empty.");
        } else if (textilesQueue.isEmpty()) {
            System.out.println("Textiles are empty.");
        } else if (medicamentsStack.isEmpty()) {
            System.out.println("Medicaments are empty.");
        }

        if (!createdItems.isEmpty()) {
            System.out.println(createdItems.entrySet()
                    .stream()
                    .sorted((e1, e2) -> {
                        int e1Result = e1.getValue();
                        int e2Result = e2.getValue();

                        if (e1Result != e2Result){
                            return e2.getValue() - e1.getValue();
                        }

                        return e1.getKey().compareTo(e2.getKey());
                    })
                    .map(entry -> String.format("%s - %d", entry.getKey(), entry.getValue()))
                    .collect(Collectors.joining(System.lineSeparator())));
        }

        if (!medicamentsStack.isEmpty()) {
            System.out.printf("Medicaments left: ");
            System.out.printf(medicamentsStack.toString().replaceAll("[\\[\\]]", ""));
        } else if (!textilesQueue.isEmpty()) {
            System.out.printf("Textiles left: ");
            System.out.printf(textilesQueue.toString().replaceAll("[\\[\\]]", ""));
        }

    }
}
