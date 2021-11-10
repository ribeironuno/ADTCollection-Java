
import static org.junit.jupiter.api.Assertions.*;

import collections.exceptions.NotComparableInstance;
import collections.implementation.DoubleLinkedOrderedList;
import collections.interfaces.OrderedListADT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TestIteratorOrdered {

    OrderedListADT<Integer> list;
    Iterator<Integer> iterator;

    @BeforeEach
    public void beforeEach() {
        list = new DoubleLinkedOrderedList<>();
    }

    @Test
    public void hasNextInEmpty() {
        iterator = list.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void hasNextInNotEmpty() {
        try {
            list.add(0);
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
        } catch (NotComparableInstance ex) {
        }
        iterator = list.iterator();

        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        assertTrue(iterator.hasNext());
        iterator.next();

        assertTrue(iterator.hasNext());
        iterator.next();

        assertTrue(iterator.hasNext());
        iterator.next();

        assertTrue(iterator.hasNext());
        iterator.next();

        assertTrue(iterator.hasNext());
        iterator.next();

        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasNext());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void nextInEmpty() {
        iterator = list.iterator();

        assertThrows(NoSuchElementException.class, () -> iterator.next());
        assertThrows(NoSuchElementException.class, () -> iterator.next());
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    public void nextInNotEmpty() {
        try {
            list.add(0);
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
        } catch (NotComparableInstance ex) {
        }
        iterator = list.iterator();

        assertEquals(0, iterator.next());
        assertEquals(1, iterator.next());
        assertEquals(2, iterator.next());
        assertEquals(3, iterator.next());
        assertEquals(4, iterator.next());

        assertThrows(NoSuchElementException.class, () -> iterator.next());
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    public void removeAll() {
        try {
            list.add(0);
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
        } catch (NotComparableInstance ex) {
        }
        iterator = list.iterator();

        assertEquals(0, iterator.next());
        iterator.remove();

        assertEquals(1, iterator.next());
        iterator.remove();

        assertEquals(2, iterator.next());
        iterator.remove();

        assertEquals(3, iterator.next());
        iterator.remove();

        assertEquals(4, iterator.next());
        iterator.remove();

        assertThrows(NoSuchElementException.class, () -> iterator.next());
        assertThrows(NoSuchElementException.class, () -> iterator.next());
    }

    @Test
    public void removeBeforeNext() {
        try {
            list.add(0);
        } catch (NotComparableInstance ex) {
        }
        iterator = list.iterator();

        assertThrows(IllegalStateException.class, () -> iterator.remove());
        assertThrows(IllegalStateException.class, () -> iterator.remove());
        assertThrows(IllegalStateException.class, () -> iterator.remove());
    }

    @Test
    public void testModCount() {
        try {
            list.add(0);
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
            list.add(5);
        } catch (NotComparableInstance ex) {
        }
        iterator = list.iterator();

        list.removeFirst();

        assertThrows(ConcurrentModificationException.class, () -> iterator.next());

        iterator = list.iterator();
        list.removeLast();
        assertThrows(ConcurrentModificationException.class, () -> iterator.next());

        iterator = list.iterator();
        list.removeLast();
        assertThrows(ConcurrentModificationException.class, () -> iterator.next());
    }
}
