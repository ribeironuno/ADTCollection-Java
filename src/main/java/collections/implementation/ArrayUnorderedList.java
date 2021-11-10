package collections.implementation;

import collections.interfaces.UnorderedListADT;

import java.util.NoSuchElementException;

public class ArrayUnorderedList<T> extends ArrayList<T> implements UnorderedListADT<T> {

    /**
     * Creates an ArrayUnorderedList instance.
     *
     * @param capacity Initial capacity. Is invalid default value will be taken.
     */
    public ArrayUnorderedList(int capacity) {
        super(capacity);
    }

    /**
     * Creates an ArrayUnorderedList instance with default capacity.
     */
    public ArrayUnorderedList() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void addToFront(T element) {
        if (super.size == this.array.length) {
            super.expand();
        }
        for (int i = super.size; i > 0; i--) {
            super.array[i] = super.array[i - 1];
        }
        super.array[0] = element;
        super.size++;
        super.modCount++;
    }

    @Override
    public void addToRear(T element) {
        if (super.size == this.array.length) {
            this.expand();
        }
        super.array[super.size] = element;
        super.size++;
        super.modCount++;
    }

    @Override
    public void addAfter(T target, T element) throws NoSuchElementException {
        if (super.size == this.array.length) {
            this.expand();
        }
        int index = -1;

        for (int i = 1; i <= this.size && index == -1; i++) { //Will check the index of target element.
            if (super.array[i - 1].equals(target)) {
                index = i;
            }
        }

        if (index == -1) {//If the target does not exist
            throw new NoSuchElementException("Element not found");
        }
        for (int i = this.size; i > index; i--) {
            this.array[i] = this.array[i - 1];
        }
        this.array[index] = element;
        super.size++;
        super.modCount++;
    }
}
