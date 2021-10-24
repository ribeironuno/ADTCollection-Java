package collections.implementation;

import collections.interfaces.IList;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/**
 * Classe que irá funcionar como uma lista ligada.
 *
 * @param <T> Tipo a ser suportado pela lista.
 */
public class MyLinkedList<T> implements IList<T> {

    /**
     * Primeiro node.
     */
    private Node<T> head;

    /**
     * Ultimo node.
     */
    private Node<T> tail;

    /**
     * Número atual de elementos na lista.
     */
    private int size;

    /**
     * Cria uma instância de lista ligada.
     */
    public MyLinkedList() {
        this.head = this.tail = null;
        this.size = 0;
    }

    /**
     * Insere o elemento no fim.
     *
     * @param elem Elemento a ser inserido.
     */
    private void addLast(T elem) {
        this.tail.setNext(new Node<T>(null, elem));
        this.tail = this.tail.getNext();
        this.size++;
    }

    /**
     * Adiciona um elemeno no inicio da lista.
     * @param elem Elemento a ser adicionado
     */
    public void addFirst(T elem) {
        if (this.isEmpty()) {
            this.head = this.tail = new Node<T>(elem);
            this.size++;
        } else {
            this.head = new Node<>(this.head, elem);
        }
    }

    @Override
    public void add(T elem) throws NullPointerException {
        if (elem == null)
            throw new NullPointerException("Linked list does not support null elements");

        if (this.isEmpty()) { //Verifica se a lista está vazia e coloca no node head
            this.head = this.tail = new Node<T>(elem);
            this.size++;
        } else { //Caso a lista não esteja limpa, o node é inserido no fim
            this.addLast(elem);
        }
    }

    /**
     * Remove o primeiro node.
     */
    private void removeFirst() {
        if (this.size == 1) {
            this.clear();
        } else {
            this.head = this.head.getNext();
            this.size--;
        }
    }

    @Override
    public boolean remove(T elem) throws NoSuchElementException {
        if (this.size == 0) //Caso a lista esteja vazia
            throw new NoSuchElementException("Operation REMOVE failed: list is empty!");

        if (this.head.getData().equals(elem)) { //Caso o node seja o node head
            this.removeFirst();
        } else {
            Node<T> current = this.head;
            while (current.getNext() != null) { //Percorre a lista toda
                if (current.getNext().getData().equals(elem)) { //Caso o node apontado seja igual
                    if (current.getNext().getNext() == null) { //Caso o node apontado seja o ultimo
                        current.setNext(null);
                    } else {
                        current.setNext(current.getNext().getNext());
                    }
                    this.size--;
                    return true;
                }
                current = current.getNext(); //Atualiza o node, para percorrer a lista
            }
        }
        return false;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException, EmptyStackException {
        if (this.isEmpty())
            throw new EmptyStackException();

        if (index < 0 || index > this.size - 1)
            throw new IndexOutOfBoundsException("Index invalid");

        Node<T> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
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