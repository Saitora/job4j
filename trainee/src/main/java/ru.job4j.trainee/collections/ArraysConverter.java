package ru.job4j.trainee.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArraysConverter {

    public static List<Integer> convertMultiArrayToList(final int[][] array) {
          return Arrays.stream(array).flatMapToInt(Arrays::stream).boxed().collect(Collectors.toList());
    }

}
