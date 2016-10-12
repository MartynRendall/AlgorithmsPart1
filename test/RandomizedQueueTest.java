
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RandomizedQueueTest {

    RandomizedQueue<String> testee;

    @Before
    public void setUp() {
        testee = new RandomizedQueue<>();
    }

    @Test
    public void enqueOnEmpty() {

        testee.enqueue("First");

        assertEquals(1, testee.size());
    }

    @Test
    public void enqueNonEmpty() {

        testee.enqueue("First");
        testee.enqueue("Second");

        assertEquals(2, testee.size());
    }

    @Test(expected = NullPointerException.class)
    public void enqueWithNullItem() {
        testee.enqueue(null);
    }

    @Test
    public void enqueBeyondDefaultSize() {

        testee.enqueue("One");
        testee.enqueue("Two");
        testee.enqueue("Three");

        assertEquals(3, testee.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void sampleWhenEmpty() {

        testee.sample();
    }

    @Test
    public void isEmptyTrueByDefault() {

        assertTrue(testee.isEmpty());
    }

    @Test
    public void isEmptyWhenNotEmpty() {

        testee.enqueue("First");

        assertFalse(testee.isEmpty());
    }

    @Test
    public void isEmptyWhenNotEmptyThenEmptied() {

        testee.enqueue("First");

        testee.dequeue();

        assertTrue(testee.isEmpty());
    }

    @Test
    public void sampleWithOneValue() {

        testee.enqueue("First");

        assertEquals("First", testee.sample());
    }

    @Test(expected = NoSuchElementException.class)
    public void dequeOnEmpty() {

        testee.dequeue();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void iteratorRemoveUnsupported() {
        testee.iterator().remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorNextWhenEmpty() {
        testee.iterator().next();
    }

    @Test
    public void testNextWithOneItem() {
        testee.enqueue("First");

        assertEquals("First", testee.iterator().next());
    }

    @Test
    public void iteratorHasNextOnEmpty() {
        assertFalse(testee.iterator().hasNext());
    }

    @Test
    public void iteratorHasNextNotEmpty() {
        testee.enqueue("First");

        assertTrue(testee.iterator().hasNext());
    }

    @Test
    public void iteratorHasNextMultipleItems() {
        testee.enqueue("First");
        testee.enqueue("Second");

        Iterator<String> iterator = testee.iterator();
        String next = iterator.next();
        assertTrue(next.equals("First") || next.equals("Second"));
        next = iterator.next();
        assertTrue(next.equals("First") || next.equals("Second"));
    }

    @Test
    public void dequeRemovesItem() {

        List<String> testData = new ArrayList<>(4);

        testData.add("One");
        testData.add("Two");
        testData.add("Three");
        testData.add("Four");

        for (String testEntry : testData) {
            testee.enqueue(testEntry);
        }

        testData.remove(testee.dequeue());

        Iterator<String> iter = testee.iterator();

        String item1 = iter.next();
        System.out.println(item1);
        assertTrue(testData.contains(item1));

        String item2 = iter.next();
        System.out.println(item2);
        assertTrue(testData.contains(item2));

        String item3 = iter.next();
        System.out.println(item3);
        assertTrue(testData.contains(item3));
    }

    @Test
    public void addingThenRemovingManyItems() {

        testee.enqueue("One");
        testee.enqueue("Two");
        testee.enqueue("Three");
        testee.enqueue("Four");
        testee.enqueue("Five");
        testee.enqueue("Six");
        testee.enqueue("Seven");
        testee.enqueue("Eight");
        testee.enqueue("Nine");
        testee.enqueue("Ten");

        testee.dequeue();
        testee.dequeue();
        testee.dequeue();
        testee.dequeue();
        testee.dequeue();
        testee.dequeue();
        testee.dequeue();
        testee.dequeue();
        testee.dequeue();

        assertEquals(1, testee.size());

    }

    @Test
    public void dequeTwoItems() {
        testee.enqueue("One");
        testee.enqueue("Two");

        testee.dequeue();

        assertEquals(1, testee.size());
    }

    @Test
    public void assessmentTest2a() {
        RandomizedQueue<Integer> testee = new RandomizedQueue<>();

        testee.enqueue(474);
        testee.enqueue(459);
        testee.dequeue();
        testee.dequeue();
        testee.enqueue(103);
    }
}