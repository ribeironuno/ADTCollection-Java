package edu.demo;

import edu.implementation.*;

public class Demo1 {

    public static void main(String[] args) {

        Person p1 = new Person("AAA");
        Person p2 = new Person("BBB");
        Person p3 = new Person("CCC");
        Person p4 = new Person("DDD");

        DoublyLinkedList<Person> list = new DoublyLinkedList<>();

        list.addLast(p1);
        list.addLast(p2);
        list.addLast(p3);
        list.addLast(p4);

        list.print();

        list.removeLast();
        list.removeFirst();
        list.removeFirst();
        list.removeFirst();

        System.out.println("--------");

        list.print();
    }

}
