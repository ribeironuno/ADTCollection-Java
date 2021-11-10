package collections.implementation;

import collections.interfaces.OrderedListADT;

public class LinkedListOrdered<T extends Comparable<T>> extends LinkedList<T> implements OrderedListADT<T> {

    @Override
    public void add(T element) {
        if (element == null)
            throw new NullPointerException("Linked list does not support null elements");

        if (super.size == 0) {
            super.head = super.tail = new Node<>(element);
        } else {
            Node<T> previous = null;
            Node<T> current = super.head;
            boolean found = false;

            while (current != null && !found) {
                if (current.getData().compareTo(element) > 0) { //Current will set to index that will stay after the new node, if exists.
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
