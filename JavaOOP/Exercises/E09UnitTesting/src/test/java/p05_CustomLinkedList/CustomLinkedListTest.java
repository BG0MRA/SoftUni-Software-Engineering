package E09UnitTesting.src.test.java.p05_CustomLinkedList;

import E09UnitTesting.src.main.java.p05_CustomLinkedList.CustomLinkedList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomLinkedListTest {
    private CustomLinkedList<String> list;
    private static final String FIRST_ELEMENT = "Pesho";
    private static final String SECOND_ELEMENT = "Gosho";
    private static final String THIRD_ELEMENT = "Tosho";
    private static final String ADDITIONAL_ELEMENT = "Andrei";

    @Before
    public void prepare() {
        list = new CustomLinkedList<>();
        list.add(FIRST_ELEMENT);
        list.add(SECOND_ELEMENT);
        list.add(THIRD_ELEMENT);
    }

    @Test
    public void testAddShouldAdd() {
        int previousSize = list.getCount();
        list.add(ADDITIONAL_ELEMENT);
        int currentSize = list.getCount();

        assertEquals(previousSize + 1, currentSize);
        assertEquals(list.getCount() - 1, list.indexOf(ADDITIONAL_ELEMENT));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetShouldThrowWithNegativeIndex() {
        list.get(-2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetShouldThrowWithBigIndex() {
        list.get(list.getCount() + 1);
    }

    @Test
    public void testGetShouldGet() {
        assertEquals(SECOND_ELEMENT, list.get(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetShouldThrowWithNegativeIndex() {
        list.set(-2, ADDITIONAL_ELEMENT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetShouldThrowWithBigIndex() {
        list.set(list.getCount() + 1, ADDITIONAL_ELEMENT);
    }

    @Test
    public void testSetShouldSet() {
        list.set(1, ADDITIONAL_ELEMENT);
        assertEquals(ADDITIONAL_ELEMENT, list.get(1));
    }

    @Test
    public void testIndexOfShouldFindIndex() {
        assertEquals(1, list.indexOf(SECOND_ELEMENT));
    }

    @Test
    public void testIndexOfShouldNotFindIndex() {
        assertEquals(-1, list.indexOf(ADDITIONAL_ELEMENT));
    }

    @Test
    public void testContainsShouldReturnTrue() {
        assertTrue(list.contains(SECOND_ELEMENT));
    }

    @Test
    public void testContainsShouldReturnFalse() {
        assertFalse(list.contains(ADDITIONAL_ELEMENT));
    }

    @Test
    public void testRemoveWithMissingElement() {
        assertEquals(-1, list.remove(ADDITIONAL_ELEMENT));
    }

    @Test
    public void testRemoveShouldRemoveElement() {
        int countBeforeRemove = list.getCount();
        assertEquals(1, list.remove(SECOND_ELEMENT));
        int countAfterRemove = list.getCount();
        assertEquals(countBeforeRemove - 1, countAfterRemove);
        assertEquals(-1, list.indexOf(SECOND_ELEMENT));
    }

    @Test
    public void testRemoveAtIndexShouldRemove() {
        int countBeforeRemove = list.getCount();
        assertEquals(SECOND_ELEMENT, list.removeAt(1));
        int countAfterRemove = list.getCount();
        assertEquals(countBeforeRemove - 1, countAfterRemove);
//        assertFalse(list.contains("Gosho"));
    }
}