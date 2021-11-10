package collections.implementation;

import collections.interfaces.StackADT;

import java.util.EmptyStackException;

/**
 * Class that implements a linked stack.
 *
 * @param <T> Type being storage.
 */
public class LinkedStack<T> implements StackADT<T> {

    /**
     * Top node.
     */
    private Node<T> top;

    private int size;

    public LinkedStack() {
        this.top = null;
        this.size = 0;
    }


    @Override
    public void push(T element) {
        top = new Node<>(top, element);
        this.size++;
    }

    @Override
    public T pop() throws EmptyStackException {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        T result = this.top.getData();
        top = top.getNext();
        this.size--;
        return result;
    }

    @Override
    public T peek() throws EmptyStackException {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return this.top.getData();
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        if (this.isEmpty()) {
            return "EMPTY LIST";
        }
        Node<T> current = this.top;
        String string = "";
        while (current != null) {
            string += current.getData().toString() + " ";
            current = current.getNext();
        }
        return string;
    }
}