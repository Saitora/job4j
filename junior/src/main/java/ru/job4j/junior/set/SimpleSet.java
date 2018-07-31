package ru.job4j.junior.set;

import ru.job4j.junior.list.SimpleArrayList;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {

    private SimpleArrayList<E> list = new SimpleArrayList<>();

    public void add(E e) {
        if (!hasElement(e)) {
            list.addToEnd(e);
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
