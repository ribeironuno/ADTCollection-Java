package collections.implementation;

import collections.interfaces.QueueADT;

import java.util.NoSuchElementException;

public class CircularArrayQueue<T> implements QueueADT<T> {

    /**
     * Array that storages data.
     */
    private T[] arr;

    /**
     * Index of front element.
     */
    private int front;

    /**
     * Index of rear element.
     */
    private int rear;

    /**
     * Tells how many elements exists in array.
     */
    private int size;

    /**
     * Default initial value.
     */
    private static final int DEFAULT_INITIAL_SIZE = 5;

    /**
     * Creates an instance of CircularQueue with given capacity.
     *
     * @param initialCapacity initial capacity given. If there is a invalid number, the capacity will be the default value.
     */
    @SuppressWarnings("unchecked")
    public CircularArrayQueue(int initialCapacity) {
        if (initialCapacity <= 0)
            initialCapacity = DEFAULT_INITIAL_SIZE;

        this.front = this.rear = this.size = 0;
        this.arr = (T[]) new Object[initialCapacity];
    }

    /**
     * Creates an instance of CircularQueue with default capacity.
     */
    @SuppressWarnings("unchecked")
    public CircularArrayQueue() {
        this.front = this.rear = this.size = 0;
        this.arr = (T[]) new Object[DEFAULT_INITIAL_SIZE];
    }

    /**
     * "Expands" array in double size.
     */
    private void expand() {
        @SuppressWarnings("unchecked")
        T[] tmp = (T[]) new Object[this.arr.length * 2];
        int count = 0;

        int i = this.front; //We will start in front index
        do {
            tmp[count++] = this.arr[i];
            i = (i + 1) % this.arr.length;
        } while (i != this.front); //Until we reach the last position, 1 before front index

        this.front = 0; //we update the front index and rear
        this.rear = this.size;
        this.arr = tmp;
    }

    @Override
    public void enqueue(T element) {
        if ((this.rear + 1) % this.arr.length == this.front)  //Checks if array is full
            this.expand();

        this.arr[this.rear] = element;
        this.rear = (this.rear + 1) % this.arr.length;
        this.size++;
    }


    @Override
    public T dequeue() throws NoSuchElementException {
        if (this.isEmpty())
            throw new NoSuchElementException("Queue is empty");

        T result = this.arr[this.front];
        if (this.front == this.rear) {
            this.front = this.rear = 0;
        } else {
            this.front = (this.front + 1) % this.arr.length;
        }
        this.size--;
        return result;
    }

    @Override
    public T first() throws NoSuchElementException {
        if (this.isEmpty())
            throw new NoSuchElementException("Queue is empty");

        return this.arr[this.front];
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        String string = "";
        if (this.isEmpty()) {
            string = "Queue is empty!";
        } else {
            int i = this.front;
            while (i != this.rear) {
                string += this.arr[i].toString() + " ";
                i = (i + 1) % this.arr.length;
            }
        }
        return string;
    }
}
