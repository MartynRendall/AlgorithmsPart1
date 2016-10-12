import edu.princeton.cs.algs4.StdRandom;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] elements;
    private int size;

    public RandomizedQueue() {

        elements = (Item[]) new Object[2];
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
            elements = Arrays.copyOf(elements, elements.length*2);
        }

        elements[size++] = item;
    }

    public Item dequeue() {

        if (isEmpty()) {
            throw new NoSuchElementException("Couldn't sample empty stack");
        }

        int randomIndex = StdRandom.uniform(size);
//        int randomIndex = 0;

        Item removed = elements[randomIndex];
        elements[randomIndex] = elements[size-1];
        elements[size-1] = null;
        size--;

        //TODO resize when arrary is too large

//        rebuildArray(randomIndex);



        return removed;
    }

    private void rebuildArray(int randomIndex) {

//        Item[] items = (Item[]) new Object[elements.length];
////        Item[] items = (Item[]) new Object[size];
//
//        int index = 0;
//
//        for (Item item : Arrays.copyOfRange(elements, 0, randomIndex)) {
//
//            items[index] = item;
//            index++;
//        }
//
//        for (Item item :  Arrays.copyOfRange(elements, randomIndex+1, size+1)) {
////        for (Item item :  Arrays.copyOfRange(elements, randomIndex+1, elements.length+1)) {
//
//            items[index] = item;
//            index++;
//        }
//
//        elements = items;

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
//            StdRandom.shuffle(elements);

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

//            Item element = elements[currentIndex++];

//            while (element == null) {
//                element = elements[currentIndex++];
//            }
//            return element;
            //return elements[currentIndex++];
            return elements[shuffledIndexes[currentIndex++]];
        }
    }
}
