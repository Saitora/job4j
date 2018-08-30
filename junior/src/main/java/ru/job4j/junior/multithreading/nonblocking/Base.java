package ru.job4j.junior.multithreading.nonblocking;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public abstract class Base {

    @GuardedBy("Base.class")
    private static final AtomicInteger COUNT = new AtomicInteger(0);
    @GuardedBy("this")
    private int id;
    @GuardedBy("this")
    private int version;

    public Base() {
        this.id = COUNT.incrementAndGet();
    }

    protected abstract Base getCopy();

    public synchronized int getId() {
        return id;
    }

    protected synchronized void setId(int id) {
        this.id = id;
    }

    public synchronized int getVersion() {
        return version;
    }

    protected synchronized void setVersion(int version) {
        this.version = version;
    }

    public synchronized void incVersion() {
        version++;
    }
}
