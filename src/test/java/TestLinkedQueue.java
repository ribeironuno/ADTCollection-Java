import collections.implementation.LinkedQueue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.*;

/*
#COPYRIGHT: 
This is a sample junit test file for the Queue class.
It exists only to verify that AwesomeQueue passes a series of tests designed to spot bugs.

Running a JUnit test:
Run it in Eclipse by going to the 'Run' menu, selecting 'Run Configurations', and double clicking 'JUnit test'.  If there are multiple junit files laying around, you may have to select which one you want.
Running it will execute all the methods marked with the '@Test' annotation, then give you a report of whether they passed or not.
For the most part, tests pass as long as they don't fail an assertion or throw an exception.
*/

public class TestLinkedQueue {

    private final LinkedQueue<Integer> queue = new LinkedQueue<>();

    @Test
    //the '@Test' annotation marks it as, surprisingly enough, a test.
    //each test will be run independently
    public void testIsEmptyOnEmpty() {
        Assertions.assertTrue(queue.isEmpty());
    }

    @Test
    public void testIsEmptyOnNotEmpty() {
        queue.enqueue(5);
        Assertions.assertTrue(!queue.isEmpty());
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
    //this checks to make sure that enqueueing then dequeueing doesn't break isEmpty()
    public void testEmptyAfterDequeue() {
        queue.enqueue(5);
        queue.dequeue();
        Assertions.assertTrue(queue.isEmpty());
    }


    //you can, of course, declare other methods besides tests; just exclude the '@Test'.
    //This one gets used by several tests that call it with different arguments.
    //this performs n random operations on an AwesomeQueue and java's LinkedList, and verifies that they end up the same.
    //this is useful for testing because 1) it can test for very large examples and 2) it tests enqueues and dequeues in all sorts of interleaved orderings, possibly picking out obscure bugs
    //Note: this uses random values, which means that running tests different times could have different results (if, say, there is a bug in your code but hitting it is rare)
    private void testNOperations(int n) {
        Random r = new Random();
        LinkedList<Integer> goodQueue = new LinkedList<Integer>();
        int num;
        for (int i = 0; i < n; i++) {
            //enqueue element if queue is empty, or on a 2/3 chance
            if (queue.isEmpty() || r.nextInt(3) < 2) {
                num = r.nextInt(100);
                queue.enqueue(num);
                goodQueue.add(num);
            } else //dequeue
            {
                Assertions.assertTrue(goodQueue.remove() == queue.dequeue());
            }
            //using different asserts, such as assertEquals, can clarify your intent to people reading your code
            //technically, when using assertEquals, you're supposed to put the expected value first, then the actual value
            Assertions.assertEquals(goodQueue.isEmpty(), queue.isEmpty());
        }

        //now that we're done going through n operations, dequeue until empty and compare results
        while (!queue.isEmpty()) {
            Assertions.assertTrue(!goodQueue.isEmpty());
            Assertions.assertTrue(goodQueue.remove() == queue.dequeue());
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