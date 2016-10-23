import org.junit.Before;
import org.junit.Test;
import week2.Deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class DequeTest {


    Deque<String> testee;

    @Before
    public void before() {

        testee = new Deque<>();
    }


    @Test
    public void testIsEmptyStartsEmpty() {

        assertTrue(testee.isEmpty());
    }

    @Test
    public void testIsEmptyWhenItemsExist() {

        testee.addFirst("First item");

        assertFalse(testee.isEmpty());

    }

    @Test
    public void testSizeStartsAsZero() {

        assertEquals(0, testee.size());
    }

    @Test
    public void testSizeWithOneItem() {

        testee.addFirst("One");

        assertEquals(1, testee.size());
    }

    @Test
    public void testSizeWithMultipleItems() {

        testee.addFirst("One");
        testee.addFirst("Two");

        assertEquals(2, testee.size());
    }

    @Test
    public void testAddFirstWithOne() {

        testee.addFirst("One");

        assertEquals("One", testee.iterator().next());
    }

    @Test(expected = NullPointerException.class)
    public void testAddFirstWitNullItem() {

        testee.addFirst(null);
    }

    @Test
    public void testAddFirstWithMultiple() {

        testee.addFirst("One");
        testee.addFirst("Two");
        testee.addFirst("Three");

        Iterator<String> iterator = testee.iterator();
        assertEquals("Three", iterator.next());
        assertEquals("Two", iterator.next());
        assertEquals("One", iterator.next());
    }

    @Test
    public void addLastOnEmptyList() {

        testee.addLast("One");

        assertEquals(1, testee.size());
        assertEquals("One", testee.iterator().next());
    }

    @Test(expected = NullPointerException.class)
    public void addLastWithNullitem() {

        testee.addFirst("Boom");
        testee.addLast(null);
    }

    @Test
    public void addLastOnNonEmptyList() {

        testee.addFirst("Two");
        testee.addFirst("One");

        testee.addLast("Three");

        assertEquals(3, testee.size());
        Iterator<String> iterator = testee.iterator();
        assertEquals("One", iterator.next());
        assertEquals("Two", iterator.next());
        assertEquals("Three", iterator.next());
    }
    
    @Test
    public void testAddLastMultipleCalls() {
        
        testee.addLast("Second last");
        testee.addLast("Really last");
        
        assertEquals(2, testee.size());
        Iterator<String> iterator = testee.iterator();
        assertEquals("Second last", iterator.next());
        assertEquals("Really last", iterator.next());
        
    }


    @Test(expected = NoSuchElementException.class)
    public void testRemoveFirstWithNoItems() {

        testee.removeFirst();
    }

    @Test
    public void testRemoveFirstReturnsItem() {

        testee.addFirst("One");

        assertEquals("One", testee.removeFirst());
    }

    @Test
    public void testRemoveFirstReOrdersItems() {

        testee.addFirst("One");
        testee.addFirst("Two");

        testee.removeFirst();

        assertEquals("One", testee.iterator().next());
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveLastWithNoElements() {

        testee.removeLast();
    }

    @Test
    public void testRemoveLastWithOneElement() {

        testee.addFirst("One");

        assertEquals("One", testee.removeLast());
        assertEquals(0, testee.size());
    }

    @Test
    public void testRemoveLastWithMultipleElement() {

        testee.addLast("One");
        testee.addLast("Two");
        testee.addLast("Three");

        assertEquals("Three", testee.removeLast());
        assertEquals(2, testee.size());
    }

    @Test
    public void testRemoveLastCalledMultipleTimes() {

        testee.addLast("One");
        testee.addLast("Two");

        assertEquals("Two", testee.removeLast());
        assertEquals("One", testee.removeLast());
        assertEquals(0, testee.size());
    }

    @Test
    public void testIteratorHasNextWithNoItems () {

        assertFalse(testee.iterator().hasNext());
    }

    @Test
    public void testIteratorHasNextWithItem () {

        testee.addFirst("One");

        assertTrue(testee.iterator().hasNext());
    }

    @Test
    public void testIteratorHasNextAtLastItem () {

        testee.addLast("One");
        testee.addLast("Two");

        Iterator<String> iterator = testee.iterator();
        iterator.next();
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorHasNextHalfWayThrough () {

        testee.addLast("One");
        testee.addLast("Two");

        Iterator<String> iterator = testee.iterator();
        iterator.next();
        assertTrue(iterator.hasNext());
    }


    @Test(expected = UnsupportedOperationException.class)
    public void testIteratorRemove () {

        testee.iterator().remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorNextWithNoItems () {

        testee.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorNextWhenAtEnd () {

        testee.addLast("Last");
        Iterator<String> iterator = testee.iterator();
        iterator.next();
        iterator.next();
    }

    @Test
    public void testNextReturnsCorrectItem() {
        testee.addLast("One");
        testee.addLast("Two");

        Iterator<String> iterator = testee.iterator();
        assertEquals("One", iterator.next());
        assertEquals("Two", iterator.next());
    }

    @Test
    public void nonEmptyToEmptyBackToNonEmpty() {

        testee.addLast("Last");
        testee.removeLast();
        testee.addFirst("First");

        assertEquals(1, testee.size());
    }

    @Test
    public void failingTest2a() {

        Deque<Integer> testee = new Deque<>();

        assertTrue(testee.isEmpty());
        testee.addFirst(1);

        assertEquals(new Integer(1), testee.removeFirst());

        assertTrue(testee.isEmpty());
    }

    @Test
    public void failingTest2b() {

        Deque<Integer> testee = new Deque<>();

        testee.isEmpty();
        testee.addFirst(1);
        testee.removeFirst();
        testee.addFirst(3);
        testee.removeFirst();
        testee.addFirst(5);
        testee.removeFirst();


        assertTrue(testee.isEmpty());
    }

    @Test
    public void failingTest3() {
        Deque<Integer> testee = new Deque<>();

        testee.addFirst(0);
        testee.addFirst(1);
        testee.removeLast();
    }

    @Test
    public void failingTest3b() {
        Deque<Integer> testee = new Deque<>();

        testee.addFirst(0);
        testee.addFirst(1);
        testee.addFirst(2);
        testee.removeLast();
        testee.addFirst(4);
        testee.addFirst(5);
        testee.addFirst(6);
        testee.isEmpty();
        assertEquals(new Integer(1), testee.removeLast());
    }

    @Test
    public void memoryTesting() {

        testee.addLast("One");
        testee.addLast("Two");
        testee.addLast("Three");

        testee.removeLast();
        testee.removeLast();
        testee.removeLast();

    }
}