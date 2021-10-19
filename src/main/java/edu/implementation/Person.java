package edu.implementation;

public class Person {

    private String name;

    private static int count = 0;

    private int id;

    public Person(String name) {
        this.name = name;
        this.id = Person.count++;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || ((Person) obj).getId() == this.id;
    }

    public int getId(){
        return this.id;
    }

    @Override
    public String toString() {
        return "Person -> Id: " + this.id + "| Name: " + this.name;
    }
}
