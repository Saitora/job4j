package ru.job4j.junior.control;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class StoreTest {

    private List<Store.User> previous = new ArrayList<>();
    private List<Store.User> current = new ArrayList<>();

    @Before
    public void prepare() {
        previous.add(new Store.User(1, "First"));
        previous.add(new Store.User(2, "Second"));
        previous.add(new Store.User(3, "Third"));
        current.add(new Store.User(1, "First"));
        current.add(new Store.User(2, "Second"));
        current.add(new Store.User(3, "Third"));
    }

    @Test
    public void whenCallDiffOnEqualsListsShouldReturnZeroOnAllParameters() {
        Info info = Store.diff(previous, current);
        assertThat(
                (info.getAddedCount() == 0) && (info.getChangedCount() == 0) && (info.getDeletedCount() == 0),
                is(true)
        );
    }

    @Test
    public void whenChangedDeletedAddedOneElementShouldReturnCorrectValues() {
        current.set(0, new Store.User(1, "changed"));
        current.add(new Store.User(4, "Fourth"));
        current.remove(new Store.User(2, "Second"));
        Info info = Store.diff(previous, current);
        assertThat(
                (info.getAddedCount() == 1) && (info.getChangedCount() == 1) && (info.getDeletedCount() == 1),
                is(true)
        );
    }

}