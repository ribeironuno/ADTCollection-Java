package collections.implementation;

/**
 * Class that represents a double node.
 *
 * @param <T> Type being stored.
 */
public class DoubleNode<T> {
    /**
     * Element's data.
     */
    private T data;

    /**
     * Next node.
     */
    private DoubleNode<T> next;

    /**
     * Previous node.
     */
    private DoubleNode<T> prev;

    /**
     * Creates a double node instance.
     *
     * @param next Next node.
     * @param prev Previous node.
     * @param data Element's data.
     */
    public DoubleNode(DoubleNode<T> next, DoubleNode<T> prev, T data) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

    /**
     * Creates a double node instance, with reference of next node and previous node set to null.
     *
     * @param data Element's data.
     */
    public DoubleNode(T data) {
        this(null, null, data);
    }

    /**
     * Update element's data.
     *
     * @param data Element's data.
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Returns element's data.
     *
     * @return Value.
     */
    public T getData() {
        return this.data;
    }

    /**
     * Update next node reference.
     *
     * @param next New reference.
     */
    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }

    /**
     * Return next node.
     *
     * @return Next node.
     */
    public DoubleNode<T> getNext() {
        return this.next;
    }

    /**
     * Update previous node.
     *
     * @param prev Previous node.
     */
    public void setPrev(DoubleNode<T> prev) {
        this.prev = prev;
    }

    /**
     * Returns previous node.
     *
     * @return Previous node.
     */
    public DoubleNode<T> getPrev() {
        return this.prev;
    }

}
