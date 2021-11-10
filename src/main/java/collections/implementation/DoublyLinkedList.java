package collections.implementation;

import collections.interfaces.LinkedListADT;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Class that implement a doubly linked list.
 *
 * @param <T> Type being stored.
 */
public class DoublyLinkedList<T> implements LinkedListADT<T> {

    /**
     * Head node, most recently added node.
     */
    private DoubleNode<T> head;

    /**
     * Tail node, the oldest node.
     */
    private DoubleNode<T> tail;

    /**
     * Size of list.
     */
    private int size;

    public DoublyLinkedList() {
        this.head = this.tail = null;
        this.size = 0;
    }

    @Override
    public void add(T elem) {
        if (this.size == 0) {
            this.head = this.tail = new DoubleNode<T>(elem);
        } else {
            DoubleNode<T> newNode = new DoubleNode<>(this.head, null, elem);
            this.head.setPrev(newNode);
            this.head = newNode;
        }
        this.size++;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void clear() {
        this.head = this.tail = null;
        this.size = 0;
    }

    /**
     * Remove the most recently added element.
     *
     * @throws NoSuchElementException Throws an exception if there is no element.
     */
    public void removeFirst() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Empty list");
        } else if (this.size == 1) {
            this.clear();
        } else {
            this.head = this.head.getNext();
            this.head.setPrev(null);
            this.size--;
        }
    }

    /**
     * Remove the oldest element.
     *
     * @throws NoSuchElementException Throws an exception if there is no element corresponding.
     */
    public void removeLast() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Empty list");
        } else if (this.size == 1) {
            this.clear();
        } else {
            this.tail = this.tail.getPrev(); //A tail passa a ser o penultimo node.
            this.tail.setNext(null);
            this.size--;
        }
    }

    /**
     * Removes one node inside of borders, in other words, removes a node THAT IS NEITHER HEAD NOR TAIL
     *
     * @param node Node to be removed.
     * @implNote ELEMENT RECEIVED CANNOT BELONG TO A NODE THAT IS NEITHER HEAD NOR TAIL
     */
    private void removeNode(DoubleNode<T> node) {
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
        this.size--;
    }

    /**
     * Removes an element from list.
     *
     * @param elem Element to be removed.
     * @return False case element was not found, true if element was removed.
     * @throws NoSuchElementException Throws an exception if list is empty.
     */
    public boolean remove(T elem) throws NoSuchElementException {
        boolean flag = false;
        if (this.isEmpty()) {
            throw new NoSuchElementException("Operation REMOVE failed: list is empty!");
        }
        if (elem != null) {
            if (this.head.getData().equals(elem)) { //if is head node
                this.removeFirst();
                flag = true;
            } else if (this.tail.getData().equals(elem)) { //if is tail node
                this.removeLast();
                flag = true;
            } else {
                DoubleNode<T> current = this.head;
                while (!flag && current.getNext() != null) {
                    if (current.getNext().getData().equals(elem)) {
                        this.removeNode(current.getNext());
                        flag = true;
                    }
                    current = current.getNext();
                }
            }
        }
        return flag;
    }

    @Override
    public int size() {
        return this.size;
    }

    /**
     * Verify if limits are valid.
     *
     * @param firstPos First position
     * @param lastPos  Last position.
     * @return True if all are valid, false otherwise.
     */
    private boolean positionsAreValid(int firstPos, int lastPos) {
        return firstPos >= 0 && lastPos >= 0 && firstPos <= lastPos && firstPos <= this.size;
    }

    /**
     * Returns in array format elements from collection.
     *
     * @param firstPos First position inclusive.
     * @param lastPos  Last position inclusive. In case of the value is greater than the real size, the last value will be the max value.
     * @return Return valid array, null array if limits are out of bounds.
     */
    public Object[] toArray(int firstPos, int lastPos) {
        if (this.positionsAreValid(firstPos, lastPos)) {
            Object[] arr = new Object[this.size];
            int arrayCount = 0;
            int actualPos = 0;

            DoubleNode<T> current = this.head;
            while (current != null) {
                if (actualPos >= firstPos && actualPos <= lastPos) {
                    arr[arrayCount++] = current.getData();
                }
                current = current.getNext();
                actualPos++;
            }
            return Arrays.copyOfRange(arr, 0, arrayCount);
        } else {
            return new Object[0];
        }
    }

    /**
     * Return all methods in array.
     *
     * @return Return array with elements, array empty if there is no elements.
     */
    public Object[] toArray() {
        return this.toArray(0, this.size);
    }

    /**
     * Return elements in array until position.
     *
     * @param untilPos Posistion.
     * @return Return array with elements, array empty if there is no elements.
     */
    public Object[] toArrayUntil(int untilPos) {
        return this.toArray(0, untilPos);
    }

    /**
     * Return elements in array after position.
     *
     * @param afterPos * Return elements in array until position.
     * @return Return array with elements, array empty if there is no elements.
     */
    public Object[] toArrayAfter(int afterPos) {
        return this.toArray(afterPos, this.size);
    }

    /**
     * Remove all elements equals to received element and returns number of occurrences.
     *
     * @param elem Element to be compared
     * @return Return number of changes
     * @throws NoSuchElementException If list is empty
     */
    public int removeAllOf(T elem) throws NoSuchElementException {
        int count = 0;
        DoubleNode<T> current = this.head;
        while (current != null) {
            if (current.getData().equals(elem)) { //Caso o node em questão seja para remover
                if (current.equals(this.head)) { //Caso seja o node head.
                    this.removeFirst();
                } else if (current.equals(this.tail)) { //Caso seja o node tail
                    this.removeLast();
                } else {
                    this.removeNode(current);
                }
                count++;
            }
            current = current.getNext();
        }
        return count;
    }

    @Override
    public String toString() {
        String string = "";
        if (this.size != 0) {
            DoubleNode<T> current = this.head;
            while (current != null) {
                string += current.getData().toString() + " ";
                current = current.getNext();
            }
        }
        return string;
    }
}
