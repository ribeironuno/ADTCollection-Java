package collections.implementation;

import collections.interfaces.OrderedListADT;

public class ArrayOrderedList<T extends Comparable<T>> extends ArrayList<T> implements OrderedListADT<T> {

    /**
     * Creates an ArrayOrderedList instance.
     *
     * @param capacity Initial capacity. Is invalid default value will be taken.
     */
    @SuppressWarnings("unchecked")
    public ArrayOrderedList(int capacity) {
        super(capacity);
        super.array = (T[]) new Comparable[capacity]; //Makes super class threats array type Comparable[]
    }

    /**
     * Creates an ArrayOrderedList instance with default value.
     */
    public ArrayOrderedList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Sets the reference of array to another array of double size.
     */
    @SuppressWarnings("unchecked")
    protected void expand() {
        T[] tmp = (T[]) new Comparable[this.array.length * 2];

        for (int i = 0; i < this.size; i++) {
            tmp[i] = this.array[i];
        }
        this.array = tmp;
    }

    @Override
    public void add(T element) {
        if (super.size == super.array.length)
            this.expand();

        int index = -1;

        for (int i = 0; i < super.size && index == -1; i++) { //Will check the element greater more closed and save index.
            if (super.array[i].compareTo(element) > 0)
                index = i;
        }

        if (index == -1) { //If the new element is the biggest on list or list is empty
            super.array[super.size] = element;
        } else {
            for (int i = this.size; i > index; i--)
                super.array[i] = super.array[i - 1];

            super.array[index] = element;
        }
        super.size++;
        super.modCount++;
    }
}
