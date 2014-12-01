import org.omg.CORBA.*;

import java.lang.Object;
import java.util.*;

/**
 * @author Ninjava
 * @see Collection
 */

public class MyArrayList<T> implements List<T> {

    /**
     * The size of the ArrayList
     * */
    private int size = 0;

    /**
     * The array buffer. Buffer into which the element of the ArrayList are stored.
     * */
    private Object[] data;

    /**
     * ArrayList constructor without params. You have to use this type of constructor if you want to create default capacity list.
     * */
    public MyArrayList() {
        data = new Object[10];
    }

    /**
     * ArrayList constructor with params. You have to use this type if you want to set another length of list.
     *
     * @param length the initial length of list.
     * */
    public MyArrayList(int length) {
        data = new Object[length];
    }

    /**
     * Returns the number of elements of the list.
     *
     * @return the number of elements of the list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns <tt>true</tt> if this list contains given element.
     *
     * @param o the element which have to check in list.
     * @return true if the list contains the given element.
     */
    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * Returns the iterator over the elements in this list.
     *
     * @return the iterator over the elements in this list.
     */
    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    /**
     * Returns the array of all elements which are in the list.
     *
     * @return the array of all elements which are in the list.
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    /**
     * Returns <tt>true</tt>  if the given element has been successfully added.
     *
     * @param t the element to be added of this list.
     * @return true if the given element has been successfully added.
     */
    @Override
    public boolean add(T t) {
        if (size >= 10) {
            data = new Object[(data.length * 3) / 2 + 1];
            data = Arrays.copyOfRange(data, 0, data.length);
        }
        data[size++] = t;
        return true;
    }

    /**
     * Returns <tt>true</tt> if the given element has been successfully removed from this list.
     *
     * @param o the element to be removed.
     * @return true if the given element has been successfully removed from this list.
     */
    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (contains(o)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Return <tt>true</tt> if this list contains all of the elements of given collection.
     *
     * @param c collection to be checked.
     * @return true if this list contains all of the elements of given collection.
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        Object[] newArray = c.toArray();
        int check = 0;
        for (int i = 0; i < newArray.length; i++) {
            if (contains(newArray[i]))
                check++;
        }
        if(check == newArray.length) {
            return true;
        }
        return false;
    }

    /**
     * Returns <tt>true</tt> if all of the elements of given collection has been successfully added to this list.
     *
     * @param c collection to be added.
     * @return true if all of the elements of given collection has been successfully added to this list.
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] newArray = c.toArray();
        int newSize = newArray.length;
        System.arraycopy(newArray, 0, data, size, newSize);
        size += newSize;
        return newSize != 0 ? true : false;
    }

    /**
     * Returns <tt>true</tt> if all of the elements of given collection has been successfully added to this list by index.
     *
     * @param index index by which the collection to be added to this list.
     * @param c collection to be added.
     * @return true if all of the elements of given collection has been successfully added to this list by index.
     */
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        Object[] newArray = c.toArray();
        int newSize = newArray.length;
        for (int i = index; i < newSize; i++) {
            add(i, (T) newArray[i]);
        }
        return true;
    }

    /**
     * Returns <tt>true</tt> if all of the elements of given collection has been successfully removed from this list.
     *
     * @param c collection to be removed.
     * @return true if all of the elements of given collection has been successfully removed from this list.
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        Object[] newArray = c.toArray();
        for (int i = 0; i < newArray.length; i++) {
            remove(newArray[i]);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Object[] newArray = c.toArray();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < newArray.length; j++) {
                if (data[i] != newArray[j])
                    remove(i);
            }
        }
        return true;
    }

    /**
     * Removes all elements from this list.
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    /**
     * Returns the element of the list by index.
     *
     * @param index index of getting element.
     * @return the element by the given index which is in range of the list indexes.
     */
    @Override
    public T get(int index) {
        return indexCheck(index) ? (T) data[index] : null;
    }

    /**
     * Returns <tt>true</tt> if the given index is in range of the list indexes.
     *
     * @param index index to be checked.
     * @return true if the given index is in range of the list indexes.
     */
    public boolean indexCheck(int index) {
        return (index >= 0 && index <= size) ? true : false;
    }

    /**
     * Replaces the element at the specified position in this list with
     * the specified element.
     *
     * @param index index of the element to replace.
     * @param element element to be stored at specified position.
     * @return the element previously at the specified position.
     */
    @Override
    public T set(int index, T element) {
        if (indexCheck(index)) {
            T previousValue = (T) data[index];
            data[index] = element;
            return previousValue;
        }
        return null;
    }

    /**
     * Inserts the specified element at the specified position in this
     * list.
     *
     * @param index index at which the specified element is to be inserted.
     * @param element element to be inserted.
     */
    @Override
    public void add(int index, T element) {
        Object[] newArray = new Object[size + 1];
        for (int i = 0; i < index; i++) {
            newArray[i] = data[i];
        }
        newArray[index] = element;
        for (int i = index + 1; i < newArray.length; i++) {
            newArray[i] = data[i - 1];
        }
        data = newArray;
        size++;
    }

    /**
     * Removes the element of specified position in this list.
     *
     * @param index index of the element to be removed.
     * @return the element that was previously removed from the list.
     * @throws java.lang.IndexOutOfBoundsException
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds exception.");
        }

        T oldValue = (T) data[index];
        int move = index - size - 1;
        if (move > 0) {
            System.arraycopy(data, index + 1, data, index, move);
        }

        data[--size] = null;
        return oldValue;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     *
     * @param o the element which index will be returned.
     * @return the index of the given element.
     */
    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     *
     * @param o the element which index will be returned.
     * @return the index of the given element.
     */
    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i++) {
            if (o.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }
    //---------------------------------------------Iterator-----------------------------------------------------------------
    @Override
    public ListIterator<T> listIterator() {
        return new MyListIter(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        indexCheck(index);
        return new MyListIter(index);
    }

    public class Itr implements Iterator<T> {

        int current = 0;
        int previous = -1;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public T next() {
            int index = current;
            if(index >= size) {
                throw new NoSuchElementException();
            }
            Object[] arr = MyArrayList.this.data;
            current = index + 1;
            previous = index;
            return (T) arr[previous];
        }

        @Override
        public void remove() {
            if(previous < 0) {
                throw new IllegalStateException();
            }

            try {
                MyArrayList.this.remove(previous);
                current = previous;
                previous = -1;
            } catch (IndexOutOfBoundsException e) {}
        }

    }

    public class MyListIter extends Itr implements ListIterator<T> {
        //private Itr iter = new Itr();

        public MyListIter(int index) {
            super();
            //iter.current = index;
            current = index;
        }

        @Override
        public boolean hasNext() {
            return current < size();
            //return iter.current < size();
        }

        @Override
        public T next() {
            int index = current;
            if(index >= size) {
                throw new NoSuchElementException();
            }
            Object[] arr = MyArrayList.this.data;
            current = index + 1;
            previous = index;
            return (T) arr[previous];
        }

        @Override
        public boolean hasPrevious() {
            return current != 0;
//            return iter.current != 0;
        }

        @Override
        public T previous() {
            return null;
        }

        @Override
        public int nextIndex() {
            return current;
//            return iter.current;
        }

        @Override
        public int previousIndex() {
            return current - 1;
//            return iter.current - 1;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(T t) {
//            if(iter.previous < 0) {
            if(previous < 0) {
                throw new IllegalStateException();
            }

            try {
//                MyArrayList.this.set(iter.previous, t);
                MyArrayList.this.set(previous, t);
            } catch (IndexOutOfBoundsException e) {}
        }

        @Override
        public void add(T t) {
            try {
//                int index = iter.current;
                int index = current;
                MyArrayList.this.add(index, t);
//                iter.current = index + 1;
                current = index + 1;
//                iter.previous = -1;
                previous = -1;
            } catch (IndexOutOfBoundsException e) {}
        }
    }

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("From index out of bounds.");
        }
        if (toIndex > size) {
            throw new IndexOutOfBoundsException("To index out of bounds.");
        }
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("From index must be less than to index.");
        }
        Object[] subList = new Object[toIndex - fromIndex];
        int index = 0;
        for (int i = fromIndex; i < toIndex; i++) {
            subList[index++] = data[i];
        }
        return (List<T>) Arrays.asList(subList);
    }

    /**
     * Trims the capacity of the list to the list's current size.
     */
    public boolean trimToSize() {
        if(data.length > size) {
            Arrays.copyOf(data, size);
            return true;
        }
        return false;
    }
}
