package E09UnitTesting.src.test.java.p03_IteratorTest;

import E09UnitTesting.src.main.java.p03_IteratorTest.ListIterator;
import org.junit.Before;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class ListIteratorTest {

    private ListIterator listIterator;
    private static final String[] NAMES = {"Pesho", "Gosho", "Toshko"};

    @Before
    public void prepare() throws OperationNotSupportedException {
        listIterator = new ListIterator(NAMES);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorShouldTrowWithNull() throws OperationNotSupportedException {
        new ListIterator(null);
    }

    @Test
    public void testConstructorShouldCreate() throws OperationNotSupportedException {
        ListIterator listIterator = new ListIterator(NAMES);
        assertEquals(NAMES[0], listIterator.print());
        listIterator.move();
        assertEquals(NAMES[1], listIterator.print());
        listIterator.move();
        assertEquals(NAMES[2], listIterator.print());
    }

    @Test
    public void testHasNext() {
        assertTrue(listIterator.hasNext()); // true
        listIterator.move(); //"Gosho"
        assertTrue(listIterator.hasNext()); // true
        listIterator.move(); //"Toshko"
        assertFalse(listIterator.hasNext()); // false
    }

    @Test
    public void testMove () {
        assertTrue(listIterator.move());// true
        assertTrue(listIterator.move());// true
        assertFalse(listIterator.move());//false
    }

    @Test(expected = IllegalStateException.class)
    public void testPrintShouldThrowWithEmptyIterator () throws OperationNotSupportedException {
        ListIterator emptyIterator = new ListIterator();
        emptyIterator.print();
    }

    @Test
    public void testPrintShouldReturn ()  {
        assertEquals(NAMES[0], listIterator.print());
        listIterator.move();
        assertEquals(NAMES[1], listIterator.print());
    }

}