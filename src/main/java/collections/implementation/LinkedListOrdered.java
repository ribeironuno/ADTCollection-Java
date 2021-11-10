package collections.implementation;

import collections.exceptions.NotComparableInstance;
import collections.interfaces.OrderedListADT;

public class LinkedListOrdered<T> extends LinkedList<T> implements OrderedListADT<T> {

    @Override
    @SuppressWarnings("unchecked")
    public void add(T element) throws NotComparableInstance {
        if (element == null) {
            throw new NullPointerException("Linked list does not support null elements");
        } else if (!(element instanceof Comparable)) {
            throw new NotComparableInstance("Element must be instance of comparable");
        }

        if (super.size == 0) {
            super.head = super.tail = new Node<>(element);
        } else {
            Node<T> previous = null;
            Node<T> current = super.head;
            boolean found = false;

            while (current != null && !found) {
                if (((Comparable<T>) current.getData()).compareTo(element) > 0) { //Current will set to index that will stay after the new node, if exists.
                    found = true;
                } else {
                    previous = current;
                    current = current.getNext();
                }
            }
            if (found) {
                if (current.equals(this.head)) {
                    super.head = new Node<>(super.head, element);
                } else {
                    Node<T> newNode = new Node<>(previous.getNext(), element);
                    previous.setNext(newNode);
                }
            } else {
                Node<T> newNode = new Node<>(element);
                super.tail.setNext(newNode);
                super.tail = newNode;
            }
        }
        size++;
    }
}
