package ru.job4j.junior.multithreading.pool;

import ru.job4j.junior.multithreading.waitnotify.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();
    private int size = Runtime.getRuntime().availableProcessors();

    public ThreadPool() {
        createWorkers();
    }

    public void work(Runnable job) {
        tasks.offer(job);
    }

    public void shutdown() {
        for(Thread t : threads) {
            t.interrupt();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void createWorkers() {
        if (threads.size() < size) {
            for (int i = 0; i < size; i++) {
                Thread worker = new Thread(new Worker(tasks));
                threads.add(worker);
                worker.start();
            }
        }
    }

    private class Worker implements Runnable {

        private final SimpleBlockingQueue<Runnable> tasks;

        Worker(SimpleBlockingQueue<Runnable> tasks) {
            this.tasks = tasks;
            System.out.println(this);
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Runnable job = tasks.poll();
                    job.run();
                } catch (InterruptedException e) {
                    System.out.println("stop worker [".concat(Long.toString(Thread.currentThread().getId())).concat("]"));
                    Thread.currentThread().interrupt();
                }
            }
        }

        @Override
        public String toString() {
            return "Worker{".concat(super.toString()).concat("}");
        }
    }
}