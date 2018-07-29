package ru.job4j.junior.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class LinkedListContainer<E> implements Iterable<E> {

    private SimpleArrayList<E> container = new SimpleArrayList<>();
    private int modCount = 0;

    public void add(E... value) {
        for (E v : value) {
            container.addToEnd(v);
            modCount++;
        }
    }

    public E get(int index) {
        return container.get(index);
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListContainerIterator<>(modCount, container);
    }

    private class LinkedListContainerIterator<E> implements Iterator<E> {

        private final int iteratorModCount;
        private final Iterator<E> iter;

        LinkedListContainerIterator(final int modCount, final SimpleArrayList<E> container) {
            iteratorModCount = modCount;
            iter = container.iterator();
        }

        @Override
        public boolean hasNext() {
            return iter.hasNext();
        }

        @Override
        public E next() {
            if (iteratorModCount != modCount) {
                throw new ConcurrentModificationException();
            }
            return iter.next();
        }
    }

}
