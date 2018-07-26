package ru.job4j.junior.chapter001.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenNumbersIterator implements Iterator<Integer> {

    private final int[] innerArray;
    int index;

    public EvenNumbersIterator(final int[] array) {
        innerArray = array;
    }

    @Override
    public boolean hasNext() {
        boolean result = false;
        while (index < innerArray.length) {
            if (innerArray[index] % 2 == 0) {
                result = true;
                break;
            } else {
                index++;
            }
        }
        return result;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return innerArray[index++];
    }
}
