package E09UnitTesting.src.test.java.p02_ExtendedDatabase;

import E09UnitTesting.src.main.java.p02_ExtendedDatabase.Database;
import E09UnitTesting.src.main.java.p02_ExtendedDatabase.Person;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.assertEquals;

public class DatabaseTest {

    private Database database;
    private static final Person PESHO = new Person(1, "Pesho");
    private static final Person TOSHKO = new Person(2, "Toshko");
    private static final Person GOSHKO = new Person(3, "Goshko");
    private static final Person[] PEOPLE = {PESHO, TOSHKO, GOSHKO};
    private static final Person PERSON_FOR_ADDING = new Person(999999, "TestForAdding");
    private static final String PERSON_NAME_MISSING = "Some missing name";
    private static final int PERSON_ID_MISSING = -1;

    @Before
    public void prepare() throws OperationNotSupportedException {
        database = new Database(PEOPLE);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameShouldThrowWithNull() throws OperationNotSupportedException {
        database.findByUsername(null);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUsernameShouldThrowIfMissing() throws OperationNotSupportedException {
        database.findByUsername(PERSON_NAME_MISSING);
    }

    @Test
    public void testFindByUsernameShouldReturnPerson() throws OperationNotSupportedException {
        Person person = database.findByUsername(PEOPLE[0].getUsername());
        assertEquals(PEOPLE[0].getId(), person.getId());
        assertEquals(PEOPLE[0].getUsername(), person.getUsername());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByIDShouldThrowIfMissing() throws OperationNotSupportedException {
        database.findById(PERSON_ID_MISSING);
    }

    @Test
    public void testFindByIFShouldReturnPerson() throws OperationNotSupportedException {
        Person person = database.findById(PEOPLE[0].getId());
        assertEquals(PEOPLE[0].getId(), person.getId());
        assertEquals(PEOPLE[0].getUsername(), person.getUsername());
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddNullShouldThrow() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void testAddElement() throws OperationNotSupportedException {

        int lengthBefore = database.getElements().length;
        database.add(PERSON_FOR_ADDING);
        int lengthAfter = database.getElements().length;

        assertEquals(PERSON_FOR_ADDING, database.getElements()[lengthAfter - 1]);
        assertEquals(lengthBefore + 1, lengthAfter);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveShouldThrowWithEmptyDB() throws OperationNotSupportedException {
        for (int i = 0; i < PEOPLE.length; i++) {
            database.remove();
        }

        database.remove();
    }

    @Test
    public void testRemoveElement() throws OperationNotSupportedException {
        database.remove();
        int lengthAfter = database.getElements().length;
        assertEquals(PEOPLE.length - 1, lengthAfter);
        assertEquals(PEOPLE[PEOPLE.length - 2], database.getElements()[lengthAfter - 1]);

    }


}