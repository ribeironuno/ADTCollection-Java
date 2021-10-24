package collections.implementation;

import collections.interfaces.IStack;

import java.util.EmptyStackException;

/**
 * Class that implements a stack with linked list.
 *
 * @param <T> Type being storage.
 */
public class LinkedStack<T> implements IStack<T> {

    /**
     * Collection of elements.
     */
    private final MyLinkedList<T> list = new MyLinkedList<>();


    @Override
    public void push(T element) {
        this.list.add(element);
    }

    @Override
    public T pop() throws EmptyStackException {
        if (this.isEmpty())
            throw new EmptyStackException();

        T result = this.list.get(this.list.size() - 1);
        this.list.remove(result);
        return result;
    }

    @Override
    public T peek() throws EmptyStackException {
        if (this.isEmpty())
            throw new EmptyStackException();

        return this.list.get(this.list.size() - 1);
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public String toString() {
        if (this.isEmpty())
            return "EMPTY LIST";

        return "Stack : " + this.list.toString();
    }
}