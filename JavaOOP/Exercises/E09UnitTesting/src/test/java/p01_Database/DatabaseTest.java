package E09UnitTesting.src.test.java.p01_Database;

import E09UnitTesting.src.main.java.p01_Database.Database;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DatabaseTest {
    private Database database;
    private final Integer[] NUMBERS = {7, 3, 2, 1};

    @Before
    public void setUp() throws OperationNotSupportedException {
        database = new Database(NUMBERS);
    }

    @Test
    public void testConstructorCreatesValidDB() throws OperationNotSupportedException {
        Integer[] dbElements = database.getElements();

//        for (int i = 0; i < dbElements.length; i++) {
//            assertEquals(NUMBERS[i], dbElements[i]);
//        }

        assertArrayEquals(NUMBERS, dbElements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorShouldThrowsWithMoreThan16elements() throws OperationNotSupportedException {
        Integer[] bigArray = new Integer[17];
        new Database(bigArray);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorShouldThrowsWithNoelElements() throws OperationNotSupportedException {
        Integer[] emptyArray = new Integer[0];
        new Database(emptyArray);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddNullShouldThrow() throws OperationNotSupportedException {
        database = new Database(NUMBERS);
        database.add(null);
    }

    @Test
    public void testAddElement() throws OperationNotSupportedException {
        database = new Database(NUMBERS);
        database.add(42);
        Integer[] dbElements = database.getElements();

        assertEquals(Integer.valueOf(42), dbElements[dbElements.length - 1]);
        assertEquals(NUMBERS.length + 1, dbElements.length);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveShouldThrowWithEmptyDB() throws OperationNotSupportedException {
        for (int i = 0; i < NUMBERS.length; i++) {
            database.remove();
        }

        database.remove();
    }

    @Test
    public void testRemoveElement() throws OperationNotSupportedException {
        database.remove();
        Integer[] dbElements = database.getElements();

        assertEquals(NUMBERS[NUMBERS.length - 2], dbElements[dbElements.length - 1]);
        assertEquals(NUMBERS.length - 1, dbElements.length);
    }
}