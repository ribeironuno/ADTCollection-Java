package collections.implementation;

import collections.interfaces.ListADT;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/**
 * Class that will implement contract of IList with focus on being a LINKED LIST.
 *
 * @param <T> Type being store in list.
 */
public class MyLinkedList<T> implements ListADT<T> {

    /**
     * First node, most recently node added always.
     */
    private Node<T> head;

    /**
     * Number of elements.
     */
    private int size;

    /**
     * Creates an instance of linked list.
     */
    public MyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Add an element in last node.
     * ---------CURRENTLY NOT USED---------
     *
     * @param elem Element to be added.
     */
    private void addLast(T elem) {
        Node<T> current = this.head;
        while (current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(new Node<T>(null, elem));
    }

    @Override
    public void add(T elem) throws NullPointerException {
        if (elem == null)
            throw new NullPointerException("Linked list does not support null elements");

        if (this.isEmpty()) {
            this.head = new Node<T>(elem);
        } else {
            this.head = new Node<>(this.head, elem);
        }
        this.size++;
    }

    /**
     * Removes head node.
     */
    private void removeHead() {
        if (this.size == 1) {
            this.clear();
        } else {
            this.head = this.head.getNext();
            this.size--;
        }
    }

    @Override
    public boolean remove(T elem) throws EmptyStackException {
        if (this.size == 0)
            throw new NoSuchElementException("Operation REMOVE failed: list is empty!");

        if (this.head.getData().equals(elem)) { //If is the head
            this.removeHead();
        } else {
            Node<T> current = this.head;
            while (current.getNext() != null) {
                if (current.getNext().getData().equals(elem)) {
                    if (current.getNext().getNext() == null) {
                        current.setNext(null);
                    } else {
                        current.setNext(current.getNext().getNext());
                    }
                    this.size--;
                    return true;
                }
                current = current.getNext();
            }
        }
        return false;
    }

    @Override
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
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