package edu.demo;

import collections.implementation.MyLinkedList;

import java.util.NoSuchElementException;

public class DemoLinkedList {

    public static void main(String[] args) {
        try {
            MyLinkedList<Integer> list = new MyLinkedList<>();

            list.add(null);
            list.add(2);
            list.add(10);
            list.add(7);
            list.add(6);
            list.add(4);
            list.add(3);
            System.out.println(list.toString());

            System.out.println("--------");

            list.remove(2);
            list.remove(7);
            list.remove(6);
            list.remove(4);
            list.remove(3);
            list.remove(10);
            list.remove(null);
            System.out.println(list.toString());

        } catch (NoSuchElementException | NullPointerException ex) {
            System.out.println(ex.toString());
        }
    }

}
