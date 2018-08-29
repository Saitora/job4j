package ru.job4j.junior.multithreading.waitnotify;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

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

    @Test
    public void whenProducedShouldBeFullyConsumed() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(
                () -> {
                    IntStream.range(0, 5).forEach(
                            queue::offer
                    );
                }
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer, is(Arrays.asList(0, 1, 2, 3, 4)));
    }

}