package ru.job4j.junior.tree;

import java.util.Optional;

public interface SimpleTree<T extends Comparable<T>> extends Iterable<T> {
    /**
     * Добавить элемент child в parent.
     * Parent может иметь список child.
     * @param parent parent.
     * @param child child.
     * @return
     */
    boolean add(T parent, T child);

    Optional<Node<T>> findBy(T value);
}

