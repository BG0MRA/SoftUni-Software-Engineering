package E04InterfacesAndAbstraction.P06_MilitaryElite;



import E04InterfacesAndAbstraction.P06_MilitaryElite.SoldiersAndOthers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

//        List<SoldierImpl> soldiers = new ArrayList<>();
        List<PrivateImpl> unassignedPrivates = new ArrayList<>();

        while (!"End".equals(input)) {
            String[] soldierData = input.split("\\s+");
            String clazz = soldierData[0];
            int id = Integer.parseInt(soldierData[1]);
            String firstName = soldierData[2];
            String lastName = soldierData[3];
            double salary;
            String corps;

            switch (clazz) {
                case "Private":
                    salary = Double.parseDouble(soldierData[4]);
                    PrivateImpl priv = new PrivateImpl(id, firstName, lastName, salary);
                    unassignedPrivates.add(priv);
                    System.out.println(priv);
                    break;

                case "LieutenantGeneral":
                    salary = Double.parseDouble(soldierData[4]);
                    LieutenantGeneralImpl lieutenantGeneral = new LieutenantGeneralImpl(id, firstName, lastName, salary);
                    addPrivates(unassignedPrivates, soldierData, lieutenantGeneral);
                    System.out.println(lieutenantGeneral);
                    break;

                case "Engineer":
                    salary = Double.parseDouble(soldierData[4]);
                    corps = soldierData[5];

                    EngineerImpl engineer;

                    try {
                        engineer = new EngineerImpl(id, firstName, lastName, salary, corps);
                        addRepairs(soldierData, engineer);
                        System.out.println(engineer);
                    } catch (IllegalStateException e) {
                        input = scanner.nextLine();
                        continue;
                    }
                    break;

                case "Commando":
                    salary = Double.parseDouble(soldierData[4]);
                    corps = soldierData[5];

                    CommandoImpl commando;

                    try {
                        commando = new CommandoImpl(id, firstName, lastName, salary, corps);
                        addMissions(soldierData, commando);
                        System.out.println(commando);

                    } catch (IllegalStateException e) {
                        input = scanner.nextLine();
                        continue;
                    }
                    break;
                case "Spy":
                    String codeNumber = soldierData[4];
                    SpyImpl spy = new SpyImpl(id, firstName, lastName, codeNumber);
                    System.out.println(spy);
                    break;
            }

            input = scanner.nextLine();
        }

    }

    private static void addMissions(String[] soldierData, CommandoImpl commando) {
        for (int i = 6; i < soldierData.length; i += 2) {
            String codeName = soldierData[i];
            String state = soldierData[i + 1];
            Mission mission;
            try {
                mission = new Mission(codeName, state);
            } catch (IllegalStateException e) {
                continue;
            }
            commando.addMission(mission);
        }
    }

    private static void addRepairs(String[] soldierData, EngineerImpl engineer) {
        for (int i = 6; i < soldierData.length; i += 2) {
            String partName = soldierData[i];
            int hoursWorked = Integer.parseInt(soldierData[i + 1]);
            Repair repair = new Repair(partName, hoursWorked);
            engineer.addRepair(repair);
        }
    }

    private static void addPrivates(List<PrivateImpl> unassignedPrivates, String[] soldierData, LieutenantGeneralImpl lieutenantGeneral) {

        List<PrivateImpl> assignedPrivates = new ArrayList<>();

        for (int i = 5; i < soldierData.length; i++) {
            int privateID = Integer.parseInt(soldierData[i]);


            unassignedPrivates.stream()
                    .filter(p -> p.getId() == privateID)
                    .forEach(p -> {
                        lieutenantGeneral.addPrivate(p);
                        assignedPrivates.add(p);
                    });
        }

        unassignedPrivates.removeAll(assignedPrivates);


    }
}
