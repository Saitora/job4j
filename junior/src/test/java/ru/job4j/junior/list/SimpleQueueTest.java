package ru.job4j.junior.list;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SimpleQueueTest {

    @Test
    public void whenPollFromQueueShouldReturnInRightOrder() {
        SimpleQueue<String> queue = new SimpleQueue<>();
        queue.push("1");
        queue.push("2");
        queue.push("3");
        String result = "";
        String item = queue.poll();
        while (item != null) {
            result += item;
            item = queue.poll();
        }
        assertThat(result, is("123"));
    }

}