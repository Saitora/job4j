package ru.job4j.junior.generic;

public class AbstractStore<T extends Base> implements Store<T> {

    private SimpleArray<T> innerArray;

    public AbstractStore(int size) {
        innerArray = new SimpleArray<>(size);
    }

    @Override
    public void add(T model) {
        innerArray.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        for (int i = 0; i < innerArray.getSize(); i++) {
            if (innerArray.get(i).getId().equals(id)) {
                result = innerArray.set(i, model);
                break;
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i < innerArray.getSize(); i++) {
            if (innerArray.get(i).getId().equals(id)) {
                result = innerArray.delete(i);
                break;
            }
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T result = null;
        for (T el : innerArray) {
            if (el.getId().equals(id)) {
                result = el;
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return innerArray.toString();
    }
}
