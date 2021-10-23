import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import collections.implementation.*;

import java.util.NoSuchElementException;


public class TestLinkedList {

    private MyLinkedList<Integer> list = new MyLinkedList<>();

    @Test
    @DisplayName("Testa a exceção da lista vazia")
    public void testEmptyList() {
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            list.remove(2);
        });
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            list.remove(null);
        });
    }

    @Test
    public void testAddElement() {
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
