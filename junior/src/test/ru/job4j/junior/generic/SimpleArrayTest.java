package ru.job4j.junior.generic;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayTest {

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenAddTooManyToArrayShouldThrowException() {
        SimpleArray<String> sa = new SimpleArray<>(0);
        sa.add("1");
    }

    @Test
    public void whenAddOneElementShouldReturnCorrectSize() {
        SimpleArray<String> sa = new SimpleArray<>(5);
        sa.add("1");
        sa.add("2");
        sa.add("3");
        assertThat(3, is(sa.getSize()));
    }

    @Test
    public void whenRemoveLastElementShouldBecomeEmpty() {
        SimpleArray<String> sa = new SimpleArray<>(5);
        sa.add("1");
        sa.add("2");
        sa.add("3");
        sa.delete(0);
        sa.delete(0);
        sa.delete(0);
        assertThat(sa.getSize(), is(0));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenRemoveWrongIndexShouldThrowException() {
        SimpleArray<String> sa = new SimpleArray<>(1);
        sa.delete(0);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenSetToEmptyArrayShouldThrowException() {
        SimpleArray<String> sa = new SimpleArray<>(1);
        sa.set(1, "1");
    }

    @Test
    public void whenSetTwoRightIndexShouldReturnCorrectValue() {
        SimpleArray<String> sa = new SimpleArray<>(1);
        sa.add("1");
        sa.set(0, "2");
        assertThat(sa.get(0), is("2"));
    }

    @Test
    public void whenIterateWithForEachShouldIteratesWithoutError() {
        SimpleArray<String> sa = new SimpleArray<>(5);
        sa.add("1");
        sa.add("2");
        sa.add("3");
        sa.add("4");
        sa.add("5");
        String result = "";
        for(String el : sa) {
            result += el;
        }
        assertThat(result, is("12345"));
    }
}
