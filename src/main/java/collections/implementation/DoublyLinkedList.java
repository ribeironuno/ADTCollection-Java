package collections.implementation;

import collections.interfaces.IList;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/**
 * Classe que irá funcionar como uma LinkedList duplamente ligada.
 *
 * @param <T> Tipo a ser armazenado pelos nodes da LinkedList.
 */
public class DoublyLinkedList<T> implements IList<T> {

    /**
     * Primeiro node da lista.
     */
    private NodeDouble<T> head;

    /**
     * ùltimo node da lista.
     */
    private NodeDouble<T> tail;

    /**
     * Tamanho da lista.
     */
    private int size;

    public DoublyLinkedList() {
        this.head = this.tail = null;
        this.size = 0;
    }

    /**
     * Adiciona um elemento à lista.
     *
     * @param elem Elemento a ser adicionado.
     */
    @Override
    public void add(T elem) {
        this.addLast(elem);
    }

    /**
     * Adiciona o primeiro elemento da lista.
     *
     * @param elem Informação a ser guardada.
     */
    private void addInEmptyList(T elem) {
        this.head = this.tail = new NodeDouble<T>(elem);
        this.size++;
    }

    /**
     * Adiciona um elemento ao fim da lista.
     *
     * @param elem Elemento a ser adicionado.
     */
    public void addLast(T elem) {
        if (this.size == 0) { //Caso a lista esteja vazia
            this.addInEmptyList(elem);
        } else {
            NodeDouble<T> newNode = new NodeDouble<>(null, this.tail, elem); //Criamos o node em que o prox aponta para null e o prev aponta para a tail atual
            this.tail.setNext(newNode); //A tail aponta para o novo node
            this.tail = newNode; //A tail passa a ser o node criado
            this.size++;
        }
    }

    /**
     * Adiciona um elemento no inicio da lista.
     *
     * @param elem Elemento a ser adicionado.
     */
    public void addFirst(T elem) {
        if (this.size == 0) { //Caso a lista esteja vazia
            this.addInEmptyList(elem);
        } else {
            NodeDouble<T> newNode = new NodeDouble<>(this.head, null, elem); //Cria um novo node onde o next é a head atual e o prev é null
            this.head.setPrev(newNode); //O prev da atual head aponta para o novo node
            this.head = newNode; //A head é atualizada para o novo node
            this.size++;
        }
    }

    /**
     * Verifica se a lista está vazia.
     *
     * @return Retorna true caso a lista esteja vazia, false caso contrário.
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Limpa a lista.
     */
    @Override
    public void clear() {
        this.head = this.tail = null;
        this.size = 0;
    }

    /**
     * Remove o primeiro elemento da lista.
     *
     * @throws NoSuchElementException Lança a exceção caso a lista esteja vazia.
     */
    public void removeFirst() throws NoSuchElementException {
        if (this.size == 0) {
            throw new NoSuchElementException("Empty list");
        } else if (this.size == 1) { //Caso haja apenas um elemento, limpa a lista.
            this.clear();
        } else {
            this.head = this.head.getNext(); //A head atual passa a ser o 2º node.
            this.head.setPrev(null);
            this.size--;
        }
    }

    /**
     * Remove o último elemento da lista.
     *
     * @throws NoSuchElementException Lança a exceção caso a lista esteja vazia.
     */
    public void removeLast() throws NoSuchElementException {
        if (this.size <= 1) { //Caso a lista esteja vazia ou tenha apenas um elemento.
            this.removeFirst();
        } else {
            this.tail = this.tail.getPrev(); //A tail passa a ser o penultimo node.
            this.tail.setNext(null);
            this.size--;
        }
    }

    /**
     * Remove um nó que não é o node do inicio nem o nó do fim.
     *
     * @param node Nó a remover.
     * @implNote Node recebido não é o head nem o tail
     */
    private void removeNode(NodeDouble<T> node) {
        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
        this.size--;
    }

    /**
     * Remove um elemento da lista caso este exista.
     *
     * @param elem Elemento a ser eliminado
     * @return Retorno false caso não seja encontrado o elemento na lista, retorno true caso contrário.
     * @throws NoSuchElementException Lança exceção caso a lista esteja vazia.
     */
    public boolean remove(T elem) throws NoSuchElementException {
        boolean flag = false;
        if (this.size == 0) {
            throw new NoSuchElementException("Operation REMOVE failed: list is empty!");
        }
        if (elem != null) {
            if (this.head.getData().equals(elem)) { //Caso o node a ser eliminado seja o node head
                this.removeFirst();
                flag = true;
            } else if (this.tail.getData().equals(elem)) { //Caso o node seja o node tail.
                this.removeLast();
                flag = true;
            } else {
                NodeDouble<T> current = this.head;
                while (!flag && current.getNext() != null) { //Percorre a lista toda, pára quando o node apontado é null ou quando for encontrado o node.
                    if (current.getNext().getData().equals(elem)) { //Caso o node apontado seja o node pretendido.
                        this.removeNode(current.getNext());
                        flag = true;
                    }
                    current = current.getNext(); //Atualiza o node, para percorrer a lista
                }
            }
        }
        return flag;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException, EmptyStackException {
        if (this.isEmpty())
            throw new EmptyStackException();

        if (index < 0 || index > this.size)
            throw new IndexOutOfBoundsException("Index invalid");

        NodeDouble<T> current = this.head;
        for (int i = 0; i <= index; i++) {
            current = current.getNext();
        }
        return current.getData();
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
