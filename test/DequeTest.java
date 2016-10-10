import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class DequeTest {


    Deque<String> testee;

    @Before
    public void before() {

        testee = new Deque<>();
    }

    @Test
    public void testIsEmpty() {



        assertTrue(testee.isEmpty());

        testee.addFirst("First item");

        assertFalse(testee.isEmpty());

    }

    @Test
    public void testAddFirst() {

        List<String> items = new ArrayList<>();

        items.add("One");
        items.add("Two");
        items.add("Three");
        items.add("Four");
        items.add("Five");
        items.add("Six");

        for (String item : items) {
            testee.addFirst(item);
        }

        Iterator<String> iterator = testee.iterator();
        int counter = items.size()-1;
        while (iterator.hasNext()) {

            assertEquals(items.get(counter), iterator.next());
            counter--;
        }
    }

    @Test
    public void testSize() {

        assertEquals(0, testee.size());

        testee.addFirst("One");

        assertEquals(1, testee.size());

        testee.addFirst("Two");

        assertEquals(2, testee.size());
    }

    @Test
     public void testRemoveFirst() {

        testee.addFirst("One");

        assertEquals("One", testee.removeFirst());

    }

    @Test
    public void testRemoveFirstReOrdersItems() {

        List<String> items = new ArrayList<>();

        items.add("One");
        items.add("Two");
        items.add("Three");

        for (String item : items) {
            testee.addFirst(item);
        }

        assertEquals("Three", testee.removeFirst());

        Iterator<String> iterator = testee.iterator();
        int counter = items.size()-2;
        while (iterator.hasNext()) {

            assertEquals(items.get(counter), iterator.next());
            counter--;
        }



    }

    @Test
    public void testRemoveFirstOnEmpty() {

        assertEquals("One", testee.removeFirst());

    }
}