package ru.job4j.junior.multithreading.waitnotify;

public class Consumer implements Runnable {

    private boolean isRunning = false;
    private final SimpleBlockingQueue<String> queue;

    public Consumer(SimpleBlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {
        isRunning = true;
        while (isRunning) {
            try {
                System.out.println(new StringBuilder("Consumer[").append(this).append("]=").append(queue.poll()));
            } catch (InterruptedException e) {
                // NoOps
            }
        }
    }
}
