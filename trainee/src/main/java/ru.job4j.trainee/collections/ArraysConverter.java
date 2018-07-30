package ru.job4j.trainee.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArraysConverter {

    public static List<Integer> convertMultiArrayToList(final int[][] array) {
        return Arrays.stream(array).collect(
                ArrayList::new,
                (arrList, intArr) -> {
                    for (int i : intArr) {
                        arrList.add(i);
                    }
                },
                ArrayList::addAll
        );
    }

}
