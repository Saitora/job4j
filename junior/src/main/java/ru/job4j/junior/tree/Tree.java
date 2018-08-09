package ru.job4j.junior.tree;

import java.util.*;

public class Tree<T extends Comparable<T>> implements SimpleTree<T> {

    private Node<T> root = null;

    public Tree(T value) {
        root = (Node<T>) new Node(value);
    }

    public void setRoot(Node<T> root) {
        this.root = root;
    }

    @Override
    public boolean add(T parent, T child) {
        Optional<Node<T>> parentNode = findBy(parent);
        boolean result = false;
        if (parentNode.isPresent()) {
            parentNode.get().add((Node<T>) new Node(child));
            result = true;
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new TreeIterator<T>(root);
    }

    @Override
    public Optional<Node<T>> findBy(T value) {
        Optional<Node<T>> rsl = Optional.empty();
        Queue<Node<T>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<T> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<T> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    private class TreeIterator<E extends Comparable<E>> implements Iterator<E> {

        private final Node<E> root;
        private Queue<Node<E>> queue = new LinkedList<>();
        private Iterator queueIterator;

        public TreeIterator(Node<E> root) {
            this.root = root;
            fillQueue(root);
            queueIterator = queue.iterator();
        }

        private void fillQueue(Node<E> n) {
            List<Node<E>> children = (List<Node<E>>) n.leaves();
            if (children.size() != 0) {
                children.forEach(this::fillQueue);
            }
            queue.add(n);
        }

        @Override
        public boolean hasNext() {
            return queueIterator.hasNext();
        }

        @Override
        public E next() {
            return ((Node<E>) queueIterator.next()).getValue();
        }

        public Node<E> nextNode() {
            return (Node<E>) queueIterator.next();
        }
    }

}
