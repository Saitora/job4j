package ru.job4j.junior.list;

import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayListTest {

    private SimpleArrayList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    @Test
    public void whenDeleteFromNonEmptyListShouldReturnDataFromDeletedElement() {
        Integer data = list.get(0);
        Integer result = list.delete();
        assertThat(result, is(data));
    }

    @Test
    public void whenDeleteFromEmptyListShouldReturnNull() {
        list = new SimpleArrayList<>();
        Integer result = list.delete();
        assertThat(result, is((Integer) null));
    }
}