package collections.implementation;

import collections.interfaces.ListADT;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class ArrayList<T> implements ListADT<T> {

    /**
     * Array of elements.
     */
    protected T[] array;

    /**
     * Default capacity of array.
     */
    protected static final int DEFAULT_CAPACITY = 50;

    /**
     * Says the current number of elements in the list.
     */
    protected int size;

    /**
     * Variable to check modifications done in collection. This counter will increase when an operation of add or removed occurs.
     */
    protected int modCount;

    /**
     * Creates an ordered list.
     *
     * @param capacity Initial capacity of list. In case of capacity inserted is invalid, default value will be chosen.
     */
    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        if (capacity <= 0) {
            capacity = DEFAULT_CAPACITY;
        }
        this.array = (T[]) (new Object[capacity]);
        this.size = this.modCount = 0;
    }

    /**
     * Creates an ordered list with default capacity.
     */
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Sets the reference of array to another array of double size.
     */
    @SuppressWarnings("unchecked")
    protected void expand() {
        T[] tmp = (T[]) new Object[this.array.length * 2];

        for (int i = 0; i < this.size; i++) {
            tmp[i] = this.array[i];
        }
        this.array = tmp;
    }

    @Override
    public T removeFirst() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("List is empty!");
        }
        T result = this.array[0];

        for (int i = 0; i < this.size - 1; i++) {
            this.array[i] = this.array[i + 1];
        }
        this.size--;
        this.modCount++;
        return result;
    }

    @Override
    public T removeLast() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("List is empty!");
        }
        T result = this.array[this.size - 1];
        this.size--;
        this.modCount++;
        return result;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("List is empty!");
        }
        int index = this.indexOf(element);

        if (index == -1) {
            throw new NoSuchElementException("Element does not exist!");
        }
        T result = this.array[index];
        for (int i = index; i < this.size - 1; i++) {
            this.array[i] = this.array[i + 1];
        }
        this.size--;
        this.modCount++;
        return result;
    }

    @Override
    public T first() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("List is empty!");
        }
        return this.array[0];
    }

    @Override
    public T last() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("List is empty!");
        }
        return this.array[this.size - 1];
    }

    /**
     * Searches for an element and return the index.
     *
     * @param element Element to be searched.
     * @return Index of element case element was found, -1 otherwise.
     */
    private int indexOf(T element) {
        int index = -1;

        for (int i = 0; i < this.size && index == -1; i++) {
            if (this.array[i].equals(element)) {
                index = i;
            }
        }
        return index;
    }

    @Override
    public boolean contains(T target) throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("List is empty!");
        }
        return this.indexOf(target) != -1;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    /**
     * Checks if modifications occurred.
     *
     * @param expectedMod Mod to compare.
     * @return true if changes was made meanwhile, false otherwise.
     */
    private boolean modificationsOccurred(int expectedMod) {
        return this.modCount != expectedMod;
    }

    private class BasicIterator implements Iterator<T> {

        /**
         * Index of element returned by most recent call to next or
         * previous.  Reset to -1 if this element is deleted by a call
         * to remove.
         */
        private int lastGap;

        /**
         * Index of element to be returned by subsequent call to next.
         */
        private int cursor;

        /**
         * The modCount value that the iterator believes that the backing
         * List should have.  If this expectation is violated, the iterator
         * has detected concurrent modification.
         */
        private int expectedMod;

        /**
         * Flag to check if it's ok to remove.
         */
        private boolean okToRemove;

        BasicIterator() {
            this.cursor = 0;
            this.lastGap = -1;
            this.expectedMod = modCount;
            this.okToRemove = false;
        }

        @Override
        public boolean hasNext() {
            if (modificationsOccurred(this.expectedMod)) {
                throw new ConcurrentModificationException();
            }
            return this.cursor != size;
        }

        @Override
        public T next() {
            if (modificationsOccurred(this.expectedMod)) {
                throw new ConcurrentModificationException();
            }
            if (!this.hasNext()) {
                throw new NoSuchElementException("There is no next");
            }
            this.okToRemove = true;
            this.lastGap = this.cursor;
            this.cursor++;
            return array[this.lastGap];
        }

        @Override
        public void remove() {
            if (modificationsOccurred(this.expectedMod)) {
                throw new ConcurrentModificationException();
            }
            if (this.okToRemove) {
                ArrayList.this.remove(array[this.lastGap]);
                this.cursor = this.lastGap;
                this.expectedMod = modCount; //Because remove action on list change mod count
                this.lastGap = -1;
                this.okToRemove = false;
            } else {
                throw new IllegalStateException("Next has not occurred");
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new BasicIterator();
    }

    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < this.size; i++) {
            string += this.array[i].toString();
        }
        return string;
    }
}
