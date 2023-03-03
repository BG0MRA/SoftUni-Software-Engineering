package E03Inheritance.P03_PlayersAndMonsters;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String username = scanner.nextLine();
        int level = Integer.parseInt(scanner.nextLine());

        Hero hero = new Hero(username, 11);
        System.out.println(hero);

        Elf elf = new Elf(username, 11);
        System.out.println(elf);

        MuseElf museElf = new MuseElf(username, 11);
        System.out.println(museElf);

        Wizard wizard = new Wizard(username, level);
        System.out.println(wizard);

        DarkWizard darkWizard = new DarkWizard(username, level);
        System.out.println(darkWizard);

        SoulMaster soulMaster = new SoulMaster(username, level);
        System.out.println(soulMaster);

        Knight knight = new Knight(username, level);
        System.out.println(knight);

        DarkKnight darkKnight = new DarkKnight(username, level);
        System.out.println(darkKnight);

        BladeKnight bladeKnight = new BladeKnight(username, level);
        System.out.println(bladeKnight);

    }
}
