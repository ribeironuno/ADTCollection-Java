package collections.implementation;

import collections.interfaces.IStack;

import java.util.EmptyStackException;

/**
 * Class that implements stack contract
 *
 * @param <T> Type being stored
 */
public class ArrayStack<T> implements IStack<T> {

    /**
     * Default value for array
     */
    private static final int DEFAULT_CAPACITY = 100;

    /**
     * Top of stack
     */
    private int top;

    /**
     * Collection of stack
     */
    private T[] stack;

    /**
     * Creates an array stack
     *
     * @param initialCapacity Value for array.
     */
    @SuppressWarnings("unchecked")
    public ArrayStack(int initialCapacity) {
        this.top = 0;
        this.stack = (T[]) (new Object[initialCapacity]);
    }

    /**
     * Creates an array stack with default value capacity
     */
    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * "Expands" the array in 1.5 times
     */
    @SuppressWarnings("unchecked")
    private void expandCapacity() {
        T[] tmp = (T[]) (new Object[(int) (this.top * 1.5)]);
        for (int i = 0; i < this.top; i++) {
            tmp[i] = this.stack[i];
        }
        this.stack = tmp;
    }

    @Override
    public void push(T element) {
        if (this.size() == top)
            this.expandCapacity();

        this.stack[top++] = element;
    }

    @Override
    public T pop() throws EmptyStackException {
        if (this.isEmpty())
            throw new EmptyStackException();

        this.top--;
        T result = this.stack[this.top];
        this.stack[top] = null;
        return result;
    }

    @Override
    public T peek() throws EmptyStackException {
        if (this.isEmpty())
            throw new EmptyStackException();

        return this.stack[top];
    }

    @Override
    public boolean isEmpty() {
        return this.top == 0;
    }

    @Override
    public int size() {
        return this.top;
    }
}
