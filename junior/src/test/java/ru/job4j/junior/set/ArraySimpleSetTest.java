package ru.job4j.junior.set;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ArraySimpleSetTest {

    @Test
    public void whenAddSameValuesShouldReturnOnlyOne() {
        ArraySimpleSet<String> set = new ArraySimpleSet<>();
        set.add("1");
        set.add("1");
        set.add("1");
        set.add("2");
        assertThat(String.join("", set), is("12"));
    }

}