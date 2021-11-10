package collections.implementation;

import collections.interfaces.ListADT;
import jdk.jfr.Description;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class LinkedList<T> implements ListADT<T> {

    /**
     * Node head.
     */
    protected Node<T> head;

    /**
     * Node tail.
     */
    protected Node<T> tail;

    /**
     * Size of list.
     */
    protected int size;


    /**
     * Variable to check modifications done in collection. This counter will increase when an operation of add or removed occurs.
     */
    protected int modCount;

    public LinkedList() {
        this.head = this.tail = new Node<>(null);
        this.size = 0;
    }

    /**
     * Clears list.
     */
    private void clear() {
        this.head = this.tail = new Node<>(null);
        this.size = 0;
    }

    @Override
    public T removeFirst() throws NoSuchElementException {
        if (this.isEmpty())
            throw new NoSuchElementException("List is empty");

        T result = this.head.getData();
        if (this.size == 1) {
            this.clear();
        } else {
            this.head = this.head.getNext();
            this.size--;
        }
        this.modCount++;
        return result;
    }

    @Override
    public T removeLast() throws NoSuchElementException {
        if (this.isEmpty())
            throw new NoSuchElementException("List is empty");

        T result = this.tail.getData();
        if (this.size == 1) {
            this.clear();
        } else {
            Node<T> current = this.head;
            while (current.getNext().getNext() != null)
                current = current.getNext();

            this.tail = current;
            this.tail.setNext(null);
            this.size--;
        }
        this.modCount++;
        return result;
    }

    @Override
    public T remove(T targetElement) throws NoSuchElementException {
        if (this.isEmpty())
            throw new NoSuchElementException("List is empty");

        boolean found = false;
        Node<T> previous = null;
        Node<T> current = this.head;

        while (current != null && !found) {
            if (current.getData().equals(targetElement)) {
                found = true;
            } else {
                previous = current;
                current = current.getNext();
            }
        }
        if (!found)
            throw new NoSuchElementException("Element not found!");

        if (this.size == 1) {
            this.clear();
        } else if (current.equals(head)) {
            this.head = this.head.getNext();
        } else if (current.equals(tail)) {
            this.tail = previous;
            this.tail.setNext(null);
        } else {
            previous.setNext(current.getNext());
        }
        this.size--;
        this.modCount++;
        return current.getData();
    }

    @Override
    public T first() throws NoSuchElementException {
        if (this.isEmpty())
            throw new NoSuchElementException("List is empty");

        return this.head.getData();
    }

    @Override
    public T last() throws NoSuchElementException {
        if (this.isEmpty())
            throw new NoSuchElementException("List is empty");

        return this.tail.getData();
    }

    @Override
    public boolean contains(T target) throws NoSuchElementException {
        if (this.isEmpty())
            throw new NoSuchElementException("List is empty");

        boolean found = false;
        Node<T> current = this.head;
        while (!found && current != null) {
            if (current.getData().equals(target))
                found = true;
            current = current.getNext();
        }
        return found;
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

    /**
     * Unlink a node THAT IS NOT HEAD OR TAIL. (WARNING)
     *
     * @param node node to unlink.
     */
    private void unlink(Node<T> node) {
        Node<T> current = this.head;

        while (current.getNext() != node)
            current = current.getNext();

        current.setNext(current.getNext().getNext());
        this.size--;
        this.modCount++;
    }

    private class LinkedIterator implements Iterator<T> {

        /**
         * Reference to next element to be returned by subsequent call to next.
         */
        private Node<T> cursor;

        /**
         * Reference to last element returned by next.
         */
        private Node<T> lastReturned;

        /**
         * Counter to operations of next.
         */
        private int counter;

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

        LinkedIterator() {
            this.cursor = null;
            this.lastReturned = null;
            this.expectedMod = modCount;
            this.okToRemove = false;
        }

        @Override
        public boolean hasNext() {
            if (modificationsOccurred(this.expectedMod))
                throw new ConcurrentModificationException();

            return this.counter < size;
        }

        @Override
        public T next() {
            if (modificationsOccurred(this.expectedMod))
                throw new ConcurrentModificationException("Changes occurred in collection");

            if (!this.hasNext())
                throw new NoSuchElementException("There is no more elements to iterate");

            if (this.counter == 0)
                this.cursor = head;
            else
                this.cursor = this.cursor.getNext();

            this.okToRemove = true;
            this.lastReturned = this.cursor;

            this.counter++;
            return this.lastReturned.getData();
        }

        @Override
        public void remove() {
            if (modificationsOccurred(this.expectedMod))
                throw new ConcurrentModificationException("Changes occurred in list");

            if (this.okToRemove) {
                Node<T> lastNext = this.lastReturned.getNext();
                if (this.lastReturned.equals(head)) {
                    LinkedList.this.removeFirst();
                } else if (this.lastReturned.equals(tail)) {
                    LinkedList.this.removeLast();
                } else {
                    LinkedList.this.unlink(this.lastReturned);
                }

                if (this.cursor.equals(this.lastReturned))
                    this.cursor = lastNext;
                else
                    this.counter--;

                this.expectedMod = modCount; //Because remove action on list change mod count
                this.okToRemove = false;
            } else {
                throw new IllegalStateException("Next has not occurred");
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator();
    }

    @Override
    public String toString() {
        String string = "";
        if (this.size != 0) {
            Node<T> current = this.head;
            while (current != null) {
                string += current.getData().toString() + " ";
                current = current.getNext();
            }
        }
        return string;
    }
}
