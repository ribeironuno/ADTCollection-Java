package collections.implementation;

import collections.exceptions.ElementNotFoundException;
import collections.exceptions.NotComparableInstanceException;
import collections.interfaces.BinarySearchTreeADT;

import java.util.NoSuchElementException;

public class LinkedBinarySearchTree<T> extends LinkedBinaryTree<T> implements BinarySearchTreeADT<T> {
    /**
     * Creates an empty binary search tree.
     */
    public LinkedBinarySearchTree() {
        super();
    }

    /**
     * Creates a binary search with the specified element as its root.
     *
     * @param element the element that will be the root of the new binary search tree
     */
    public LinkedBinarySearchTree(T element) {
        super(element);
    }

    @Override
    public void addElement(T element) throws NotComparableInstanceException {
        BinaryTreeNode<T> temp = new BinaryTreeNode<>(element);
        if (!(element instanceof Comparable)) {
            throw new NotComparableInstanceException("Binary search tree");
        }
        Comparable<T> comparableElement = (Comparable<T>) element;

        if (super.isEmpty()) {
            super.root = temp;
        } else {
            BinaryTreeNode<T> current = super.root;
            boolean added = false;

            while (!added) {
                if (comparableElement.compareTo(current.element) < 0) { //comparableElement is smaller
                    if (current.left == null) {
                        current.left = temp;
                        added = true;
                    } else {
                        current = current.left;
                    }
                } else {
                    if (current.right == null) {
                        current.right = temp;
                        added = true;
                    } else {
                        current = current.right;
                    }
                }
            }
        }
        super.count++;
    }

    /**
     * Returns a reference to a node that will replace the one specified for removal. In the case where the removed node has
     * two children, the inorder successor (most left possible after right child) is used as its replacement.
     *
     * @param node the node to be removed
     * @return a reference to the replacing node
     */
    protected BinaryTreeNode<T> replacement(BinaryTreeNode<T> node) {
        BinaryTreeNode<T> result = null;

        if (node.left == null && node.right == null) { //If it's a leaf
            return null;
        } else if (node.left != null && node.right == null) {
            return node.left;
        } else if (node.left == null && node.right != null) {
            return node.right;
        } else {
            BinaryTreeNode<T> current = node.right;
            BinaryTreeNode<T> parent = node;

            while (current.left != null) {
                parent = current;
                current = current.left;
            }

            if (node.right == current) {
                current.left = node.left;
            } else {
                parent.left = current.right;
                current.right = node.right;
                current.left = node.left;
            }
            result = current;
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T removeElement(T targetElement) throws ElementNotFoundException {
        T result = null;

        Comparable<T> comparableElement = (Comparable<T>) targetElement;

        if (!super.isEmpty()) {
            if (comparableElement.compareTo(super.root.element) == 0) { //Equals to root
                result = super.root.element;
                super.root = this.replacement(super.root);
                super.count--;
            } else { //If is not root
                BinaryTreeNode<T> current, parent;
                current = parent = super.root;
                boolean found = false;

                if (comparableElement.compareTo(current.element) < 0) { //If target is smaller
                    current = super.root.left;
                } else {
                    current = super.root.right;
                }

                while (current != null && !found) {
                    if (comparableElement.compareTo(current.element) == 0) {
                        found = true;
                        super.count--;
                        result = current.element;

                        if (current == parent.left) {
                            parent.left = replacement(current);
                        } else {
                            parent.right = replacement(current);
                        }
                    } else {
                        parent = current;
                        if ((comparableElement).compareTo(current.element) < 0) {
                            current = current.left;
                        } else {
                            current = current.right;
                        }
                    }
                }
                if (!found) {
                    throw new ElementNotFoundException("binary search tree");
                }
            }
        }
        return result;
    }

    private T removeAux(BinaryTreeNode<T> node, BinaryTreeNode<T> parent, Comparable<T> targetElement) {
        if (node == null) {
            return null;
        } else {

            if (targetElement.compareTo(node.element) == 0) {
                if (node == super.root) {
                    super.root = this.replacement(node);
                } else {
                    if (parent.right == node) {
                        parent.right = this.replacement(node);
                    } else {
                        parent.left = this.replacement(node);
                    }
                }
                super.count--;
                return node.element;
            } else if (targetElement.compareTo(node.element) < 0) {
                return removeAux(node.left, node, targetElement);
            } else {
                return removeAux(node.right, node, targetElement);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public T removeElementRecursive(T targetElement) throws ElementNotFoundException {
        T result = removeAux(root, null, (Comparable<T>) targetElement);
        if (result == null) {
            throw new ElementNotFoundException("binary search tree");
        }
        return result;
    }

    @Override
    public void removeAllOccurrences(T targetElement) {
        try {
            removeElement(targetElement);
        } catch (ElementNotFoundException e) {
            return;
        }
        removeAllOccurrences(targetElement);
    }

    @Override
    public T removeMin() throws ElementNotFoundException {
        if (super.isEmpty()) {
            throw new NoSuchElementException("Binary search tree empty");
        }
        T result = super.root.element;
        BinaryTreeNode<T> current = super.root;
        BinaryTreeNode<T> parent = null;

        while (current.left != null) {
            result = current.element;
            parent = current;
            current = current.left;
        }

        if (current == super.root) { //Equals to root
            result = super.root.element;
            super.root = this.replacement(super.root);
            super.count--;
        } else {
            parent.left = this.replacement(current);
        }
        return result;
    }

    @Override
    public T removeMax() throws NoSuchElementException {
        if (super.isEmpty()) {
            throw new NoSuchElementException("Binary search tree empty");
        }

        T result = super.root.element;
        BinaryTreeNode<T> current = super.root;
        BinaryTreeNode<T> parent = null;

        while (current.right != null) {
            result = current.element;
            parent = current;
            current = current.right;
        }

        if (current == super.root) { //Equals to root
            result = super.root.element;
            super.root = this.replacement(super.root);
            super.count--;
        } else {
            parent.right = this.replacement(current);
        }
        return result;
    }

    @Override
    public T findMin() {
        if (super.isEmpty()) {
            throw new NoSuchElementException("Binary search tree empty");
        }
        BinaryTreeNode<T> current = super.root;

        while (current.left != null) {
            current = current.left;
        }
        return current.element;
    }

    @Override
    public T findMax() {
        if (super.isEmpty()) {
            throw new NoSuchElementException("Binary search tree empty");
        }
        BinaryTreeNode<T> current = super.root;

        while (current.right != null) {
            current = current.right;
        }
        return current.element;
    }
}