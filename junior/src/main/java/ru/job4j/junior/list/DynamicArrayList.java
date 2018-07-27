package ru.job4j.junior.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArrayList<E> implements Iterable<E> {

    private Object[] container = new Object[32];
    private int currentEnd = 0;
    private int modCount = 0;

    private void extendContainer() {
        Object[] newContainer = new Object[container.length * 2];
        System.arraycopy(container, 0, newContainer, 0, container.length);
        container = newContainer;
    }

    public void add(E value) {
        if (currentEnd == container.length) {
            extendContainer();
        }
        if (value != null) {
            container[currentEnd] = value;
            currentEnd++;
            modCount++;
        }
    }

    public E get(int index) {
        if (index < 0 || index >= currentEnd) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (E) container[index];
    }

    @Override
    public Iterator<E> iterator() {
        return new DynamicArrayIterator<E>(container, currentEnd, modCount);
    }

    private class DynamicArrayIterator<T> implements Iterator<T> {

        private final Object[] innerContainer;
        private int end;
        private int iteratorModCount;
        private int index = 0;

        public DynamicArrayIterator(final Object[] container, int currentEnd, int modCount) {
            innerContainer = container;
            end = currentEnd;
            iteratorModCount = modCount;
        }

        @Override
        public boolean hasNext() {
            boolean result = false;
            if (index < end) {
                result = true;
            }
            return result;
        }

        @Override
        public T next() {
            if (iteratorModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) innerContainer[index++];
        }

    }
}
