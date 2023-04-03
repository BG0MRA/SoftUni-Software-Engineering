package L09UnitTesting.src.main.java.rpg_lab;

public interface Weapon {

    int getAttackPoints();

    int getDurabilityPoints();

    void attack(Target target);
}
