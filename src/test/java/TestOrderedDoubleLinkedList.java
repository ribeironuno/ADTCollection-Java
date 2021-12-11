
import static org.junit.jupiter.api.Assertions.*;

import collections.exceptions.NotComparableInstanceException;
import collections.implementation.DoubleLinkedOrderedList;
import collections.interfaces.OrderedListADT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

class TestOrderedDoubleLinkedList {

    OrderedListADT<Integer> strList;

    @BeforeEach
    public void beforeEach(){
        strList = new DoubleLinkedOrderedList<>();
    }

    @Test
    public void isEmpty() throws NotComparableInstanceException {
        assertTrue(strList.isEmpty());
        strList.add(1);
        assertFalse(strList.isEmpty());
    }

    @Test
    public void removeInEmpty(){
        Assertions.assertThrows(NoSuchElementException.class, () -> strList.removeFirst());
        Assertions.assertThrows(NoSuchElementException.class, () -> strList.removeLast());
        Assertions.assertThrows(NoSuchElementException.class, () -> strList.remove(2));
    }

    @Test
    public void size() throws NotComparableInstanceException {
        assertEquals(0, strList.size());
        strList.add(1);
        assertEquals(1, strList.size());
        strList.add(2);
        strList.add(3);
        assertEquals(3, strList.size());
        strList.add(4);
        strList.add(5);
        assertEquals(5, strList.size());
    }

    @Test
    public void clear() throws NotComparableInstanceException {
        strList.add(1);
        strList.add(2);
        strList.add(3);
        strList.add(4);
        strList.add(5);

        assertEquals(5, strList.size());

        strList.removeFirst();
        strList.removeFirst();
        strList.removeFirst();
        strList.removeFirst();
        strList.removeFirst();

        assertEquals(0, strList.size());

        Assertions.assertThrows(NoSuchElementException.class, () -> strList.removeLast());
        strList.add(2);
        Assertions.assertEquals(2, strList.first());
        strList.removeLast();
        Assertions.assertThrows(NoSuchElementException.class, () -> strList.removeFirst());
    }

    @Test
    public void remove() throws NotComparableInstanceException {
        strList.add(0);
        strList.add(1);
        strList.add(2);
        strList.add(3);

        assertEquals(0, strList.first());
        assertEquals(3, strList.last());

        assertEquals(4, strList.size());

        strList.remove(2);

        assertEquals(3, strList.size());

        strList.add(1);
        strList.add(4);

        assertEquals(0, strList.first());
        assertEquals(4, strList.last());

        assertEquals(5, strList.size());

        strList.remove(1);

        assertEquals(0, strList.first());

        assertEquals(4, strList.size());
    }

    @Test
    public void orderedAdd() throws NotComparableInstanceException {
        strList.add(1);
        strList.add(5);
        strList.add(10);
        strList.add(1022);
        strList.add(0);
        strList.add(-1022);
        strList.add(-1);
        strList.add(-100);

        assertEquals(-1022, strList.removeFirst());
        assertEquals(-100, strList.removeFirst());
        assertEquals(-1, strList.removeFirst());
        assertEquals(0, strList.removeFirst());
        assertEquals(1, strList.removeFirst());
        assertEquals(5, strList.removeFirst());
        assertEquals(10, strList.removeFirst());
        assertEquals(1022, strList.removeFirst());
    }

    @Test
    public void contains() throws NotComparableInstanceException {
        strList.add(1);
        strList.add(2);
        strList.add(3);
        strList.add(4);
        strList.add(5);

        assertTrue(strList.contains(1));
        assertTrue(strList.contains(2));
        assertTrue(strList.contains(3));
        assertTrue(strList.contains(4));
        assertTrue(strList.contains(5));

        assertFalse(strList.contains(44));
        assertFalse(strList.contains(-1));
    }

    @Test
    public void getFirstAndLast() throws NotComparableInstanceException {
        strList.add(0);
        strList.add(1);
        strList.add(2);
        strList.add(3);

        assertEquals(0, strList.first());
        assertEquals(3, strList.last());

    }

}