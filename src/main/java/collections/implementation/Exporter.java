package collections.implementation;

import collections.interfaces.BinaryTreeADT;
import collections.interfaces.IExporter;
import collections.interfaces.QueueADT;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Class that implements interface IExporter.
 */
public class Exporter implements IExporter {

    public <T> void exportLinkedBinaryTreeToGraph(LinkedBinaryTree<T> tree, String filename) {
        QueueADT<BinaryTreeNode<T>> nodes = new LinkedQueue<>();

        //Make a lever order traverse and write into string the content of .dot graph
        String content = """
                digraph{
                    graph [ordering="out"];
                """;

        nodes.enqueue(tree.root);

        BinaryTreeNode<T> current;
        while (!nodes.isEmpty()) {
            current = nodes.dequeue();
            if (current.left != null) {
                nodes.enqueue(current.left);
                content += current.element.toString() + "->" + current.left.element.toString() + "[label=\"L\"]\n";
            }
            if (current.right != null) {
                nodes.enqueue(current.right);
                content += current.element.toString() + "->" + current.right.element.toString() + "[label=\"R\"]\n";
            }
        }

        content += "\n}";

        //Make a file .dot with content
        try {
            FileWriter myWriter = new FileWriter("docs/" + filename + ".dot");
            myWriter.write(content);
            myWriter.close();

        } catch (
                IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        makePngFile(filename + ".dot");

    }

    /**
     * Creates and png image based on dot file, by executing the command "dot -Tpng 'filename' -O. Based on documentation
     * of dot shell.
     *
     * @param fileNameOfDot File name of dot document to be created
     * @param <T>
     */
    private <T> void makePngFile(String fileNameOfDot) {
        /**
         Creates and png image based on dot file, by executing the command
         */
        String dotFileName = "docs/" + fileNameOfDot;
        try {
            String[] c = {"dot", "-Tpng", dotFileName, "-O"}; //command to execute
            Process p = Runtime.getRuntime().exec(c);
            int err = p.waitFor();
        } catch (IOException |
                InterruptedException e1) {
            System.out.println(e1);
        }
    }
}
