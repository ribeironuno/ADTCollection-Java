package collections.implementation;

import collections.exceptions.ElementNotFoundException;
import collections.interfaces.BinaryTreeADT;
import collections.interfaces.QueueADT;

import java.util.Iterator;

public class ArrayBinaryTree<T> implements BinaryTreeADT<T> {

    /**
     * Counter to elements
     */
    protected int count;

    /**
     * Array that contains elements of tree
     */
    protected T[] tree;

    /**
     * Initial capacity
     */
    private final int INITIAL_CAPACITY = 50;

    /**
     * Creates an empty binary tree.
     */
    @SuppressWarnings("unchecked")
    public ArrayBinaryTree() {
        this.count = 0;
        this.tree = (T[]) (new Object[INITIAL_CAPACITY]);
    }

    /**
     * Creates a binary tree with the specified element as its root.
     *
     * @param element the element which will become the root of the new tree
     */
    @SuppressWarnings("unchecked")
    public ArrayBinaryTree(T element) {
        this.count = 1;
        this.tree = (T[]) (new Object[INITIAL_CAPACITY]);
        this.tree[0] = element;
    }

    @SuppressWarnings("unchecked")
    protected void expandCapacity() {
        T[] tmp = (T[]) (new Object[this.count * 2]);
        for (int i = 0; i < this.count; i++) {
            tmp[i] = this.tree[i];
        }
        tree = tmp;
    }

    @Override
    public T getRoot() {
        return this.count == 0 ? null : this.tree[0];
    }

    @Override
    public boolean isEmpty() {
        return this.count == 0;
    }

    @Override
    public int size() {
        return this.count;
    }

    @Override
    public boolean contains(T targetElement) {
        try {
            find(targetElement);
        } catch (ElementNotFoundException ex) {
            return false;
        }
        return true;
    }

    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        if (this.count == 0) {
            throw new ElementNotFoundException("binary tree");
        }
        T result = null;
        boolean found = false;

        Comparable<T> comparable = (Comparable<T>) targetElement;
        int current = 0;

        while (tree[current] != null && !found) {

            if (comparable.compareTo(tree[current]) == 0) {
                result = tree[current];
                found = true;
            } else if (comparable.compareTo(tree[current]) < 0) {
                current = leftSideIndex(current);
            } else {
                current = rightSideIndex(current);
            }
        }

        if (!found) {
            throw new ElementNotFoundException("Binary tree");
        } else {
            return result;
        }
    }

    /**
     * Return index of left element
     *
     * @param node index of node
     * @return left element index
     */
    protected int leftSideIndex(int node) {
        return (2 * node) + 1;
    }

    /**
     * Return index of right element
     *
     * @param node index of node
     * @return right element index
     */
    protected int rightSideIndex(int node) {
        return 2 * (node + 1);
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param node     the node used in the traversal
     * @param templist the temporary list used in the traversal
     */
    protected void inOrder(int node, ArrayUnorderedList<T> templist) {
        if (node < tree.length) {
            if (tree[node] != null) {
                this.inOrder(this.leftSideIndex(node), templist);
                templist.addToRear(tree[node]);
                this.inOrder(this.rightSideIndex(node), templist);
            }
        }
    }

    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>(this.count);
        this.inOrder(0, tempList);
        return tempList.iterator();
    }

    /**
     * Performs a recursive preorder traversal.
     *
     * @param node     the node used in the traversal
     * @param templist the temporary list used in the traversal
     */
    protected void preOrder(int node, ArrayUnorderedList<T> templist) {
        if (node < tree.length) {
            if (tree[node] != null) {
                templist.addToRear(tree[node]);
                this.inOrder(this.leftSideIndex(node), templist);
                this.inOrder(this.rightSideIndex(node), templist);
            }
        }
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>(this.count);
        this.preOrder(0, tempList);
        return tempList.iterator();
    }

    /**
     * Performs a recursive postOrder traversal.
     *
     * @param node     the node used in the traversal
     * @param templist the temporary list used in the traversal
     */
    protected void postOrder(int node, ArrayUnorderedList<T> templist) {
        if (node < tree.length) {
            if (tree[node] != null) {
                this.inOrder(this.leftSideIndex(node), templist);
                this.inOrder(this.rightSideIndex(node), templist);
                templist.addToRear(tree[node]);
            }
        }
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>(this.count);
        this.postOrder(0, tempList);
        return tempList.iterator();
    }

    @Override
    public Iterator<T> iteratorLevelOrder() {
        ArrayUnorderedList<T> results = new ArrayUnorderedList<>(this.count);
        QueueADT<Integer> nodes = new CircularArrayQueue<>(this.count);

        nodes.enqueue(0);
        while (!nodes.isEmpty()) {
            Integer current = nodes.dequeue();

            //If its last
            if (this.tree[current] != null) {
                results.addToRear(tree[current]);
                if (leftSideIndex(current) < tree.length) {
                    nodes.enqueue(leftSideIndex(current));
                }
                if (rightSideIndex(current) < tree.length) {
                    nodes.enqueue(rightSideIndex(current));
                }
            }
        }

        return results.iterator();
    }
}
