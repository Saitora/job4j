package ru.job4j.junior.generic;

/**
 * @author Nikolaev Rostislav (mailto:rost_1989@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public interface Store<T extends Base> {

    void add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    T findById(String id);
}