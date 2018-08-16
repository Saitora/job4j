package ru.job4j.junior.multithreading.jmm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static ru.job4j.junior.multithreading.jmm.ConcurrentUtils.stop;

public class ConcurrentAccess {

    public int count = 0;

    // should be synchronized
    public void increment() {
        count = count + 1;
    }

    public void run() {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 100000)
                .forEach(i -> executor.submit(this::increment));

        stop(executor);

        System.out.println(count);
    }

    public static void main(String[] args) {
        ConcurrentAccess ca = new ConcurrentAccess();
        ca.run();
    }

}
