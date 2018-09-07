package ru.job4j.junior.multithreading.pool;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class ThreadPoolTest {

    @Test
    public void whenHaveJobsShouldWorkItCorrectly() throws InterruptedException {
        ThreadPool tp = new ThreadPool();
        tp.work(new Job());
        tp.work(new Job());
        tp.work(new Job());
        tp.work(new Job());
        tp.work(new Job());
        tp.work(new Job());
        tp.work(new Job());
        tp.work(new Job());
        tp.work(new Job());
        tp.work(new Job());
        Thread.sleep(1000);
        tp.shutdown();
    }

    static private class Job implements Runnable {

        private static AtomicInteger counter = new AtomicInteger();
        private Integer id = counter.incrementAndGet();

        @Override
        public void run() {
            System.out.println(
                            "".concat("[")
                            .concat(Long.toString(Thread.currentThread().getId()))
                            .concat("] job № ")
                            .concat(Integer.toString(id))
            );
        }
    }

}