import java.util.Iterator;

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

//        Iterator<Item> iter = iterator();
//        Node latest = null;
//        while (iter.hasNext()) {
//            latest = iter.next();
//        }


    }

    public Item removeFirst() {

        if (!isEmpty()) {
            Node n = first;

            if (n.next != null) {
                first = n.next;
            }


            return n.item;
        }

        return null;
    }

    public Item removeLast() {

        return null;
    }

    public Iterator<Item> iterator() {

        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public void remove() {
            // not supported!
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {

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
