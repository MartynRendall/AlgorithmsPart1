import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private int size;

    public Deque() {
        first = null;
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
        node.next = first;

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

        if (!isEmpty()) {
            getLastNode().next = node;
        } else {
            first = node;
        }

        size++;
    }

    private Node getLastNode() {
        Node lastNode = first;

        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }

        return lastNode;
    }

    public Item removeFirst() {

        if (isEmpty()) {
            throw new NoSuchElementException("No element exists");
        }

        Node node = first;

        if (first.next != null) {
            first = node.next;
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

        Node lastNode = first;
        Node secondLastNode = null;

        while (lastNode.next != null) {

            secondLastNode = lastNode;
            lastNode = lastNode.next;
        }

        if (secondLastNode == null) {
            first = null; // if no previous node then we must be dealing with the first node, so remove it
        } else {
            secondLastNode.next = null;
        }

        size--;

        return lastNode.item;
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
    }
}
