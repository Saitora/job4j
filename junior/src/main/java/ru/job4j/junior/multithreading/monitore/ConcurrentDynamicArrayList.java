package ru.job4j.junior.multithreading.monitore;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.junior.list.DynamicArrayList;

import java.util.Iterator;

@ThreadSafe
public class ConcurrentDynamicArrayList<E> implements Iterable<E> {

    @GuardedBy("this")
    private final DynamicArrayList<E> array = new DynamicArrayList<>();

    public synchronized void add(E value) {
        array.add(value);
    }

    public synchronized E get(int index) {
        return array.get(index);
    }

    @Override
    public synchronized Iterator<E> iterator() {
        return array.iterator();
    }
}
