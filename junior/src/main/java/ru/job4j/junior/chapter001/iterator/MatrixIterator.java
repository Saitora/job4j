package ru.job4j.junior.chapter001.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This Iterator iterates through matrix from left to right and from top to bottom
 */
public class MatrixIterator implements Iterator {

    private final int[][] matrix;
    private int xIndex;
    private int yIndex;

    public MatrixIterator(final int[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public boolean hasNext() {
        return ((matrix.length > yIndex) && (matrix[yIndex].length > xIndex)) ? true : false;
    }

    @Override
    public Object next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int element = matrix[yIndex][xIndex++];
        if (matrix[yIndex].length == xIndex) {
            yIndex++;
            xIndex = 0;
        }
        return element;
    }
}
