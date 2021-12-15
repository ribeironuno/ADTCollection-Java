package collections.interfaces;

import collections.implementation.LinkedBinaryTree;
import collections.interfaces.BinaryTreeADT;

/**
 * Interface of exporter
 */
public interface IExporter {

    /**
     * Exports an instance of {@link LinkedBinaryTree<T>} to a file .dot and png image.
     * @param tree Instance of linked binary tree to be exported.
     * @param filename Name of file to be created on directory "docs". Attention: if file name already exists will be overwritten
     */
    <T> void exportLinkedBinaryTreeToGraph(LinkedBinaryTree<T> tree, String filename);

}
