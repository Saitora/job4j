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

    @Test
    public void whenAddToEndShouldBeCorrect() {
        SimpleArrayList<String> list = new SimpleArrayList<>();
        list.addToEnd("1");
        list.addToEnd("2");
        list.addToEnd("3");
        assertThat(String.join("", list), is("123"));
    }

    @Test
    public void whenListHasCyclesShouldReturnTrue() {
        SimpleArrayList.Node<String> first = new SimpleArrayList.Node<>("1");
        SimpleArrayList.Node<String> second = new SimpleArrayList.Node<>("2");
        first.next = second;
        second.next = first;
        boolean result = SimpleArrayList.hasCycles(first);
        assertThat(result, is(true));
    }

    @Test
    public void whenListHasNoCyclesShouldReturnFalse() {
        SimpleArrayList.Node<String> first = new SimpleArrayList.Node<>("1");
        SimpleArrayList.Node<String> second = new SimpleArrayList.Node<>("2");
        SimpleArrayList.Node<String> third = new SimpleArrayList.Node<>("3");
        first.next = second;
        second.next = third;
        boolean result = SimpleArrayList.hasCycles(first);
        assertThat(result, is(false));
    }

    @Test
    public void whenListHasInnerCyclesShouldReturnTrue() {
        SimpleArrayList.Node<String> first = new SimpleArrayList.Node<>("1");
        SimpleArrayList.Node<String> second = new SimpleArrayList.Node<>("2");
        SimpleArrayList.Node<String> third = new SimpleArrayList.Node<>("3");
        first.next = second;
        second.next = third;
        third.next = second;
        boolean result = SimpleArrayList.hasCycles(first);
        assertThat(result, is(true));
    }
}