package ru.job4j.junior.multithreading.monitore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.junior.list.LinkedListContainer;

import java.util.Iterator;

@ThreadSafe
public class ConcurrentLinkedListContainer<E> implements Iterable<E> {

    @GuardedBy("this")
    private final LinkedListContainer<E> list = new LinkedListContainer<>();

    public synchronized void add(E value) {
        list.add(value);
    }

    public synchronized E get(int index) {
        return list.get(index);
    }

    @Override
    public synchronized Iterator<E> iterator() {
        return list.iterator();
    }

}
