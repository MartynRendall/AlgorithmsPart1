import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;

    public Deque() {
    }

    public boolean isEmpty() {

        return first == null;
    }

    public int size() {

        int size = 0;

        if (!isEmpty()) {
            Node n = first;
            while (n != null) {
                size++;
                n = n.next;
            }
        }

        return size;
    }

    public void addFirst(Item item) {

        if (item == null) {
            throw new NullPointerException("Cant add null item to deque");
        }

        if ( first == null ) {
            first = new Node();
            first.item = item;
            first.next = null;
        } else {
            Node n = new Node();

            n.item = item;
            n.next = first;

            first = n;
        }
    }

    public void addLast(Item item) {

        if (item == null) {
            throw new NullPointerException("Cant add null item");
        }

        if (isEmpty()) {
            addFirst(item);

        } else {

            Node lastNode = getLastNode();

            Node n = new Node();
            n.next = null;
            n.item = item;

            lastNode.next = n;

        }

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

        Node n = first;

        if (n.next != null) {
            first = n.next;
        }


        return n.item;
    }

    public Item removeLast() {

        if (isEmpty()) {
            throw new NoSuchElementException("No elements to remove!");
        }

        Node lastNode = first;
        Node previousNode = null;

        while (lastNode.next != null ) {

            previousNode = lastNode;
            lastNode = lastNode.next;
        }

        if (previousNode == null) {
            first = null; // if no previous node then we must be dealing with the first node, so remove it
        } else {
            previousNode.next = null;
        }
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
