package ru.job4j.junior.set;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddSameValuesShouldReturnOnlyOne() {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("1");
        set.add("1");
        set.add("1");
        set.add("2");
        assertThat(String.join("", set), is("12"));
    }

}