import static org.junit.jupiter.api.Assertions.*;

import collections.implementation.ArrayUnorderedList;
import collections.implementation.DoubleLinkedUnorderedList;
import collections.interfaces.UnorderedListADT;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TestIteratorUnordered {

    UnorderedListADT<Integer> list;
    Iterator<Integer> iterator;

    @BeforeEach
    public void beforeEach() {
        list = new DoubleLinkedUnorderedList<>();
    }

    @Test
    public void hasNextInEmpty() {
        iterator = list.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void hasNextInNotEmpty() {
        list.addToRear(0);
        list.addToRear(1);
        list.addToRear(2);
        list.addToRear(3);
        list.addToRear(4);

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
        list.addToRear(0);
        list.addToRear(1);
        list.addToRear(2);
        list.addToRear(3);
        list.addToRear(4);

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
        list.addToRear(0);
        list.addToRear(1);
        list.addToRear(2);
        list.addToRear(3);
        list.addToRear(4);

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
        list.addToRear(0);
        list.addToRear(1);

        iterator = list.iterator();

        assertThrows(IllegalStateException.class, () -> iterator.remove());
        assertThrows(IllegalStateException.class, () -> iterator.remove());
        assertThrows(IllegalStateException.class, () -> iterator.remove());
    }

    @Test
    public void testModCount() {
        list.addToRear(0);
        list.addToRear(1);
        list.addToRear(2);
        list.addToRear(3);
        list.addToRear(4);

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
