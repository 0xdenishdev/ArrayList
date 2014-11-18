import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Ninjava on 14.11.2014.
 */

public class MyArrayListTest {

    private List<Integer> list;

    @Before
    public void init() {
        list = new MyArrayList<Integer>();
    }

    @Test
    public void testSizeWithEmptyList() {
        assertEquals(0, list.size());
    }

    @Test
    public void testIsEmpty() {
        assertEquals(true, list.isEmpty());
    }

    @Test
    public void testIsEmptyList() {
        assertTrue(new ArrayList().isEmpty());
    }

    @Test
    public void testIsNotEmpty() {
        list.add(1);
        assertFalse(list.isEmpty());
    }

    @Test
    public void testAdd() {
        list.add(1);
        assertEquals(1, list.size());
    }

    @Test
    public void testAddMoreThanSize() {
        for (int i = 0; i < 11; i++) {
            list.add(i);
        }
        assertEquals(11, list.size());
    }

    @Test
    public void testContains() {
        list.add(1);
        assertTrue(list.contains(1));
    }

    @Test
    public void testNotContains() {
        assertFalse(list.contains(1));
    }

    @Test
    public void testClear() {
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        list.clear();
        assertEquals(0, list.size());
    }

    @Test
    public void testClearWithElement() {
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        list.clear();
        list.add(1);
        assertEquals(1, list.size());
    }

    @Test
    public void testToArray() {
        list.add(1);
        list.add(2);
        list.add(3);
        Object[] newArray = list.toArray();
        assertEquals(Arrays.asList(newArray), list);
    }

    @Test
    public void testGet() {
        list.add(1);
        list.add(2);
        assertEquals(2, (int) list.get(1));
    }

    @Test
    public void testNoneGet() {
        list.add(1);
        list.add(2);
        try {
            list.get(4);
        } catch (NullPointerException e) {}
    }

    @Test
    public void testIndexOf() {
        list.add(1);
        list.add(2);
        list.add(3);
        assertEquals(0, list.indexOf(new Integer(1)));
    }

    @Test
    public void testNoSuchElementIndexOf() {
        list.add(1);
        assertEquals(-1, list.indexOf(new Integer(4)));
    }

    @Test
    public void testLastIndexOf() {
        list.add(1);
        list.add(2);
        list.add(1);
        list.add(1);
        assertEquals(3, list.lastIndexOf(1));
    }

    @Test
    public void testRetainAll() {
        ArrayList<Integer> list1  = new ArrayList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        assertTrue(list.retainAll(list1));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testNotRetainAll() {
        ArrayList<Integer> list1  = new ArrayList<Integer>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        list.add(1);
        list.retainAll(list1);
    }

    @Test
    public void testContainsAll() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        assertTrue(list.containsAll(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void testNotContainsAll() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        assertFalse(list.containsAll(Arrays.asList(1, 2, 6)));
    }

    @Test
    public void testAddAll() {
        list.add(1);
        list.add(2);
        assertTrue(list.addAll(Arrays.asList(1, 2, 3)));
    }

    @Test
    public void testAddAllByIndex() {
        list.add(1);
        list.add(2);
        assertTrue(list.addAll(1, Arrays.asList(1, 2, 3)));
    }

    @Test
    public void testAddAllByElementIndex() {
        list.add(1);
        list.add(2);
        list.addAll(0, Arrays.asList(1, 2, 3));
        assertEquals(2, (int) list.get(4));
    }

    @Test
    public void testRemoveByIndex() {
        list.add(1);
        list.add(2);
        list.remove(1);
        assertFalse(list.contains(2));
    }

    @Test
    public void testRemoveByObject() {
        list.add(1);
        list.add(2);
        assertTrue(list.remove(new Integer(2)));
    }

    @Test
    public void testSubList() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        assertEquals(Arrays.asList(1, 2), list.subList(0, 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubListIllegalArgument() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        assertEquals(Arrays.asList(2, 3), list.subList(3, 2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSubListIndexOutOfBounds() {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        assertEquals(Arrays.asList(2, 3), list.subList(1, 8));
    }

    @Test
    public void testTrimToSize() {
        MyArrayList<Integer> list1 = new MyArrayList<Integer>();
        list1.add(1);
        list1.add(2);
        assertTrue(list1.trimToSize());
    }

    @Test
    public void testEmptyListTrimToSize() {
        MyArrayList<Integer> list = new MyArrayList(5);
        list.trimToSize();
        assertEquals(0, list.size());
    }

    @Test
    public void testSet() {
        list.add(1);
        list.add(0);
        list.add(3);
        assertEquals(0, (int) list.set(1, 2));
    }

    @Test
    public void testAddByIndex() {
        list.add(1);
        list.add(3);
        list.add(4);
        list.add(1, 2);
        assertEquals(Arrays.asList(1, 2, 3, 4), Arrays.asList(1, 2, 3, 4));
    }


    @Test
    public void testIterator() {
        list.add(1);
        list.add(2);
        ListIterator<Integer> iter = list.listIterator();
        assertTrue(iter.hasNext());
    }

    @Test
    public void testNoSuchIterator() {
        ListIterator<Integer> iter = list.listIterator();
        assertFalse(iter.hasNext());
    }

    @Test
    public void testIteratorGetElement() {
        list.add(1);
        list.add(2);
        ListIterator<Integer> iter = list.listIterator();
        assertEquals(1, (int) iter.next());
    }

    @Test
    public void testNoSuchElementIterator() {
        ListIterator<Integer> iter = list.listIterator();
        try {
            iter.next();
        } catch (NoSuchElementException e) {
            assertEquals(e.getMessage(), null);
        }
    }

    @Test
    public void testIndexIterator() {
        list.add(1);
        list.add(2);
        assertEquals(2, (int) list.listIterator(1).next());
    }

    @Test
    public void testNextIndexIterator() {
        list.add(1);
        list.add(2);
        assertEquals(1, list.listIterator(1).nextIndex());
    }

    @Test
    public void testNoSuchIndexListIterator() {
        try {
            list.listIterator(1);
        } catch (IndexOutOfBoundsException e) {
            assertEquals(e.getMessage(), "Index: 1");
        }
    }

    @Test
    public void testHasPrevious() {
        list.add(1);
        list.add(2);
        list.add(3);
        ListIterator<Integer> iter = list.listIterator();
        assertFalse(iter.hasPrevious());
    }

    @Test
    public void testNextIndex() {
        list.add(1);
        list.add(2);
        list.add(3);
        ListIterator<Integer> iter = list.listIterator();
        assertEquals(0, iter.nextIndex());
    }

}
