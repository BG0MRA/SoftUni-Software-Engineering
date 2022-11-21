package implementations;

import interfaces.IReversedList;

import java.util.Arrays;
import java.util.Iterator;

public class ReversedList<E> implements IReversedList<E> {
    private final int INITIAL_CAPACITY = 2;
    private Object[] elements;
    private int size;

    public ReversedList() {
        this.elements = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    @Override
    public void add(E element) {
        if (this.size == this.capacity()) {
            grow();
        }
        this.elements[size] = element;
        this.size++;

    }

    private void grow() {
        int newCapacity = this.capacity() * 2; // this.elements.length
        this.elements = Arrays.copyOf(this.elements, newCapacity);


    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int capacity() {
        return this.elements.length;
    }

    @Override
    public E get(int index) {
        if (validIndex(index)) {
            int realIndex = getRealIndex(index);
            return getAt(realIndex);
        } else {
            throw new IndexOutOfBoundsException("Invalid input index");
        }
    }

    private int getRealIndex(int index) {
        int realIndex = this.size - 1 - index;
        return realIndex;
    }

    @SuppressWarnings("unchecked")
    private E getAt(int index) {
        return (E) this.elements[index];
    }

    private boolean validIndex(int index) {
        return (index < this.size) && (index >= 0);
    }

    @Override
    public E removeAt(int index) {
        if (validIndex(index)){
            this.elements[index] = null;
            size--;
            siftLeft(index);
        return null;
        } else {
            throw new IndexOutOfBoundsException("Invalid input index");
        }
    }

    private void siftLeft(int index) {
        for (int i = index; i < elements.length - 1; i++) {
            this.elements[i] = this.elements[i + 1];

        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = size -1;
            @Override
            public boolean hasNext() {
                return index >= 0;
            }

            @Override
            public E next() {
                return getAt(index--);
            }
        };
    }
}