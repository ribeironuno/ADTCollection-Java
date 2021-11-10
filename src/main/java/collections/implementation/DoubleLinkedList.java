package collections.implementation;

import collections.interfaces.ListADT;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class DoubleLinkedList<T> implements ListADT<T> {

    /**
     * Front node.
     */
    protected DoubleNode<T> front;

    /**
     * Rear node.
     */
    protected DoubleNode<T> rear;

    /**
     * Size of collection.
     */
    protected int size;

    /**
     * Variable to check modifications done in collection. This counter will increase when an operation of add or removed occurs.
     */
    protected int modCount;


    public void DoublyLinkedList() {
        this.front = this.rear = null;
        this.size = modCount = 0;
    }

    @Override
    public T removeFirst() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        T result = this.front.getData();
        if (this.size == 1) {
            this.front = this.rear = null;
        } else {
            this.front = this.front.getNext();
            this.front.setPrev(null);
        }

        this.size--;
        this.modCount++;
        return result;
    }

    @Override
    public T removeLast() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        T result = this.rear.getData();
        if (this.size == 1) {
            this.front = this.rear = null;
        } else {
            this.rear = this.rear.getPrev();
            this.rear.setNext(null);
        }
        this.size--;
        this.modCount++;
        return result;
    }

    /**
     * Returns node from specific element.
     *
     * @param element element to search.
     * @return Node if element exists, null otherwise.
     */
    protected DoubleNode<T> find(T element) {
        boolean found = false;
        DoubleNode<T> current = this.front;

        if (!this.isEmpty()) {
            while (!found && current != null) {
                if (current.getData().equals(element))
                    found = true;
                else
                    current = current.getNext();
            }
        }
        return found ? current : null;
    }

    @Override
    public T remove(T element) throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        T result;
        DoubleNode<T> node = this.find(element);

        if (node == null) {
            throw new NoSuchElementException("Element not found");
        }
        if (this.front.equals(node)) {
            result = this.removeFirst();
        } else if (this.rear.equals(node)) {
            result = this.removeLast();
        } else {
            result = node.getData();
            node.getPrev().setNext(node.getNext());
            node.getNext().setPrev(node.getPrev());
            this.size--;
            this.modCount++;
        }
        return result;
    }

    @Override
    public T first() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return this.front.getData();
    }

    @Override
    public T last() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return this.rear.getData();
    }

    @Override
    public boolean contains(T target) throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        return this.find(target) != null;
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

    private class DoubleIterator implements Iterator<T> {

        /**
         * Reference to next element to be returned by subsequent call to next.
         */
        private DoubleNode<T> cursor;

        /**
         * Tracks the position on chain.
         */
        private int count;

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

        DoubleIterator() {
            this.cursor = front;
            this.count = 0;
            this.expectedMod = modCount;
            this.okToRemove = false;
        }

        @Override
        public boolean hasNext() {
            return this.count < size;
        }

        @Override
        public T next() {
            if (modificationsOccurred(this.expectedMod)) {
                throw new ConcurrentModificationException("Changes occurred in list");
            }
            if (!hasNext()) {
                throw new NoSuchElementException("There is no more elements to iterate");
            }
            this.okToRemove = true;
            this.count++;
            T result = cursor.getData();
            cursor = cursor.getNext();
            return result;
        }

        @Override
        public void remove() {
            if (modificationsOccurred(this.expectedMod)) {
                throw new ConcurrentModificationException("Changes occurred in list");
            }
            if (this.okToRemove) {
                DoubleNode<T> nodeToRemove = cursor == null ? rear : cursor.getPrev();
                if (nodeToRemove.equals(front)) {
                    DoubleLinkedList.this.removeFirst();
                } else if (nodeToRemove.equals(rear)) {
                    DoubleLinkedList.this.removeLast();
                } else {
                    nodeToRemove.getPrev().setNext(nodeToRemove.getNext());
                    nodeToRemove.getNext().setPrev(nodeToRemove.getPrev());
                    size--;
                    modCount++;
                }
                this.count--;
                this.okToRemove = false;
                expectedMod = modCount;
            } else {
                throw new IllegalStateException("Next has not occurred");
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new DoubleIterator();
    }

    @Override
    public String toString() {
        String string = "";
        if (this.size != 0) {
            DoubleNode<T> current = this.front;
            while (current != null) {
                string += current.getData().toString() + " ";
                current = current.getNext();
            }
        }
        return string;
    }
}
