package collections.interfaces;

import java.util.EmptyStackException;

/**
 * Contract for a list.
 *
 * @param <T> Type being store by list.
 */
public interface ListADT<T> {
    /**
     * Adds an element to list.
     *
     * @param elem Element being added.
     * @throws NullPointerException Throws an exception in case the element is null.
     */
    public void add(T elem) throws NullPointerException;

    /**
     * Method to remove an element.
     *
     * @param elem Element being removed.
     * @throws EmptyStackException Throws an exceptions if list is empty.
     */
    public boolean remove(T elem) throws EmptyStackException;

    /**
     * Says size of list.
     *
     * @return Size of list.
     */
    public int size();

    /**
     * Checks if list is empty.
     *
     * @return True if is empty, false otherwise.
     */
    public boolean isEmpty();

    /**
     * Clears list.
     */
    public void clear();
}
