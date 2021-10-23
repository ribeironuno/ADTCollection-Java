package collections.implementation;

/**
 * Classe que representa um nó.
 *
 * @param <T> Tipo a ser guardado.
 */
public class NodeDouble<T> {
    /**
     * Elemento a ser guardado.
     */
    private T data;

    /**
     * Node seguinte.
     */
    private NodeDouble<T> next;

    /**
     * Node anterior.
     */
    private NodeDouble<T> prev;

    /**
     * Cria uma instância de um node duplamente ligado.
     *
     * @param next Node seguinte a apontar.
     * @param prev Node anterior a apontar.
     * @param data Elemento a ser guardado.
     */
    public NodeDouble(NodeDouble<T> next, NodeDouble<T> prev, T data) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    /**
     * Criar uma intância de um node duplamente ligado, sem ligações a outros nodes.
     *
     * @param data Elemento a ser guardado.
     */
    public NodeDouble(T data) {
        this(null, null, data);
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
     * Retorna o valor do elemento.
     *
     * @return Valor armazenado.
     */
    public T getData() {
        return this.data;
    }

    /**
     * Atualiza o próximo node para que aponta.
     *
     * @param next Novo node a apontar para o próximo.
     */
    public void setNext(NodeDouble<T> next) {
        this.next = next;
    }

    /**
     * Retorna o próximo node.
     *
     * @return Node seguinte que aponta.
     */
    public NodeDouble<T> getNext() {
        return this.next;
    }

    /**
     * Atualiza o node anterior que aponta.
     *
     * @param prev Novo node para apontar como sendo anterior.
     */
    public void setPrev(NodeDouble<T> prev) {
        this.prev = prev;
    }

    /**
     * Retorna o node anterior.
     *
     * @return Node anterior que aponta.
     */
    public NodeDouble<T> getPrev() {
        return this.prev;
    }

}
