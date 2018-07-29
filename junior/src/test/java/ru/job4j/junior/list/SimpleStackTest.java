package ru.job4j.junior.list;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SimpleStackTest {

    @Test
    public void whenPollFromStackShouldReturnInRightOrder() {
        SimpleStack<String> st = new SimpleStack<>();
        st.push("1");
        st.push("2");
        st.push("3");
        String result = "";
        String item = st.poll();
        while (item != null) {
            result += item;
            item = st.poll();
        }
        assertThat(result, is("321"));
    }

}