package week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;
    private Node last;
    private int size;

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {

        return first == null;
    }

    public int size() {

        return size;
    }

    public void addFirst(Item item) {

        if (item == null) {
            throw new NullPointerException("Cant add null item to deque");
        }

        Node node = new Node();
        node.item = item;
        node.previous = null;

        if (first != null) {
            node.next = first;
            first.previous = node;

            if (last == null) {
                last = first;
            }

        } else {
            node.next = null;
        }

        first = node;
        size++;
    }

    public void addLast(Item item) {

        if (item == null) {
            throw new NullPointerException("Cant add null item");
        }

        Node node = new Node();
        node.item = item;
        node.next = null;

        if (first == null) {
            first = node;

        } else {
            if (last == null) {
                node.previous = first;
                first.next = node;

            } else {
                node.previous = last;
                last.next = node;

            }

            last = node;
        }

        size++;
    }

    public Item removeFirst() {

        if (isEmpty()) {
            throw new NoSuchElementException("No element exists");
        }

        Node node = first;

        if (first.next != null) {
            first = first.next;
            first.previous = null;

            if (first == last) {
                last = null;
            }

        } else {
            first = null;
        }

        size--;

        return node.item;
    }

    public Item removeLast() {

        if (isEmpty()) {
            throw new NoSuchElementException("No elements to remove!");
        }

        Item lastItem;


        if (last == null) {
            lastItem = first.item;
            first = null;

        } else {

            lastItem = last.item;
            last = last.previous;
            last.next = null;

            if (last == first) {
                last = null;
            }
        }

        size--;

        return lastItem;
    }

    public Iterator<Item> iterator() {

        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported currently");
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {

            if (current == null) {
                throw new NoSuchElementException("No elements present in the deque");
            }

            Item item = current.item;
            current = current.next;

            return item;
        }
    }

    private class Node {

        public Item item;
        public Node next;
        public Node previous;
    }
}
