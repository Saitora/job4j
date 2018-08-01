package ru.job4j.junior.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс SimpleArrayList.
 */
public class SimpleArrayList<E> implements Iterable<E> {

    private int size;
    private Node<E> first;
    private Node<E> last;

    public static boolean hasCycles(Node f) {
        if (f == null) {
            throw new IllegalArgumentException();
        }
        boolean result = true;
        Node tortoise = f;
        Node hare = f;
        do {
            if ((tortoise = tortoise.next) == null || (hare = hare.next) == null || (hare = hare.next) == null) {
                result = false;
                break;
            }
        } while (tortoise != hare);
        return result;
    }

    /**
     * Метод вставляет в начало списка данные.
     */
    public void add(E date) {
        if (date == null) {
            throw new IllegalArgumentException();
        }
        Node<E> newLink = new Node<>(date);
        if (last == null) {
            last = newLink;
        }
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Метод вставляет в конец списка данные
     */
    public void addToEnd(E date) {
        if (date == null) {
            throw new IllegalArgumentException();
        }
        Node<E> newLink = new Node<>(date);
        if (last == null) {
            last = newLink;
            first = newLink;
        } else {
            last.next = newLink;
            last = newLink;
        }
        this.size++;
    }

    /**
     * Метод удаления первого элемент в списке.
     */
    public E delete() {
        E result = null;
        if (first != null) {
            result = first.date;
            first = first.next;
            size--;
        }
        return result;
    }

    /**
     * Метод получения элемента по индексу.
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Метод получения размера коллекции.
     */
    public int getSize() {
        return this.size;
    }

    @Override
    public Iterator<E> iterator() {
        return new SimpleArrayListIterator<>(this);
    }

    private class SimpleArrayListIterator<E> implements Iterator<E> {

        private final SimpleArrayList<E> array;
        private Node<E> currentNode;

        public SimpleArrayListIterator(final SimpleArrayList<E> array) {
            this.array = array;
            currentNode = array.first;
        }

        @Override
        public boolean hasNext() {
            return (currentNode != null) ? true : false;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<E> result = currentNode;
            currentNode = currentNode.next;
            return result.date;
        }
    }

    /**
     * Класс предназначен для хранения данных.
     */
    public static class Node<E> {

        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }
}