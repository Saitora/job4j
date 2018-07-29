package ru.job4j.junior.list;

public class SimpleStack<E> {

    private SimpleArrayList<E> array = new SimpleArrayList<>();

    public E poll() {
        return array.delete();
    }

    public void push(E value) {
        array.add(value);
    }

}
