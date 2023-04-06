package archeologicalExcavations;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExcavationTests {


    @Test
    public void testCtorValid() {
        Excavation excavation = new Excavation("neshto", 10);
        assertEquals("neshto", excavation.getName());
    }

    //        excavation.getName();
    @Test(expected = NullPointerException.class)
    public void testSetNameNotValidNull() {
        Excavation excavation = new Excavation(null, 10);
        assertEquals("neshto", excavation.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testSetNameNotValidEmptyString() {
        Excavation excavation = new Excavation("    ", 10);
        assertEquals("neshto", excavation.getName());
    }

    //        excavation.getCapacity();
    @Test(expected = IllegalArgumentException.class)
    public void testSetCapacityNotValid() {
        Excavation excavation = new Excavation("neshto", -10);
    }

    @Test
    public void testSetCapacityValid() {
        Excavation excavation = new Excavation("neshto", 0);

        assertEquals(0, excavation.getCapacity());
    }

    //        excavation.getCount();
    //        excavation.addArchaeologist();
    @Test
    public void testAddArchaeologistOneTime() {
        Excavation excavation = new Excavation("neshto", 10);
        Archaeologist archaeologist1 = new Archaeologist("Pesho", 60);
        Archaeologist archaeologist2 = new Archaeologist("Dancho", 160);

        excavation.addArchaeologist(archaeologist1);

        assertEquals(1, excavation.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddArchaeologistNotValidCapacity() {
        Excavation excavation = new Excavation("neshto", 0);
        Archaeologist archaeologist1 = new Archaeologist("Pesho", 60);
        Archaeologist archaeologist2 = new Archaeologist("Dancho", 160);

        excavation.addArchaeologist(archaeologist1);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddArchaeologistTwiceTheSameObject() {
        Excavation excavation = new Excavation("neshto", 10);
        Archaeologist archaeologist1 = new Archaeologist("Pesho", 60);
        Archaeologist archaeologist2 = new Archaeologist("Dancho", 160);

        excavation.addArchaeologist(archaeologist1);
        excavation.addArchaeologist(archaeologist1);

    }

    //        excavation.removeArchaeologist();
    @Test
    public void testRemoveArchaeologist() {
        Excavation excavation = new Excavation("neshto", 10);
        Archaeologist archaeologist1 = new Archaeologist("Pesho", 60);
        Archaeologist archaeologist2 = new Archaeologist("Dancho", 160);

        excavation.addArchaeologist(archaeologist1);
        excavation.addArchaeologist(archaeologist2);

        excavation.removeArchaeologist(archaeologist1.getName());


        assertEquals(1, excavation.getCount());
    }

    @Test
    public void testRemoveArchaeologistTwiceTheSameObject() {
        Excavation excavation = new Excavation("neshto", 10);
        Archaeologist archaeologist1 = new Archaeologist("Pesho", 60);
        Archaeologist archaeologist2 = new Archaeologist("Dancho", 160);

        excavation.addArchaeologist(archaeologist1);
        excavation.addArchaeologist(archaeologist2);

        excavation.removeArchaeologist(archaeologist1.getName());
        boolean result = excavation.removeArchaeologist(archaeologist1.getName());


        assertEquals(false, result);
        assertEquals(1, excavation.getCount());
    }

    @Test
    public void testRemoveArchaeologistNotValid() {
        Excavation excavation = new Excavation("neshto", 10);
        Archaeologist archaeologist1 = new Archaeologist("Pesho", 60);
        Archaeologist archaeologist2 = new Archaeologist("Dancho", 160);

        excavation.addArchaeologist(archaeologist1);
        excavation.addArchaeologist(archaeologist2);

        boolean result = excavation.removeArchaeologist("unknown");


        assertEquals(false, result);
    }
}
