package ru.job4j.junior.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UserTest {

    /**
     * hashcode not overridden
     */
    @Test
    public void whenPutTwoUserInMapShouldPrintTwoUsers() {
        Calendar birthday = Calendar.getInstance();
        birthday.set(1990, 1, 1);
        User userOne = new User("Biwis", birthday);
        birthday.set(1985, 5, 5);
        User userTwo = new User("Buthead", birthday);
        Map<User, String> m = new HashMap<>();
        m.put(userOne, "first");
        m.put(userTwo, "second");
        System.out.println(m);
        assertThat(m.size(), is(2));
    }
}