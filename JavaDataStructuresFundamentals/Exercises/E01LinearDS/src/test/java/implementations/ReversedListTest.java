package implementations;

import org.junit.Assert;
import org.junit.Test;

public class ReversedListTest {
    ReversedList<Integer> list = new ReversedList<>();

    @Test
    public void add() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
    }

    @Test
    public void size() {
    }

    @Test
    public void capacity() {
    }

    @Test
    public void get() {
        list.add(11);
        list.add(12);
        list.add(13);
        list.add(14);

        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));

    }

    @Test
    public void removeAt() {
        list.add(11);
        list.add(12);
        list.add(13);
        list.add(14);
        list.add(15);

        list.removeAt(2);
        System.out.println(list.get(0));
    }

    @Test
    public void iterator() {
    }
}