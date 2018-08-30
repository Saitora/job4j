package ru.job4j.junior.multithreading.nonblocking;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class User extends Base {

    @GuardedBy("this")
    private String name;

    public User(String name) {
        super();
        this.name = name;
    }

    public User(User u) {
        setId(u.getId());
        name = u.getName();
        setVersion(u.getVersion());
    }

    public synchronized String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    @Override
    public synchronized String toString() {
        return "User{" + "name='" + name + '\''
                + ", id=" + getId()
                + ", version=" + getVersion() + '}';
    }

    @Override
    protected Base getCopy() {
        return new User(this);
    }
}
