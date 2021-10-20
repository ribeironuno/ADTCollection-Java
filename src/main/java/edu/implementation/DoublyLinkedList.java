package edu.implementation;

import java.util.Arrays;

/**
 * Classe que irá funcionar como uma LinkedList duplamente ligada.
 *
 * @param <T> Tipo a ser armazenado pelos nodes da LinkedList.
 */
public class DoublyLinkedList<T> {

    /**
     * Primeiro node da lista.
     */
    private Node<T> head;

    /**
     * ùltimo node da lista.
     */
    private Node<T> tail;

    /**
     * Tamanho de nodes na lista.
     */
    private int size;

    /**
     * Classe static inner que irá ser a abstração dos node da lista.
     *
     * @param <T> Tipo a ser armazenado.
     * @see "https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html"
     */
    private static class Node<T> {

        /**
         * Próximo node da lista.
         */
        private Node<T> next;

        /**
         * Node anterior.
         */
        private Node<T> prev;

        /**
         * Referência para a informação armazenada.
         */
        private final T data;

        /**
         * Cria uma instância de um node com ligação a outra instãncia de node e guarda a referência para a instância do tipo {@link T}.
         *
         * @param next Node que a instância aponta.
         * @param data Informação a ser guardada.
         */
        Node(Node<T> next, Node<T> prev, T data) {
            this.next = next;
            this.prev = prev;
            this.data = data;
        }

        /**
         * Cria uma instância de um node, apenas guarda a referência para a instância do tipo {@link T} e não aponta nem é apontado.
         *
         * @param data Informação a ser guardada.
         */
        Node(T data) {
            this(null, null, data);
        }
    }

    public DoublyLinkedList() {
        this.head = this.tail = null;
        this.size = 0;
    }

    /**
     * Adiciona o primeiro elemento da lista.
     *
     * @param elem Informação a ser guardada.
     */
    private void addInEmptyList(T elem) {
        this.head = this.tail = new Node<T>(elem);
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
            Node<T> node = new Node<>(null, this.tail, elem);
            this.tail.next = node; //O ultimo node aponta para o novo node
            this.tail = node; //O ultimo node passa a ser o node criado
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
            Node<T> node = new Node<>(this.head, null, elem);
            this.head.prev = node; //A atual head coloca o prev a apontar para o novo node
            this.head = node; //A head é atualizada para o novo node
            this.size++;
        }
    }

    /**
     * Verifica se a lsita está vazia.
     *
     * @return Retorna true caso a lista esteja vazia, false caso contrário.
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Limpa a lista.
     */
    private void clearList() {
        this.head = this.tail = null;
        this.size = 0;
    }

    /**
     * Remove o primeiro elemento da lista.
     */
    public void removeFirst() {
        if (this.size == 0) {
            System.out.println("Operation of REMOVE failed: list is empty");
        } else if (this.size == 1) { //Caso haja apenas um elemento limpa a lista
            this.clearList();
        } else {
            this.head = this.head.next; //A head atual passa a ser o 2º node
            this.head.prev = null;
            this.size--;
        }
    }

    /**
     * Remove o último elemento da lista.
     */
    public void removeLast() {
        if (this.size <= 1) { //Caso a lista nao tenha mais que 1 elemento.
            this.removeFirst();
        } else {
            this.tail = this.tail.prev; //O final passa a ser o penultimo node.
            this.tail.next = null;
            this.size--;
        }
    }

    /**
     * Remove um elemento da lista
     *
     * @param elem Elemento a ser eliminado
     * @return Retorno false caso não seja encontrado o elemento na lista, retorno true caso contrário.
     */
    public boolean remove(T elem) {
        boolean flag = false;
        if (this.size != 0 && elem != null) {
            if (this.head.data.equals(elem)) { //Caso o node a ser eliminado seja o node head
                this.removeFirst();
                flag = true;
            } else if (this.tail.data.equals(elem)) {
                this.removeLast();
                flag = true;
            } else {
                Node<T> node = this.head;
                while (!flag && node.next != null) { //Percorre a lista toda, pára no node null ou quando for encontrado o node
                    if (node.next.data.equals(elem)) { //Caso o node apontado seja igual
                        node.next = node.next.next;
                        flag = true;
                        this.size--;
                    }
                    node = node.next; //Atualiza o node, para percorrer a lista
                }
            }
        }
        return flag;
    }

    /**
     * Verifica se as posições são válidas.
     *
     * @param firstPos Primeira posição.
     * @param lastPos  Ultima posição.
     * @return True se forem válidas, falso caso contrário.
     */
    private boolean positionsAreValid(int firstPos, int lastPos) {
        return firstPos >= 0 && lastPos >= 0 && firstPos < lastPos && firstPos <= this.size;
    }

    /**
     * Método que retorna um array com os elementos entre duas posições da lista ligada.
     *
     * @param firstPos Primeiro limite
     * @param lastPos  Ultimo limite
     * @return Retorna um array com os elementos dentro dos limites, caso não haja elementos ou os limites estejam mal definidos,
     * o retorno será um array null
     */
    public Object[] toArray(int firstPos, int lastPos) {
        if (this.positionsAreValid(firstPos, lastPos)) { //Verifica se as posições são validas
            //T[] arr = (T[]) java.lang.reflect.Array.newInstance(this.head.data.getClass(), this.size); -> Método usado para ser possível depois fazer o cast para o tipo T
            Object[] arr = new Object[this.size];
            int arrayCount = 0;
            int actualPos = 0;

            Node<T> node = this.head;
            while (node != null) {
                if (actualPos >= firstPos && actualPos <= lastPos) {
                    arr[arrayCount++] = node.data;
                }
                node = node.next;
                actualPos++;
            }
            return Arrays.copyOfRange(arr, 0, arrayCount);
        } else {
            return new Object[0];
        }
    }

    /**
     * Método que retorna os elementos da lista em um array.
     *
     * @return Retorna um array com os elementos todos, caso não haja elementos o array é vazio
     */
    public Object[] toArray() {
        return this.toArray(0, this.size);
    }

    /**
     * Método que retorna os elementos até uma dada posição da lista no formato de array.
     *
     * @param untilPos Posição até onde são lidos os elementos.
     * @return Retorna um array com os elementos dentro dos limites, ou retorna um array vazio caso não haja elementos, ou
     * o limite tenha sido mal definido.
     */
    public Object[] toArrayUntil(int untilPos) {
        return this.toArray(0, untilPos);
    }

    /**
     * Método que retorna os elementos após uma dada posição da lista no formato de array.
     *
     * @param afterPos Posição a partir de onde são lidos os elementos.
     * @return Retorna um array com os elementos dentro dos limites, ou retorna um array vazio caso não haja elementos, ou
     * o limite tenha sido mal definido.
     */
    public Object[] toArrayAfter(int afterPos) {
        return this.toArray(afterPos, this.size);
    }

    /**
     * Retorna uma lista de inteiros pares, caso estes existam na instância.
     *
     * @return Lista de inteiros pares caso existam, ou retorno null caso contrário.
     */
    @SuppressWarnings("unchecked")
    public DoublyLinkedList<Integer> getDoublyLinkedList() {
        if (this.head != null && this.head.data instanceof Integer) { //Verifica se a lista está vazia e se o elemento que a lista guarda é instancia de Integer.
            DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
            Node<Integer> node = (Node<Integer>) this.head;
            while (node != null) {
                if (node.data % 2 == 0) {
                    list.addLast(node.data);
                }
                node = node.next;
            }
            return list;
        }
        return null;
    }

    /**
     * Imprime todos os elementos da lista.
     */
    public void print() {
        if (this.size == 0) {
            System.out.println("The list is empty");
        } else {
            Node<T> node = this.head;
            while (node != null) {
                System.out.println(node.data.toString());
                node = node.next;
            }
        }
    }
}
