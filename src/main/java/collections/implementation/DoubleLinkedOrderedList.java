package collections.implementation;

import collections.interfaces.OrderedListADT;

public class DoubleLinkedOrderedList<T extends Comparable<T>> extends DoubleLinkedList<T> implements OrderedListADT<T> {

    @Override
    public void add(T element) {
        if (element == null) {
            throw new NullPointerException("Linked list does not support null elements");
        }
        if (super.size == 0) {
            super.front = super.rear = new DoubleNode<>(element);
        } else {
            DoubleNode<T> current = super.front;
            boolean found = false;

            while (!found && current != null) {
                if (current.getData().compareTo(element) > 0) //Current will set to index that will stay after the new node, if exists.
                    found = true;
                else
                    current = current.getNext();
            }

            if (found) {
                if (current.equals(super.front)) {
                    DoubleNode<T> newNode = new DoubleNode<>(super.front, null, element);
                    super.front.setPrev(newNode);
                    super.front = newNode;
                } else {
                    DoubleNode<T> newNode = new DoubleNode<>(current, current.getPrev(), element);
                    newNode.getPrev().setNext(newNode);
                    newNode.getNext().setPrev(newNode);
                }
            } else {
                DoubleNode<T> newNode = new DoubleNode<>(null, super.rear, element);
                super.rear.setNext(newNode);
                super.rear = newNode;
            }
        }
        super.size++;
        super.modCount++;
    }
}