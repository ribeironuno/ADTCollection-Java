import collections.implementation.CircularArrayQueue;
import collections.interfaces.QueueADT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.*;

/*
#COPYRIGHT: 
*/

public class TestCircularArrayQueue {

    private final QueueADT<Integer> queue = new CircularArrayQueue<>();

    @Test
    public void testIsEmptyOnEmpty() {
        Assertions.assertTrue(queue.isEmpty());
    }

    @Test
    public void testIsEmptyOnNotEmpty() {
        queue.enqueue(5);
        Assertions.assertFalse(queue.isEmpty());
    }

    @Test
    public void testOneDequeue() {
        queue.enqueue(5);
        Assertions.assertEquals(5, (int) queue.dequeue());
    }

    @Test
    public void testOrdering() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        Assertions.assertEquals(1, queue.dequeue());
        Assertions.assertEquals(2, queue.dequeue());
        Assertions.assertEquals(3, queue.dequeue());
    }

    @Test
    public void testDequeueOnEmpty() {
        Assertions.assertThrows(NoSuchElementException.class, () -> queue.dequeue());
    }

    @Test
    public void testEmptyAfterDequeue() {
        queue.enqueue(5);
        queue.dequeue();
        Assertions.assertTrue(queue.isEmpty());
    }


    private void testNOperations(int n) {
        Random r = new Random();
        LinkedList<Integer> goodQueue = new LinkedList<>();
        int num;
        for (int i = 0; i < n; i++) {
            //enqueue element if queue is empty, or on a 2/3 chance
            if (queue.isEmpty() || r.nextInt(3) < 2) {
                num = r.nextInt(100);
                queue.enqueue(num);
                goodQueue.add(num);
            } else //dequeue
            {
                Assertions.assertSame(goodQueue.remove(), queue.dequeue());
            }
            Assertions.assertEquals(goodQueue.isEmpty(), queue.isEmpty());
        }

        while (!queue.isEmpty()) {
            Assertions.assertFalse(goodQueue.isEmpty());
            Assertions.assertSame(goodQueue.remove(), queue.dequeue());
        }
        Assertions.assertTrue(goodQueue.isEmpty());
    }

    @Test
    public void test10() {
        testNOperations(10);
    }

    @Test
    public void test100() {
        testNOperations(100);
    }

    @Test
    public void test10000() {
        testNOperations(10000);
    }

    @Test
    public void testMillion() {
        testNOperations(1000000);
    }
}