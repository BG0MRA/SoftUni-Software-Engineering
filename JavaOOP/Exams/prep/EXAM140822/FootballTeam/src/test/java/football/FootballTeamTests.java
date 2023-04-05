package football;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FootballTeamTests {

    //        team.getName();
    @Test
    public void testValidCtor() {
        FootballTeam team = new FootballTeam("spartak", 10);
        assertEquals("spartak", team.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testNotValidCtorNull() {
        FootballTeam team = new FootballTeam(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void testNotValidCtorEmpty() {
        FootballTeam team = new FootballTeam("    ", 10);
    }

    //        team.getVacantPositions();
    @Test
    public void testVacantPositionValid() {
        FootballTeam team = new FootballTeam("spartak", 10);
        assertEquals(10, team.getVacantPositions());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testVacantPositionNegativeInput() {
        FootballTeam team = new FootballTeam("spartak", -10);
    }

    //        team.addFootballer();
    @Test(expected = IllegalArgumentException.class)
    public void testAddFootballerTeamIsFull() {
        FootballTeam team = new FootballTeam("spartak", 0);
        Footballer player = new Footballer("pesho");

        team.addFootballer(player);

    }

    @Test
    public void testAddFootballerIsValid() {
        FootballTeam team = new FootballTeam("spartak", 10);
        Footballer player = new Footballer("pesho");

        team.addFootballer(player);
        team.addFootballer(player);

        assertEquals(2, team.getCount());

    }

    //        team.getCount();
    @Test
    public void testGetCountFunction() {
        FootballTeam team = new FootballTeam("spartak", 10);
        Footballer player = new Footballer("pesho");

        team.addFootballer(player);
        team.addFootballer(player);

        assertEquals(2, team.getCount());
    }

    //        team.removeFootballer();
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveFootballerNullFootballerName() {
        FootballTeam team = new FootballTeam("spartak", 10);

        team.removeFootballer("Dancho");

    }

    @Test
    public void testRemoveFootballer() {
        FootballTeam team = new FootballTeam("spartak", 10);
        Footballer player1 = new Footballer("pesho");
        Footballer player2 = new Footballer("Dancho");
        team.addFootballer(player1);
        team.addFootballer(player2);


        team.removeFootballer("Dancho");

        assertEquals(1, team.getCount());

    }


    //        team.footballerForSale();
    @Test(expected = IllegalArgumentException.class)
    public void testFootballerForSaleUnknownFootballerName() {
        FootballTeam team = new FootballTeam("spartak", 10);

        team.footballerForSale("Dancho");

    }

    @Test
    public void testFootballerForSaleIsValid() {
        FootballTeam team = new FootballTeam("spartak", 10);
        Footballer player1 = new Footballer("pesho");
        team.addFootballer(player1);

        Footballer isForSale = team.footballerForSale("pesho");

        assertEquals(false, isForSale.isActive());

    }
}
