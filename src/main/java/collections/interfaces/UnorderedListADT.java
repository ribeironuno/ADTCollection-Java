package collections.interfaces;

import java.util.NoSuchElementException;

public interface UnorderedListADT<T> extends ListADT<T> {

    /**
     * Adds the specific element to the front.
     * @param element Element to be added.
     */
    public void addToFront(T element);

    /**
     * Adds an element to the rear of list.
     * @param element element to be added.
     */
    public void addToRear(T element);

    /**
     * Adds one element after a other given element.
     * @param target Element before the new element.
     * @param element New element to be added.
     * @throws NoSuchElementException case target element does not exist in list.
     */
    public void addAfter(T target, T element) throws NoSuchElementException;
}
