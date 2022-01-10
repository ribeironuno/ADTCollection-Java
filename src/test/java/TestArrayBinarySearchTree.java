import collections.exceptions.ElementNotFoundException;
import collections.implementation.ArrayBinarySearchTree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class TestArrayBinarySearchTree {

    private ArrayBinarySearchTree<Integer> tree = null;

    @Test
    public void testIsEmpty() throws ElementNotFoundException {
        tree = new ArrayBinarySearchTree<>();

        assertTrue(tree.isEmpty());

        tree.addElement(2);

        assertFalse(tree.isEmpty());
    }

    @Test
    public void testContains() throws ElementNotFoundException {
        tree = new ArrayBinarySearchTree<>();

        tree.addElement(2);
        tree.addElement(3);
        tree.addElement(1);
        tree.addElement(10);
        tree.addElement(21);
        tree.addElement(-21);
        tree.addElement(0);
        tree.addElement(123);

        Iterator<Integer> iterator = tree.iteratorLevelOrder();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        assertTrue(tree.contains(2));
        assertTrue(tree.contains(3));
        assertTrue(tree.contains(1));
        assertTrue(tree.contains(10));
        assertTrue(tree.contains(21));
        assertTrue(tree.contains(-21));
        assertTrue(tree.contains(0));
        assertTrue(tree.contains(123));

        assertFalse(tree.contains(9));
    }

    @Test
    public void testFindMax01() throws ElementNotFoundException {
        tree = new ArrayBinarySearchTree<>();

        tree.addElement(1);
        tree.addElement(2);
        tree.addElement(10);
        tree.addElement(-2);
        tree.addElement(10000);
        tree.addElement(20000);

        assertEquals(20000, tree.findMax());
    }

    @Test
    public void testFindMax02() throws ElementNotFoundException {
        tree = new ArrayBinarySearchTree<>();

        tree.addElement(1);
        tree.addElement(-2);
        tree.addElement(-3);
        tree.addElement(-5);
        tree.addElement(-100);

        assertEquals(1, tree.findMax());
    }

    @Test
    public void testFindMin01() throws ElementNotFoundException {
        tree = new ArrayBinarySearchTree<>();

        tree.addElement(1);
        tree.addElement(2);
        tree.addElement(10);
        tree.addElement(-2);
        tree.addElement(10000);
        tree.addElement(20000);

        assertEquals(-2, tree.findMin());
    }

    @Test
    public void testFindMin02() throws ElementNotFoundException {
        tree = new ArrayBinarySearchTree<>();

        tree.addElement(1);
        tree.addElement(2);
        tree.addElement(3);
        tree.addElement(5);
        tree.addElement(100);

        assertEquals(1, tree.findMin());
    }

    @Test
    public void testFindMinMaxThrow() {
        tree = new ArrayBinarySearchTree<>();

        assertThrows(ElementNotFoundException.class, () -> tree.findMax());
        assertThrows(ElementNotFoundException.class, () -> tree.findMin());
    }


    @Test
    public void testAdd() {
        tree = new ArrayBinarySearchTree<>();

        tree.addElement(7);

        Iterator<Integer> iterator = tree.iteratorLevelOrder();
        assertEquals(7, iterator.next());

        tree.addElement(20);
        tree.addElement(5);
        tree.addElement(2);
        tree.addElement(1);
        tree.addElement(30);
        tree.addElement(25);
        tree.addElement(40);
        tree.addElement(3);

        iterator = tree.iteratorLevelOrder();
        assertEquals(7, iterator.next());
        assertEquals(5, iterator.next());
        assertEquals(20, iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(30, iterator.next());
        assertEquals(1, iterator.next());
        assertEquals(3, iterator.next());
        assertEquals(25, iterator.next());
    }

    @Test
    @DisplayName("test remove middle node with left child empty")
    public void testRemove01() throws ElementNotFoundException {
        tree = new ArrayBinarySearchTree<>();

        tree.addElement(7);
        tree.addElement(20);
        tree.addElement(5);
        tree.addElement(2);
        tree.addElement(1);
        tree.addElement(30);
        tree.addElement(25);
        tree.addElement(40);
        tree.addElement(3);

        Iterator<Integer> iterator = tree.iteratorLevelOrder();
        assertEquals(7, iterator.next());
        assertEquals(5, iterator.next());
        assertEquals(20, iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(30, iterator.next());
        assertEquals(1, iterator.next());
        assertEquals(3, iterator.next());
        assertEquals(25, iterator.next());
        assertEquals(40, iterator.next());

        assertEquals(20, tree.removeElement(20));

        iterator = tree.iteratorLevelOrder();
        assertEquals(7, iterator.next());
        assertEquals(5, iterator.next());
        assertEquals(30, iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(25, iterator.next());
        assertEquals(40, iterator.next());
        assertEquals(1, iterator.next());
        assertEquals(3, iterator.next());
    }

    @Test
    @DisplayName("test remove middle node with right child empty")
    public void testRemove02() throws ElementNotFoundException {
        tree = new ArrayBinarySearchTree<>();

        tree.addElement(7);
        tree.addElement(20);
        tree.addElement(5);
        tree.addElement(2);
        tree.addElement(1);
        tree.addElement(30);
        tree.addElement(25);
        tree.addElement(40);
        tree.addElement(3);

        Iterator<Integer> iterator = tree.iteratorLevelOrder();
        assertEquals(7, iterator.next());
        assertEquals(5, iterator.next());
        assertEquals(20, iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(30, iterator.next());
        assertEquals(1, iterator.next());
        assertEquals(3, iterator.next());
        assertEquals(25, iterator.next());
        assertEquals(40, iterator.next());

        assertEquals(20, tree.removeElement(20));

        iterator = tree.iteratorLevelOrder();
        assertEquals(7, iterator.next());
        assertEquals(5, iterator.next());
        assertEquals(30, iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(25, iterator.next());
        assertEquals(40, iterator.next());
        assertEquals(1, iterator.next());
        assertEquals(3, iterator.next());
    }

    @Test
    @DisplayName("test remove leaf node left")
    public void testRemove03() throws ElementNotFoundException {
        tree = new ArrayBinarySearchTree<>();

        tree.addElement(7);
        tree.addElement(20);
        tree.addElement(5);
        tree.addElement(2);
        tree.addElement(1);
        tree.addElement(30);
        tree.addElement(25);
        tree.addElement(40);
        tree.addElement(3);

        Iterator<Integer> iterator = tree.iteratorLevelOrder();
        assertEquals(7, iterator.next());
        assertEquals(5, iterator.next());
        assertEquals(20, iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(30, iterator.next());
        assertEquals(1, iterator.next());
        assertEquals(3, iterator.next());
        assertEquals(25, iterator.next());
        assertEquals(40, iterator.next());

        assertEquals(1, tree.removeElement(1));

        iterator = tree.iteratorLevelOrder();
        assertEquals(7, iterator.next());
        assertEquals(5, iterator.next());
        assertEquals(20, iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(30, iterator.next());
        assertEquals(3, iterator.next());
        assertEquals(25, iterator.next());
        assertEquals(40, iterator.next());
    }

    @Test
    @DisplayName("test remove leaf node right")
    public void testRemove05() throws ElementNotFoundException {
        tree = new ArrayBinarySearchTree<>();

        tree.addElement(7);
        tree.addElement(20);
        tree.addElement(5);
        tree.addElement(2);
        tree.addElement(1);
        tree.addElement(30);
        tree.addElement(25);
        tree.addElement(40);
        tree.addElement(3);

        Iterator<Integer> iterator = tree.iteratorLevelOrder();
        assertEquals(7, iterator.next());
        assertEquals(5, iterator.next());
        assertEquals(20, iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(30, iterator.next());
        assertEquals(1, iterator.next());
        assertEquals(3, iterator.next());
        assertEquals(25, iterator.next());
        assertEquals(40, iterator.next());

        assertEquals(40, tree.removeElement(40));

        iterator = tree.iteratorLevelOrder();
        assertEquals(7, iterator.next());
        assertEquals(5, iterator.next());
        assertEquals(20, iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(30, iterator.next());
        assertEquals(1, iterator.next());
        assertEquals(3, iterator.next());
        assertEquals(25, iterator.next());
    }

    @Test
    @DisplayName("test remove root empty")
    public void testRemove06() throws ElementNotFoundException {
        tree = new ArrayBinarySearchTree<>();

        tree.addElement(7);

        assertEquals(7, tree.removeElement(7));

        assertTrue(tree.isEmpty());
    }


}
