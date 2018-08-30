package ru.job4j.junior.multithreading.nonblocking;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void whenUpdateInTwoDifferentThreadsShouldThrowExceptionInSecond() throws InterruptedException {
        Cache<User> c = new Cache<>();
        c.add(new User("Adam"));
        AtomicReference<Exception> ex = new AtomicReference<>();

        Thread firstThread = new Thread(
                () -> {
                    User u = c.get(1);
                    u.setName("foo");
                    synchronized (this) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    c.update(u);
                }
        );
        Thread secondThread = new Thread(
                () -> {
                    try {
                        User u = c.get(1);
                        try {
                            synchronized (this) {
                                this.notifyAll();
                            }
                            firstThread.join();
                            u.setName("bar");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        c.update(u);
                    } catch (OptimisticException e) {
                        ex.set(e);
                    }
                }
        );
        firstThread.start();
        Thread.sleep(100);
        secondThread.start();
        secondThread.join();
        assertThat(ex.get().getMessage(), is("version not match"));
    }

}