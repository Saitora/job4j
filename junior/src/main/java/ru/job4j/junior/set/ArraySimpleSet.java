package ru.job4j.junior.set;

import ru.job4j.junior.list.DynamicArrayList;

import java.util.Iterator;

public class ArraySimpleSet<E> implements Iterable<E> {

    private DynamicArrayList<E> list = new DynamicArrayList<>();

    public void add(E e) {
        if (!hasElement(e)) {
            list.add(e);
        }
    }

    private boolean hasElement(E e) {
        boolean flag = false;
        for (E el : list) {
            if (el.equals(e)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

}
