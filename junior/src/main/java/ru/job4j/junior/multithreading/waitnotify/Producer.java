package ru.job4j.junior.multithreading.waitnotify;

public class Producer implements Runnable {

    private boolean isRunning = false;
    private final SimpleBlockingQueue<String> queue;

    public Producer(SimpleBlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {
        isRunning = true;
        while (isRunning) {
            String value = Double.toString(Math.random());
            System.out.println(new StringBuilder("Producer[").append(this).append("]=").append(value));
            queue.offer(value);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
