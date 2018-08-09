package ru.job4j.junior.tree;

import org.junit.Test;

import java.util.LinkedList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenIterateShouldReturnCorrectValues() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        final StringBuilder chars = new StringBuilder();
        tree.forEach(l -> chars.append(l.toString()));
        assertThat(chars.toString(), is("236541"));
    }

    @Test
    public void whenCallIsBinaryShouldReturnTrueOnBinaryTree() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 4);
        tree.add(3, 5);
        assertThat(tree.isBinary(), is(true));
    }

    @Test
    public void whenCallIsBinaryShouldReturnFalseOnNonBinaryTree() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(3, 4);
        tree.add(3, 5);
        tree.add(3, 6);
        assertThat(tree.isBinary(), is(false));
    }

    @Test
    public void whenAddSameChildToParentShouldNotInsertIt() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        tree.add(1, 2);
        final StringBuilder chars = new StringBuilder();
        tree.forEach(l -> chars.append(l.toString()));
        assertThat(chars.toString(), is("21"));
    }
}