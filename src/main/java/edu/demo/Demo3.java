package edu.demo;

import edu.implementation.DoublyLinkedList;

public class Demo3 {

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();
        DoublyLinkedList<Integer> list2 = new DoublyLinkedList<>();

        list.addLast(2);
        list.addLast(3);
        list.addLast(4);
        list.addLast(5);
        list.addLast(7);
        list.addLast(5);

        list.print();
        System.out.println("-----------");
        System.out.println("Elements removed: " + list2.howManyOf(10));
        list.print();

    }

}
