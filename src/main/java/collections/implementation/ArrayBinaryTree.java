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
        T temp = null;
        boolean found = false;

        for (int i = 0; i < this.count && !found; i++) {
            if (targetElement.equals(tree[i])) {
                found = true;
                temp = tree[i];
            }
        }
        if (!found) {
            throw new ElementNotFoundException("Binary tree");
        } else {
            return temp;
        }
    }

    /**
     * Return index of left element
     *
     * @param node index of node
     * @return left element index
     */
    private int leftSideIndex(int node) {
        return (2 * node) + 1;
    }

    /**
     * Return index of right element
     *
     * @param node index of node
     * @return right element index
     */
    private int rightSideIndex(int node) {
        return 2 * (node + 1);
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param node     the node used in the traversal
     * @param templist the temporary list used in the traversal
     */
    protected void inOrder(int node, ArrayUnorderedList<T> templist) {
        if (tree[node] != null) {
            this.inOrder(this.leftSideIndex(node), templist);
            templist.addToRear(tree[node]);
            this.inOrder(this.rightSideIndex(node), templist);
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
        if (tree[node] != null) {
            templist.addToRear(tree[node]);
            this.inOrder(this.leftSideIndex(node), templist);
            this.inOrder(this.rightSideIndex(node), templist);
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
        if (tree[node] != null) {
            this.inOrder(this.leftSideIndex(node), templist);
            this.inOrder(this.rightSideIndex(node), templist);
            templist.addToRear(tree[node]);
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

        for (int i = 0; i < this.count; i++) {
            results.addToRear(tree[i]);
        }

        return results.iterator();
    }
}
