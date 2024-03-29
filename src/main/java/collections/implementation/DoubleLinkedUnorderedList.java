package collections.implementation;

import collections.interfaces.UnorderedListADT;

import java.util.NoSuchElementException;

public class DoubleLinkedUnorderedList<T> extends DoubleLinkedList<T> implements UnorderedListADT<T> {

    @Override
    public void addToFront(T element) {
        if (super.size == 0) {
            super.front = super.rear = new DoubleNode<>(null, null, element);
        } else {
            DoubleNode<T> newNode = new DoubleNode<>(super.front, null, element);
            super.front.setPrev(newNode);
            super.front = newNode;
        }
        super.size++;
        super.modCount++;
    }

    @Override
    public void addToRear(T element) {
        if (super.size == 0) {
            super.front = super.rear = new DoubleNode<>(null, null, element);
        } else {
            DoubleNode<T> newNode = new DoubleNode<>(null, super.rear, element);
            super.rear.setNext(newNode);
            super.rear = newNode;
        }
        super.size++;
        super.modCount++;
    }

    @Override
    public void addAfter(T target, T element) throws NoSuchElementException {
        DoubleNode<T> afterNode = super.find(target);

        if (afterNode != null) {
            if (afterNode.equals(super.rear))
                this.addToRear(element);
            else {
                DoubleNode<T> newNode = new DoubleNode<>(afterNode.getNext(), afterNode, element);
                afterNode.setNext(newNode);
                newNode.getNext().setPrev(newNode);
                super.size++;
                super.modCount++;
            }
        } else {
            throw new NoSuchElementException("Target element not found");
        }
    }
}
