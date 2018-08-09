package ru.job4j.junior.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node<E extends Comparable<E>> {
    private final List<Node<E>> children = new ArrayList<>();
    private final E value;

    public Node(final E value) {
        this.value = value;
    }

    public boolean add(Node<E> child) {
        boolean result = false;
        if (!this.children.contains(child)) {
            this.children.add(child);
            result = true;
        }
        return result;
    }

    public List<Node<E>> leaves() {
        return this.children;
    }

    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }

    public E getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node<?> node = (Node<?>) o;
        return Objects.equals(children, node.children)
               && Objects.equals(getValue(), node.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(children, getValue());
    }

    @Override
    public String toString() {
        return "Node{"
                + "children=" + children
                + ", value=" + value + '}';
    }
}