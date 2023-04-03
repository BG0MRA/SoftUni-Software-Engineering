package E09UnitTesting.src.test.java.p04_BubbleSortTest;

import E09UnitTesting.src.main.java.p04_BubbleSortTest.Bubble;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class BubbleTest extends TestCase {

    @Test
    public void testSort() {
        int[] numbers = {3, 1, 5, 2, - 5, -10, 12};
        int[] sortedArray = {-10, -5, 1, 2, 3, 5, 12};
        Bubble.sort(numbers);
        Assert.assertArrayEquals(sortedArray, numbers);
    }

}