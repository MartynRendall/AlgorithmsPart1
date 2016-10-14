import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] elements;
    private int size;
    private int initSize = 2;

    public RandomizedQueue() {

        elements = (Item[]) new Object[initSize];
        size = 0;
    }

    public boolean isEmpty() {

        return size < 1;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {

        if (item == null) {
            throw new NullPointerException("Cant enque null item");
        }

        if (elements.length == size) {
            // If elements.size is 0 then the copyOf would return a 0 sized array. We need to ensure the array does not drop below
            //  a size of initSize
            elements = Arrays.copyOf(elements, ((elements.length == 0) ? initSize : elements.length*2));
        }

        elements[size++] = item;
    }

    public Item dequeue() {

        if (isEmpty()) {
            throw new NoSuchElementException("Couldn't sample empty stack");
        }

        int randomIndex = StdRandom.uniform(size);

        Item removed = elements[randomIndex];
        elements[randomIndex] = elements[size-1];
        elements[size-1] = null;
        size--;

        if ((elements.length / 4) > size) {
            elements = Arrays.copyOfRange(elements, 0, size);
        }

        return removed;
    }

    public Item sample() {

        if (isEmpty()) {
            throw new NoSuchElementException("Couldn't sample empty stack");
        }

        return elements[StdRandom.uniform(size)];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int currentIndex = 0;
        private int[] shuffledIndexes = new int[size];

        public RandomizedQueueIterator() {

            for (int i = 0; i < shuffledIndexes.length; i++) {
                shuffledIndexes[i] = i;
            }

            StdRandom.shuffle(shuffledIndexes);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Remove not supported");
        }

        @Override
        public boolean hasNext() {
            return currentIndex < size();
        }

        @Override
        public Item next() {

            if (!hasNext()) {
                throw new NoSuchElementException("No next item");
            }

            return elements[shuffledIndexes[currentIndex++]];
        }
    }
}
