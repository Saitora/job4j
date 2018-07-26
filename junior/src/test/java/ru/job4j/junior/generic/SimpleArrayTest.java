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

    @Test
    public void whenRemoveWrongIndexShouldReturnFalse() {
        SimpleArray<String> sa = new SimpleArray<>(1);
        boolean deleteResult = sa.delete(0);
        assertThat(deleteResult, is(false));
    }

    @Test
    public void whenRemoveRightIndexShouldReturnTrue() {
        SimpleArray<String> sa = new SimpleArray<>(1);
        sa.add("1");
        boolean deleteResult = sa.delete(0);
        assertThat(deleteResult, is(true));
    }

    @Test
    public void whenSetToEmptyArrayShouldReturnFalse() {
        SimpleArray<String> sa = new SimpleArray<>(1);
        boolean setResult = sa.set(1, "1");
        assertThat(setResult, is(false));
    }

    @Test
    public void whenSetToCorrectIndexShouldReturnTrue() {
        SimpleArray<String> sa = new SimpleArray<>(1);
        sa.add("0");
        boolean setResult = sa.set(0, "1");
        assertThat(setResult, is(true));
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
        for (String el : sa) {
            result += el;
        }
        assertThat(result, is("12345"));
    }
}
