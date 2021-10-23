package collections.implementation;

/**
 * Classe que representa um nó singular.
 *
 * @param <T> Tipo a ser guardado.
 */
public class Node<T> {

    /**
     * Próximo node da lista.
     */
    private Node<T> next;

    /**
     * Referência para a informação armazenada.
     */
    private T data;

    /**
     * Cria uma instância de um node com ligação a outra instãncia de node e guarda a referência para a instância do tipo {@link T}.
     *
     * @param next Node que a instância aponta.
     * @param data Informação a ser guardada.
     */
    Node(Node<T> next, T data) {
        this.next = next;
        this.data = data;
    }

    /**
     * Cria uma instância de um node, apenas guarda a referência para a instância do tipo {@link T} e aponta para null;
     *
     * @param data Informação a ser guardada.
     */
    Node(T data) {
        this(null, data);
    }

    /**
     * Atualiza os dados do node.
     *
     * @param data Novo dado.
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Atualiza o próximo node para que aponta.
     *
     * @param next Novo node a apontar para o próximo.
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

    /**
     * Retorna o valor do elemento.
     *
     * @return Valor armazenado.
     */
    public T getData() {
        return this.data;
    }

    /**
     * Retorna o próximo node.
     *
     * @return Node seguinte que aponta.
     */
    public Node<T> getNext() {
        return this.next;
    }
}
