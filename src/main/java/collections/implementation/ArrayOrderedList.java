package collections.implementation;

import collections.exceptions.NotComparableInstanceException;
import collections.interfaces.OrderedListADT;

public class ArrayOrderedList<T> extends ArrayList<T> implements OrderedListADT<T> {

    /**
     * Creates an ArrayOrderedList instance.
     *
     * @param capacity Initial capacity. Is invalid default value will be taken.
     */
    public ArrayOrderedList(int capacity) {
        super(capacity);
    }

    /**
     * Creates an ArrayOrderedList instance with default value.
     */
    public ArrayOrderedList() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void add(T element) throws NotComparableInstanceException {
        if (!(element instanceof Comparable)) {
            throw new NotComparableInstanceException("Element must be comparable");
        }
        if (super.size == super.array.length) {
            super.expand();
        }

        int index = -1;

        for (int i = 0; i < super.size && index == -1; i++) { //Will check the element greater more closed and save index.
            if (((Comparable<T>) super.array[i]).compareTo(element) > 0) {
                index = i;
            }
        }

        if (index == -1) { //If the new element is the biggest on list or list is empty
            super.array[super.size] = element;
        } else {
            for (int i = this.size; i > index; i--) {
                super.array[i] = super.array[i - 1];
            }
            super.array[index] = element;
        }
        super.size++;
        super.modCount++;
    }
}
