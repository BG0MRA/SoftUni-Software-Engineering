import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinarySearchTreeTest {
    private BinarySearchTree<Integer> bst;

    @Before
    public void beforeEach() {
        bst = new BinarySearchTree<>(5);
        bst.insert(3);
        bst.insert(7);
        bst.insert(6);
        bst.insert(1);
        bst.insert(17);

    }

    @Test
    public void testCreate() {
        Assert.assertEquals(Integer.valueOf(5), bst.getRoot().getValue());
    }

    @Test
    public void testInsert() {
        Assert.assertEquals(Integer.valueOf(3), bst.getRoot().getLeft().getValue());
        Assert.assertEquals(Integer.valueOf(7), bst.getRoot().getRight().getValue());
        Assert.assertEquals(Integer.valueOf(6), bst.getRoot().getRight().getLeft().getValue());
    }

    @Test
    public void testEachInOrder() {
        List<Integer> elements = new ArrayList<>();

        bst.eachInOrder(e -> elements.add(e));

        List<Integer> expected = new ArrayList<>(
                Arrays.asList(1, 3, 5, 6, 7, 17)
        );


        Assert.assertEquals(expected.size(), elements.size());
        for (int i = 0; i < expected.size(); i++) {
            Assert.assertEquals(expected.get(i), elements.get(i));
        }

    }

    @Test
    public void testContainsTrue() {
        Assert.assertTrue(bst.contains(6));

        Assert.assertFalse(bst.contains(8));

    }

    @Test
    public void testSearchTrue() {
        BinarySearchTree<Integer> search = bst.search(7);

        bst.insert(8);

        Assert.assertEquals(Integer.valueOf(7), search.getRoot().getValue());
        Assert.assertEquals(Integer.valueOf(6), search.getRoot().getLeft().getValue());
        Assert.assertEquals(Integer.valueOf(17), search.getRoot().getRight().getValue());

        Assert.assertTrue(bst.contains(8));
        Assert.assertFalse(search.contains(8));
    }

    @Test
    public void testSearchFalse() {
        Assert.assertNull(bst.search(8));
    }

    @Test
    public void testRange() {
        List<Integer> range = bst.range(3, 7);
        // 3 5 6 7
        List<Integer> expected = Arrays.asList(3, 5, 6, 7);

        Assert.assertEquals(4, range.size());

        for (Integer value : range) {
            Assert.assertTrue(expected.contains(value));
        }
    }

    @Test
    public void testDeleteMin() {
        Assert.assertTrue(bst.contains(1));
        bst.deleteMin();
        Assert.assertFalse(bst.contains(1));
    }

    @Test
    public void testDeleteMax() {
        Assert.assertTrue(bst.contains(17));
        bst.deleteMax();
        Assert.assertFalse(bst.contains(17));
    }

    @Test
    public void testCount() {
        Assert.assertEquals(6, bst.count());
    }

    @Test
    public void testCountAfterInsert() {
        bst.insert(11);
        Assert.assertEquals(7, bst.count());
    }

    @Test
    public void testCountAfterDeleteMin() {
        bst.deleteMin();
        Assert.assertEquals(5, bst.count());
    }

    @Test
    public void testCountAfterDeleteMax() {
        bst.deleteMax();
        Assert.assertEquals(5, bst.count());
    }

    @Test
    public void testRank() {
        Assert.assertEquals(4, bst.rank(7));
    }

    @Test
    public void testFloor() {
        Assert.assertEquals(Integer.valueOf(6), bst.floor(7));
    }

    @Test
    public void testEmptyFloor() {
        Assert.assertNull(bst.floor(-1));
    }

    @Test
    public void testCeil() {
        Assert.assertEquals(Integer.valueOf(7), bst.ceil(6));
    }

    @Test
    public void testEmptyCeil() {
        Assert.assertNull(bst.ceil(20));
    }


}