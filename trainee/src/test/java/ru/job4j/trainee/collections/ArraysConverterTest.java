package ru.job4j.trainee.collections;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ArraysConverterTest {

    @Test
    public void whenConvert2x2MatrixShouldReturnListWith4Elements() {
        int[][] matrix = {{1, 1}, {2, 2}};
        List<Integer> result = ArraysConverter.convertMultiArrayToList(matrix);
        assertThat(result.toString(), is("[1, 1, 2, 2]"));
    }

    @Test
    public void whenConvertJaggedArrayShouldReturnCorrectList() {
        int[][] jagged = {{1, 2, 3, 4, 5}, {6, 7, 8}};
        List<Integer> result = ArraysConverter.convertMultiArrayToList(jagged);
        assertThat(result.toString(), is("[1, 2, 3, 4, 5, 6, 7, 8]"));
    }

    @Test
    public void whenConvertEmptyMatrixShouldReturnEmptyList() {
        int[][] empty = {{}, {}};
        List<Integer> result = ArraysConverter.convertMultiArrayToList(empty);
        assertThat(result.toString(), is("[]"));
    }

}