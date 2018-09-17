package ru.job4j.junior.jdbc;

import org.junit.Test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class TrackerTest {

    @Test
    public void whenAddLessThan100ElementsTrackerShouldReturnNotNullValue() {
        Tracker tr = new Tracker();
        Item reference = null;
        for (int i = 0; i < 100; i++) {
            reference = tr.add(new Item(Integer.toString(i)));
        }
        assertThat(reference, is(notNullValue()));
    }

    @Test
    public void whenAddMoreThan100ElementsTrackerShouldReturnNull() {
        Tracker tr = new Tracker();
        Item reference = null;
        for (int i = 0; i < 101; i++) {
            reference = tr.add(new Item(Integer.toString(i)));
        }
        assertThat(reference, is(nullValue()));
    }

    @Test
    public void whenDeleteLastElementShouldMakeArrayEmpty() {
        Tracker tr = new Tracker();
        Item it = new Item("name");
        tr.add(it);
        tr.delete(it.getId());
        assertThat(tr.getSize(), is(0));
    }

    @Test
    public void whenFindAllShouldReturnArrayWithoutNull() {
        Tracker tr = new Tracker();
        tr.add(new Item("name1"));
        tr.add(new Item("name2"));
        tr.add(new Item("name3"));
        assertThat(tr.findAll().length, is(tr.getSize()));
    }

    @Test
    public void whenSearchByNameShouldReturnCorrectElement() {
        Tracker tr = new Tracker();
        tr.add(new Item("name_1"));
        tr.add(new Item("name_to_search"));
        tr.add(new Item("name_2"));
        Item[] result = tr.findByName("name_to_search");
        assertThat(result.length == 1 && result[0].getName().equals("name_to_search"), is(true));
    }

    @Test
    public void whenSearchByIdShouldReturnCorrectElement() {
        Tracker tr = new Tracker();
        Item it = new Item("item_to_search");
        tr.add(new Item("name_1"));
        tr.add(new Item("name_2"));
        tr.add(it);
        Item result = tr.findById(it.getId());
        assertThat(result.getName().equals("item_to_search"), is(true));
    }

    @Test
    public void whenReplaceShouldChangeItemById() {
        Tracker tr = new Tracker();
        Item baseIt = new Item("base_item");
        Item changedIt = new Item("changed_item");
        tr.add(baseIt);
        tr.replace(baseIt.getId(), changedIt);
        assertThat(tr.findAll()[0].getId().equals(changedIt.getId()), is(true));
    }

}