package collections.interfaces;

import collections.exceptions.ElementNotFoundException;
import collections.exceptions.NotComparableInstanceException;

/**
 * BinarySearchTreeADT defines the interface to a binary search tree.
 */
public interface BinarySearchTreeADT<T> extends BinaryTreeADT<T> {
    /**
     * Adds the specified element to the proper location in this tree.
     *
     * @param element the element to be added to this tree
     */
    public void addElement(T element) throws NotComparableInstanceException;

    /**
     * Removes and returns the specified element from this tree.
     *
     * @param targetElement the element to be removed from this tree
     * @return the element removed from this tree
     */
    public T removeElement(T targetElement) throws ElementNotFoundException;

    /**
     * Removes all occurrences of the specified element from this tree.
     *
     * @param targetElement the element that the list will have all instances of it removed
     */
    public void removeAllOccurrences(T targetElement);

    /**
     * Removes and returns the smallest element from this tree.
     *
     * @return the smallest element from this tree.
     */
    public T removeMin() throws ElementNotFoundException;

    /**
     * Removes and returns the largest element from this tree.
     *
     * @return the largest element from this tree
     */
    public T removeMax() throws ElementNotFoundException;

    /**
     * Returns a reference to the smallest element in this tree.
     *
     * @return a reference to the smallest element in this tree
     */
    public T findMin() throws ElementNotFoundException;

    /**
     * Returns a reference to the largest element in this tree.
     *
     * @return a reference to the largest element in this tree
     */
    public T findMax() throws ElementNotFoundException;
}