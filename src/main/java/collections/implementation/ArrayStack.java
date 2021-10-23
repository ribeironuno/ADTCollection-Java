package collections.implementation;

import collections.interfaces.IStack;

import java.util.EmptyStackException;

/**
 * Classe que implementa o contrato para uma Stack, com uso a array.
 *
 * @param <T> Tipo a ser guardado pela stack
 */
public class ArrayStack<T> implements IStack<T> {

    /**
     * Valor por defeito para a capacidade inicial da stack
     */
    private static final int DEFAULT_CAPACITY = 100;

    /**
     * Topo da stack;
     */
    private int top;

    /**
     * Stack de elementos.
     */
    private T[] stack;

    /**
     * Cria uma stack com o valor inicial recebido.
     *
     * @param initialCapacity Valor a capacidade.
     */
    @SuppressWarnings("unchecked")
    public ArrayStack(int initialCapacity) {
        this.top = 0;
        this.stack = (T[]) (new Object[initialCapacity]);
    }

    /**
     * Cria uma stack com a capacidade definido por defeito.
     */
    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    private void expandCapacity() {
        //TODO
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
