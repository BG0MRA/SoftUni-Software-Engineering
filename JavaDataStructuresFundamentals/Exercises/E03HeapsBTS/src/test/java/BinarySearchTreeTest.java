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

}