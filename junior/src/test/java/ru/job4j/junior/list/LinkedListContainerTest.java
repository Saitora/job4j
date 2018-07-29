package ru.job4j.junior.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LinkedListContainerTest {

    private LinkedListContainer<String> llc;

    @Before
    public void initialize() {
        llc = new LinkedListContainer<>();
    }

    @Test
    public void whenAddedFiveElementsShouldReturnAllFive() {
        llc.add("1", "2", "3", "4", "5");
        assertThat(String.join("", llc), is("12345"));
    }

    @Test
    public void whenCallGetShouldReturnCorrectElement() {
        llc.add("123");
        assertThat(llc.get(0), is("123"));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenCallGetWithWrongIndexShouldThrowException() {
        llc.get(0);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenModifyArrayAfterStartIterationIteratorShouldThrowException() {
        llc.add("1", "2", "3");
        Iterator<String> iter = llc.iterator();
        llc.add("4");
        iter.next();
    }
}