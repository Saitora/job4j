package ru.job4j.junior.list;

public class SimpleQueue<E> {

    private SimpleArrayList<E> array = new SimpleArrayList<>();

    public E poll() {
        return array.delete();
    }

    public void push(E value) {
        array.addToEnd(value);
    }

}
