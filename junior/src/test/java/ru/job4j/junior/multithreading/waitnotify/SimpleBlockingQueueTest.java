package ru.job4j.junior.multithreading.waitnotify;

import org.junit.Test;

public class SimpleBlockingQueueTest {

    @Test
    public void whenProducerStopsConsumerShouldStopToo() throws InterruptedException {
        SimpleBlockingQueue<String> queue = new SimpleBlockingQueue<>();
        Producer pr = new Producer(queue);
        Consumer con = new Consumer(queue);
        Thread prThread = new Thread(pr);
        Thread conThread = new Thread(con);
        prThread.start();
        conThread.start();

        Thread.sleep(3 * 1000);

        pr.stop();
        con.stop();
        prThread.join();
        conThread.interrupt();
        conThread.join();
    }

}