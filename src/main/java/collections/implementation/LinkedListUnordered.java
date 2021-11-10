package collections.implementation;

import collections.interfaces.UnorderedListADT;

import java.util.NoSuchElementException;

public class LinkedListUnordered<T> extends LinkedList<T> implements UnorderedListADT<T> {

    @Override
    public void addToFront(T element) {
        if (super.size == 0) {
            super.head = super.tail = new Node<>(element);
        } else {
            super.head = new Node<>(super.head, element);
        }
        super.size++;
        super.modCount++;
    }

    @Override
    public void addToRear(T element) {
        if (super.size == 0) {
            super.head = super.tail = new Node<>(element);
        } else {
            Node<T> newNode = new Node<>(element);
            super.tail.setNext(newNode);
            super.tail = newNode;
        }
        super.size++;
        super.modCount++;
    }

    @Override
    public void addAfter(T target, T element) throws NoSuchElementException {
        boolean found = false;
        Node<T> current = super.head;

        while (!found && current != null) {
            if (current.getData().equals(target)) {
                found = true;
            } else {
                current = current.getNext();
            }
        }

        if (!found) {
            throw new NoSuchElementException("Target element does not exists");
        } else {
           if (current.equals(super.tail)) {
                this.addToRear(element);
            } else {
                Node<T> newNode = new Node<>(current.getNext(), element);
                current.setNext(newNode);
                super.size++;
                super.modCount++;
            }
        }
    }
}
