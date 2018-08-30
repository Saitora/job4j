package ru.job4j.junior.multithreading.nonblocking;

import java.util.concurrent.ConcurrentHashMap;

public class Cache<T extends Base> {

    private ConcurrentHashMap<Integer, T> map = new ConcurrentHashMap<>();

    public T get(int id) {
        return (T) map.get(id).getCopy();
    }

    public void add(T model) {
        map.put(model.getId(), model);
    }

    public void update(T model) {
        map.computeIfPresent(model.getId(), (key, currentModel) -> {
            if (model.getVersion() != currentModel.getVersion()) {
                throw new OptimisticException("version not match");
            }
            model.incVersion();
            return model;
        });
    }

    public void delete(T model) {

    }

    @Override
    public String toString() {
        return "Cache{" + "map=" + map + '}';
    }
}
