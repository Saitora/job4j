package ru.job4j.junior.chapter001.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {

    public static Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {

            Iterator<Integer> currentIterator;

            @Override
            public boolean hasNext() {
                boolean result = false;
                if ((currentIterator != null) && currentIterator.hasNext()) {
                    result = true;
                } else {
                    if (it.hasNext()) {
                        currentIterator = it.next();
                        result = this.hasNext();
                    }
                }
                return result;
            }

            @Override
            public Integer next() {
                Integer value;
                if (hasNext()) {
                    value = currentIterator.next();
                } else {
                    throw new NoSuchElementException();
                }
                return value;
            }
        };
    }

}
