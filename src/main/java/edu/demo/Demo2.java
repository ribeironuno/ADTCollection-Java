package edu.demo;

import edu.implementation.DoublyLinkedList;
import edu.implementation.Person;

import java.util.Arrays;

public class Demo2 {

    public static void main(String[] args) {

        Person p1 = new Person("AAA");
        Person p2 = new Person("BBB");
        Person p3 = new Person("CCC");
        Person p4 = new Person("DDD");

        DoublyLinkedList<Person> list = new DoublyLinkedList<>();

        list.addFirst(p1);
        list.addLast(p2);
        list.addFirst(p3);


//         Person[] arr = list.getArray(0,3); -> ClassCastException
//        Object[] arr = list.toArray();
//        Object[] arr = list.toArrayAfter(22);
        Object[] arr = list.toArrayUntil(555);

        System.out.println("Size: " + arr.length + "\n" + Arrays.toString(arr));
    }
}
