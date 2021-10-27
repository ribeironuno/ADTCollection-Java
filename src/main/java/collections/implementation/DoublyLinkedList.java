package collections.implementation;

import collections.interfaces.IList;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/**
 * Class that implement a doubly linked list.
 *
 * @param <T> Type being stored.
 */
public class DoublyLinkedList<T> implements IList<T> {

    /**
     * Head node, most recently added node.
     */
    private NodeDouble<T> head;

    /**
     * Tail node, the oldest node.
     */
    private NodeDouble<T> tail;

    /**
     * Size of list.
     */
    private int size;

    public DoublyLinkedList() {
        this.head = this.tail = null;
        this.size = 0;
    }

    @Override
    public void add(T elem) {
        if (this.size == 0) {
            this.head = this.tail = new NodeDouble<T>(elem);
        } else {
            NodeDouble<T> newNode = new NodeDouble<>(this.head, null, elem);
            this.head.setPrev(newNode);
            this.head = newNode;
        }
        this.size++;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void clear() {
        this.head = this.tail = null;
        this.size = 0;
    }

    /**
     * Remove the most recently added element.
     *
     * @throws NoSuchElementException Throws an exception if there is no element.
     */
    public void removeFirst() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Empty list");
        } else if (this.size == 1) {
            this.clear();
        } else {
            this.head = this.head.getNext();
            this.head.setPrev(null);
            this.size--;
        }
    }

    /**
     * Remove the oldest element.
     *
     * @throws NoSuchElementException Throws an exception if there is no element corresponding.
     */
    public void removeLast() throws NoSuchElementException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Empty list");
        } else if (this.size == 1) {
            this.clear();
        } else {
            this.tail = this.tail.getPrev(); //A tail passa a ser o penultimo node.
            this.tail.setNext(null);
            this.size--;
        }
    }

    /**
     * Removes one node inside of borders, in other words, removes a node THAT IS NEITHER HEAD NOR TAIL
     *
     * @param node Node to be removed.
     * @implNote ELEMENT RECEIVED CANNOT BELONG TO A NODE THAT IS NEITHER HEAD NOR TAIL
     */
    private void removeNode(NodeDouble<T> node) {
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
        this.size--;
    }

    /**
     * Removes an element from list.
     *
     * @param elem Element to be removed.
     * @return False case element was not found, true if element was removed.
     * @throws NoSuchElementException Throws an exception if list is empty.
     */
    public boolean remove(T elem) throws NoSuchElementException {
        boolean flag = false;
        if (this.isEmpty())
            throw new NoSuchElementException("Operation REMOVE failed: list is empty!");

        if (elem != null) {
            if (this.head.getData().equals(elem)) { //if is head node
                this.removeFirst();
                flag = true;
            } else if (this.tail.getData().equals(elem)) { //if is tail node
                this.removeLast();
                flag = true;
            } else {
                NodeDouble<T> current = this.head;
                while (!flag && current.getNext() != null) {
                    if (current.getNext().getData().equals(elem)) {
                        this.removeNode(current.getNext());
                        flag = true;
                    }
                    current = current.getNext();
                }
            }
        }
        return flag;
    }

    @Override
    public T getHead() throws IndexOutOfBoundsException, EmptyStackException {
        if (this.isEmpty())
            throw new NoSuchElementException("Operation REMOVE failed: list is empty!");

        return this.head.getData();
    }

    @Override
    public int size() {
        return this.size;
    }

    /**
     * Verifica se os limites para analise são válidas.
     *
     * @param firstPos Primeira posição.
     * @param lastPos  Ultima posição.
     * @return True se forem válidas, falso caso contrário.
     */
    private boolean positionsAreValid(int firstPos, int lastPos) {
        return firstPos >= 0 && lastPos >= 0 && firstPos <= lastPos && firstPos <= this.size;
    }

    /**
     * Método que retorna um array com os elementos entre duas posições, inclusive, da lista ligada
     *
     * @param firstPos Primeiro limite inclusive.
     * @param lastPos  Ultimo limite inclusive. Caso o valor seja maior que o tamanho total da lista, irá ser assumido que o valor pretendido é o tamanho da lista.
     * @return Retorna um array com os elementos dentro dos limites, caso não haja elementos ou os limites estejam mal definidos,
     * o retorno será um array vazio.
     */
    public Object[] toArray(int firstPos, int lastPos) {
        if (this.positionsAreValid(firstPos, lastPos)) { //Verifica se as posições são validas
            //T[] arr = (T[]) java.lang.reflect.Array.newInstance(this.head.data.getClass(), this.size); -> Método usado para ser possível depois fazer o cast para o tipo T
            Object[] arr = new Object[this.size];
            int arrayCount = 0;
            int actualPos = 0;

            NodeDouble<T> current = this.head;
            while (current != null) {
                if (actualPos >= firstPos && actualPos <= lastPos) {
                    arr[arrayCount++] = current.getData();
                }
                current = current.getNext();
                actualPos++;
            }
            return Arrays.copyOfRange(arr, 0, arrayCount);
        } else {
            return new Object[0];
        }
    }

    /**
     * Método que retorna os elementos todos da lista.
     *
     * @return Retorna um array com os elementos todos, caso não haja elementos o array é vazio.
     */
    public Object[] toArray() {
        return this.toArray(0, this.size);
    }

    /**
     * Método que retorna os elementos da lista até uma dada posição inclusive no formato de array.
     *
     * @param untilPos Posição até onde são lidos os elementos, inclusive.
     * @return Retorna um array com os elementos dentro dos limites, ou retorna um array vazio caso não haja elementos, ou
     * o limite tenha sido mal definido.
     */
    public Object[] toArrayUntil(int untilPos) {
        return this.toArray(0, untilPos);
    }

    /**
     * Método que retorna os elementos após uma dada posição da lista no formato de array.
     *
     * @param afterPos Posição do limite, inclusive.
     * @return Retorna um array com os elementos dentro dos limites, ou retorna um array vazio caso não haja elementos, ou
     * o limite tenha sido mal definido.
     */
    public Object[] toArrayAfter(int afterPos) {
        return this.toArray(afterPos, this.size);
    }

    /**
     * Remove todos os elementos iguais ao recebido e retorna o número de interações.
     *
     * @param elem Elemento a ser comparado com os elementos da lista.
     * @return Retorno > 0 caso tenha havido alterações, retorno 0 caso não tenha havio alterações.
     * @throws NoSuchElementException Lança a exceção caso a lista esteja vazia.
     */
    public int removeAllOf(T elem) throws NoSuchElementException {
        int count = 0;
        NodeDouble<T> current = this.head;
        while (current != null) {
            if (current.getData().equals(elem)) { //Caso o node em questão seja para remover
                if (current.equals(this.head)) { //Caso seja o node head.
                    this.removeFirst();
                } else if (current.equals(this.tail)) { //Caso seja o node tail
                    this.removeLast();
                } else {
                    this.removeNode(current);
                }
                count++;
            }
            current = current.getNext();
        }
        return count;
    }

    /**
     * Imprime todos os elementos da lista.
     */
    @Override
    public String toString() {
        String string = "";
        if (this.size != 0) {
            NodeDouble<T> current = this.head;
            while (current != null) {
                string += current.getData().toString() + " ";
                current = current.getNext();
            }
        }
        return string;
    }
}
