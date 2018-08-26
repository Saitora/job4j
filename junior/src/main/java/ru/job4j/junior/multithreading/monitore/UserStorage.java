package ru.job4j.junior.multithreading.monitore;

import net.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ThreadSafe
public class UserStorage {

    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    public int size() {
        return users.size();
    }

    public User get(int id) {
        return users.get(id);
    }

    public synchronized boolean add(final User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        boolean result = false;
        if (!users.containsKey(user.getId())) {
            users.put(user.getId(), user);
            result = true;
        }
        return result;
    }

    public synchronized boolean update(final User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        boolean result = false;
        if (users.containsKey(user.getId())) {
            result = true;
            users.put(user.getId(), user);
        }
        return result;
    }

    public synchronized boolean delete(final User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }
        boolean result = false;
        if (users.containsKey(user.getId())) {
           users.remove(user.getId());
           result = true;
        }
        return result;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        if (users.containsKey(fromId)
                && users.containsKey(toId)
                && amount > 0
                && fromId != toId
                && users.get(fromId).getAmount() >= amount) {
            User fromUser = users.get(fromId);
            User toUser = users.get(toId);
            fromUser.setAmount(fromUser.getAmount() - amount);
            toUser.setAmount(toUser.getAmount() + amount);
            result = true;
        }
        return result;
    }

    @Override
    public String toString() {
        return "UserStorage{"
                + "users=" + users
                + '}';
    }
}
