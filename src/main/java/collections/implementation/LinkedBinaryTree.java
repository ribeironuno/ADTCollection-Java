package collections.implementation;

import collections.exceptions.ElementNotFoundException;
import collections.interfaces.BinaryTreeADT;
import collections.interfaces.QueueADT;

import java.util.Iterator;

/**
 * LinkedBinaryTree implements the BinaryTreeADT interface
 */
public class LinkedBinaryTree<T> implements BinaryTreeADT<T> {

    /**
     * Root of tree
     */
    protected BinaryTreeNode<T> root;

    /**
     * Counter to number of nodes
     */
    protected int count;

    /**
     * Creates an empty binary tree.
     */
    public LinkedBinaryTree() {
        this.count = 0;
        this.root = null;
    }

    /**
     * Creates a binary tree with the specified element as its root.
     *
     * @param element the element that will become the root of the new binary tree
     */
    public LinkedBinaryTree(T element) {
        count = 1;
        root = new BinaryTreeNode<T>(element);
    }

    @Override
    public T getRoot() {
        return this.root.element;
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
        } catch (ElementNotFoundException e) {
            return false;
        }
        return true;
    }

    @Override
    public T find(T targetElement) throws ElementNotFoundException {
        BinaryTreeNode<T> node = this.findAgain(targetElement, this.root);
        if (node == null) {
            throw new ElementNotFoundException("Binary tree");
        } else {
            return node.element;
        }
    }

    /**
     * Returns a reference to the specified target element if it is found in this binary tree.
     *
     * @param targetElement the element being sought in this tree
     * @param next          the element to begin searching from
     */
    private BinaryTreeNode<T> findAgain(T targetElement, BinaryTreeNode<T> next) {
        if (next == null) {
            return null;
        }

        if (next.element.equals(targetElement)) {
            return next;
        }

        BinaryTreeNode<T> temp = findAgain(targetElement, next.left);
        if (temp == null) {
            temp = findAgain(targetElement, next.right);
        }

        return temp;
    }

    /**
     * Performs a recursive inorder traversal.
     *
     * @param node     the node to be used as the root for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    protected void inOrder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            this.inOrder(node.left, tempList);
            tempList.addToRear(node.element);
            this.inOrder(node.right, tempList);
        }
    }

    @Override
    public Iterator<T> iteratorInOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>(this.count);
        this.inOrder(this.root, tempList);
        return tempList.iterator();
    }

    /**
     * Performs a recursive preorder traversal.
     *
     * @param node     the node to be used as the root for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    protected void preOrder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            tempList.addToRear(node.element);
            this.preOrder(node.left, tempList);
            this.preOrder(node.right, tempList);
        }
    }

    @Override
    public Iterator<T> iteratorPreOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>(this.count);
        this.preOrder(this.root, tempList);
        return tempList.iterator();
    }

    /**
     * Performs a recursive postorder traversal.
     *
     * @param node     the node to be used as the root for this traversal
     * @param tempList the temporary list for use in this traversal
     */
    protected void postOrder(BinaryTreeNode<T> node, ArrayUnorderedList<T> tempList) {
        if (node != null) {
            this.postOrder(node.left, tempList);
            this.postOrder(node.right, tempList);
            tempList.addToRear(node.element);
        }
    }

    @Override
    public Iterator<T> iteratorPostOrder() {
        ArrayUnorderedList<T> tempList = new ArrayUnorderedList<>(this.count);
        this.postOrder(this.root, tempList);
        return tempList.iterator();
    }

    @Override
    public Iterator<T> iteratorLevelOrder() {
        QueueADT<BinaryTreeNode<T>> nodes = new LinkedQueue<>();
        ArrayUnorderedList<T> results = new ArrayUnorderedList<>(this.count);

        nodes.enqueue(this.root);

        BinaryTreeNode<T> current;
        while (!nodes.isEmpty()) {
            current = nodes.dequeue();
            results.addToRear(current.element);

            if (current.left != null) {
                nodes.enqueue(current.left);
            }
            if (current.right != null) {
                nodes.enqueue(current.right);
            }
        }
        return results.iterator();
    }
}

