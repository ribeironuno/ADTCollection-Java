import collections.exceptions.NotComparableInstanceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import collections.implementation.*;

import java.util.NoSuchElementException;


public class TestLinkedList {

    private MyLinkedList<Integer> list;

    @Test
    @DisplayName("Test NoSuchElementException exception")
    public void testEmptyList() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            list.remove(2);
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            list.remove(null);
        });
    }

    @Test
    public void testAddElement() throws NotComparableInstanceException {
        int count = 0;
        Assertions.assertEquals(count++, list.size());
        list.add(2);
        Assertions.assertEquals(count++, list.size());
        list.add(2);
        Assertions.assertEquals(count++, list.size());
        list.add(2);
        Assertions.assertEquals(count++, list.size());
        list.add(2);
    }
}
