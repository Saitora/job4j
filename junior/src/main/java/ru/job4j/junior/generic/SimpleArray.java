package ru.job4j.junior.generic;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterable<T> {

    Object[] array;
    int currentEnd = 0;
    int size = 0;

    public SimpleArray(int size) {
        array = new Object[size];
        this.size = size;
    }

    public void add(T model) {
        if (isFull()) {
            throw new ArrayIndexOutOfBoundsException();
        } else {
            array[currentEnd++] = model;
        }
    }

    public boolean set(int index, T model) {
        boolean result = false;
        if (index >= 0 && index < currentEnd) {
            array[index] = model;
            result = true;
        }
        return result;
    }

    public boolean delete(int index) {
        boolean result = false;
        if (index >= 0 && index < currentEnd) {
            System.arraycopy(array, index + 1, array, index, array.length - 1 - index);
            array[size - 1] = null;
            currentEnd--;
            result = true;
        }
        return result;
    }

    public T get(int index) {
        if (index < 0 || index >= currentEnd) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator(array, currentEnd);
    }

    @Override
    public String toString() {
        String result = "[";
        for (Object el: array) {
            if (el == null) {
                result += "null, ";
            } else {
                result += el.toString() + ", ";
            }

        }
        if (size > 0) {
            result = result.substring(0, result.length() - 2);
        }
        result += "]";
        return result;
    }

    public String getRealToString() {
        String result = "[";
        return result;
    }

    public int getSize() {
        return currentEnd;
    }

    private boolean isFull() {
        return (currentEnd == size) ? true : false;
    }

    private class SimpleArrayIterator implements Iterator<T> {

        private Object[] array;
        private int size;
        private int currentIndex = 0;

        public SimpleArrayIterator(final Object[] array, int currentEnd) {
            this.array = array;
            this.size = currentEnd;
        }

        @Override
        public boolean hasNext() {
            return (currentIndex < size) ? true : false;
        }

        @Override
        public T next() {
            Object result = null;
            if (hasNext()) {
                result = array[currentIndex++];
            } else {
                throw new NoSuchElementException();
            }
            return (T) result;
        }

    }
}
