package ru.job4j.junior.jdbc;

import java.sql.Timestamp;
import java.util.Arrays;

public class Tracker {

    private static final int ARR_SIZE = 100;
    private final Item[] items = new Item[ARR_SIZE];
    private int lastIndex = -1;

    public Item add(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        item.setId(generateId());
        Item result = null;
        if (lastIndex != (ARR_SIZE - 1)) {
            items[++lastIndex] = item;
            result = item;
        }
        return result;
    }

    public void replace(String id, Item item) {
        for (int i = 0; i < lastIndex + 1; i++) {
            if (items[i].getId().equals(id)) {
                if (item.getId() == null) {
                    item.setId(generateId());
                }
                items[i] = item;
                break;
            }
        }
    }

    public void delete(String id) {
        for (int i = 0; i < lastIndex + 1; i++) {
            if (items[i].getId().equals(id)) {
                System.arraycopy(items, i + 1, items, i, ARR_SIZE - (i + 1));
                lastIndex--;
                break;
            }
        }
    }

    public int getSize() {
        return lastIndex + 1;
    }

    public Item[] findAll() {
        Item[] all = new Item[lastIndex + 1];
        System.arraycopy(items, 0, all, 0, lastIndex + 1);
        return all;
    }

    public Item[] findByName(String key) {
        Tracker found = new Tracker();
        for (int i = 0; i < lastIndex + 1; i++) {
            if (items[i].getName().equals(key)) {
                found.add(items[i]);
            }
        }
        return found.findAll();
    }

    public Item findById(String id) {
        Item foundItem = null;
        for (int i = 0; i < lastIndex + 1; i++) {
            if (items[i].getId().equals(id)) {
                foundItem = items[i];
                break;
            }
        }
        return foundItem;
    }

    private String generateId() {
        long time = new Timestamp(System.currentTimeMillis()).getTime();
        return Long.toString(time + (long) (Math.random() * 1000));
    }

    @Override
    public String toString() {
        return "Tracker{" + "items=" + Arrays.toString(items) + ", lastIndex=" + lastIndex + '}';
    }
}
