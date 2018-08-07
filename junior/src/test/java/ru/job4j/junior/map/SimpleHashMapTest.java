package ru.job4j.junior.map;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleHashMapTest {

    private SimpleHashMap<Integer, String> map;

    @Before
    public void prepare() {
         map = new SimpleHashMap<>();
    }

    @Test
    public void whenAddThreePairsToMapShouldHaveSizeThree() {
        map.insert(1, "one");
        map.insert(2, "two");
        map.insert(3, "three");
        map.insert(4, "four");
        map.insert(5, "five");
        map.insert(257, "big");
        map.insert(Integer.MAX_VALUE, "very big");
        assertThat(map.getSize(), is(7));
    }

    @Test
    public void whenGetValueByKeyShouldReturnCorrectValue() {
        map.insert(10, "ten");
        map.insert(20, "twenty");
        assertThat(map.get(10), is("ten"));
    }

    @Test
    public void whenDeleteExistingKeyShouldReturnTrue() {
        map.insert(10, "ten");
        map.insert(20, "twenty");
        assertThat(map.delete(10), is(true));
    }

    @Test
    public void whenDeleteExistingKeyShouldRemoveItFromMap() {
        map.insert(10, "ten");
        map.insert(20, "twenty");
        map.delete(10);
        assertThat(map.get(10), is((Integer) null));
    }

    @Test
    public void whenInsertOnExistingKeyShouldRewriteValue() {
        map.insert(10, "ten");
        map.insert(20, "twenty");
        map.insert(10, "not so ten");
        assertThat(map.get(10), is("not so ten"));
    }

    @Test
    public void whenAnotherTypeShouldWorkWell() {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        map.insert("one", "one");
        map.insert("two", "two");
        assertThat(map.getSize(), is(2));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenChangeMapAfterGetIteratorShouldThrowExceptionOnNextItem() {
        Iterator it = map.iterator();
        map.insert(1, "1");
        it.next();
    }

    @Test
    public void whenIterateShouldReturnCorrectValues() {
        map.insert(1, "1");
        map.insert(2, "2");
        map.insert(3, "3");
        String result = "";
        for (SimpleHashMap.Node n : map) {
            result += n.getKey().toString() + n.getValue();
        }
        assertThat(result, is("112233"));
    }

}