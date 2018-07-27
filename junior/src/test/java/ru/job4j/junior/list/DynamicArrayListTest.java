package ru.job4j.junior.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArrayListTest {

    private DynamicArrayList<String> dal;

    @Before
    public void initialize() {
        dal = new DynamicArrayList<>();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenCallNextOnDynamicArrayListIteratorWithNoElementsShouldThrowException() {
        dal.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCallNextOnDynamicArrayListIteratorAfterAddElementShouldThrowException() {
        dal.add("111");
        Iterator<String> iter = dal.iterator();
        dal.add("222");
        iter.next();
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenCallGetOnWrongIndexShouldThrowException() {
        dal.add("111");
        dal.get(100);
    }

    @Test
    public void whenMoreThan32ElemArrayShouldExpand() {
        for (int i = 0; i < 100; i++) {
            dal.add(new Integer(i).toString());
        }
    }

}