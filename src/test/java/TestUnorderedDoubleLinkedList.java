
import static org.junit.jupiter.api.Assertions.*;

import collections.implementation.DoubleLinkedUnorderedList;
import collections.interfaces.UnorderedListADT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

class TestUnorderedDoubleLinkedList {

    UnorderedListADT<String> strList;

    @BeforeEach
    public void beforeAll() {
        strList = new DoubleLinkedUnorderedList<>();
    }

    @Test
    public void isEmpty() {
        assertTrue(strList.isEmpty());
        strList.addToFront("pos 0");
        assertFalse(strList.isEmpty());
    }

    @Test
    public void removeInEmpty() {
        Assertions.assertThrows(NoSuchElementException.class, () -> strList.removeFirst());
        Assertions.assertThrows(NoSuchElementException.class, () -> strList.removeLast());
        Assertions.assertThrows(NoSuchElementException.class, () -> strList.remove("str"));
    }

    @Test
    public void size() {
        assertEquals(0, strList.size());
        strList.addToFront("pos 0");
        assertEquals(1, strList.size());
        strList.addToFront("pos 1");
        strList.addToFront("pos 2");
        assertEquals(3, strList.size());
        strList.addToFront("pos 3");
        assertEquals(4, strList.size());
    }

    @Test
    public void addAtBegin() {
        strList.addToFront("pos 0");
        assertEquals("pos 0", strList.first());

        strList.addToFront("pos 1");
        assertEquals("pos 1", strList.first());

        strList.addToFront("pos 2");
        assertEquals("pos 2", strList.first());

        strList.addToFront("pos 3");
        assertEquals("pos 3", strList.first());

        strList.addToFront("pos 4");
        assertEquals("pos 4", strList.first());

        strList.addToFront("pos 5");
        assertEquals("pos 5", strList.first());

        strList.addToFront("pos 6");
        assertEquals("pos 6", strList.first());
    }

    @Test
    public void addAtRear() {
        strList.addToRear("pos 0");
        assertEquals("pos 0", strList.last());

        strList.addToRear("pos 1");
        assertEquals("pos 1", strList.last());

        strList.addToRear("pos 2");
        assertEquals("pos 2", strList.last());

        strList.addToRear("pos 3");
        assertEquals("pos 3", strList.last());

        strList.addToRear("pos 4");
        assertEquals("pos 4", strList.last());

        strList.addToRear("pos 5");
        assertEquals("pos 5", strList.last());

        strList.addToRear("pos 6");
        assertEquals("pos 6", strList.last());
    }


    @Test
    public void clear() {
        strList.addToFront("pos 0");
        strList.addToFront("pos 1");
        strList.addToFront("pos 2");
        strList.addToFront("pos 3");

        assertEquals(4, strList.size());

        strList.removeFirst();
        strList.removeFirst();
        strList.removeFirst();
        strList.removeFirst();

        assertEquals(0, strList.size());

        Assertions.assertThrows(NoSuchElementException.class, () -> strList.removeLast());
        strList.addToRear("pos 0");
        strList.removeFirst();
        Assertions.assertThrows(NoSuchElementException.class, () -> strList.removeLast());
    }

    @Test
    public void remove() {
        strList.addToRear("pos 0");
        strList.addToRear("pos 1");
        strList.addToRear("pos 2");

        assertEquals("pos 0", strList.first());
        assertEquals("pos 2", strList.last());

        assertEquals(3, strList.size());

        strList.remove("pos 2");

        assertEquals("pos 0", strList.first());
        assertEquals("pos 1", strList.last());

        assertEquals(2, strList.size());

        strList.addToRear("pos 2");
        strList.addToRear("pos 3");

        assertEquals("pos 0", strList.first());
        assertEquals("pos 3", strList.last());

        assertEquals(4, strList.size());

        strList.remove("pos 0");
        strList.remove("pos 3");

        assertEquals("pos 1", strList.first());
        assertEquals("pos 2", strList.last());

        assertEquals(2, strList.size());
    }

    @Test
    public void removeLast() {
        strList.addToRear("pos 0");
        strList.addToRear("pos 1");
        strList.addToRear("pos 2");
        strList.addToRear("pos 3");
        strList.addToRear("pos 4");

        assertEquals("pos 4", strList.removeLast());
        assertEquals("pos 3", strList.removeLast());
        assertEquals("pos 2", strList.removeLast());
        assertEquals("pos 1", strList.removeLast());
        assertEquals("pos 0", strList.removeLast());

        Assertions.assertThrows(NoSuchElementException.class, () -> strList.removeLast());
    }

    @Test
    public void removeFirst() {
        strList.addToRear("pos 0");
        strList.addToRear("pos 1");
        strList.addToRear("pos 2");
        strList.addToRear("pos 3");
        strList.addToRear("pos 4");

        assertEquals("pos 0", strList.removeFirst());
        assertEquals("pos 1", strList.removeFirst());
        assertEquals("pos 2", strList.removeFirst());
        assertEquals("pos 3", strList.removeFirst());
        assertEquals("pos 4", strList.removeFirst());

        Assertions.assertThrows(NoSuchElementException.class, () -> strList.removeFirst());
    }

    @Test
    public void contains() {
        strList.addToRear("pos 0");
        strList.addToRear("pos 1");
        strList.addToRear("pos 2");
        strList.addToRear("pos 3");
        strList.addToRear("pos 4");

        assertTrue(strList.contains("pos 0"));
        assertTrue(strList.contains("pos 1"));
        assertTrue(strList.contains("pos 2"));
        assertTrue(strList.contains("pos 3"));
        assertTrue(strList.contains("pos 4"));

        assertFalse(strList.contains("pos 20"));
        assertFalse(strList.contains("pos 10"));
    }

    @Test
    public void getFirstAndLast() {
        strList.addToFront("pos 0");
        strList.addToRear("pos 3");

        assertEquals("pos 0", strList.first());
        assertEquals("pos 3", strList.last());

    }
}

