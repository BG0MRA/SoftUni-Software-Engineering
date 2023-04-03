package L09UnitTesting.src.test.java.rpg_lab;

import L09UnitTesting.src.main.java.rpg_lab.Hero;
import L09UnitTesting.src.main.java.rpg_lab.Target;
import L09UnitTesting.src.main.java.rpg_lab.Weapon;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class HeroTest {
    private static final String HERO_NAME = "Pesho";
    ;
    private static final int EXPERIENCE = 10;

    private Hero hero;
    private Weapon weapon;
    private Target facade;

//    @Before
//    public void setUp() throws Exception {
//        weapon = mock(Weapon.class);
//        hero = new Hero(HERO_NAME, weapon);
//    }
//
//    @Test
//    public void testAttackWhenTargetDiesHeroGetsExp() {
//        facade = mock(Target.class);
//        when(facade.isDead()).thenReturn(true);
//        when(facade.giveExperience()).thenReturn(EXPERIENCE);
//
////        doNothing().when(weapon).attack(any());//mocking void methods
//
//        hero.attack(facade);
//
//        assertEquals(EXPERIENCE, hero.getExperience());
//    }
}