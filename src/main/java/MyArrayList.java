import org.omg.CORBA.*;

import java.lang.Object;
import java.util.*;

/**
 * Created by Ninjava on 14.11.2014.
 */

public class MyArrayList<T> implements List<T> {

    private int size = 0;
    private Object[] data;

    public MyArrayList() {
        data = new Object[10];
    }

    public MyArrayList(int length) {
        data = new Object[length];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Itr();
    }

    @Override
    public Object[] toArray() {
        /*Object[] toArray = new Object[size];
        for (int i = 0; i < size; i++) {
            toArray[i] = data[i];
        }
        return toArray;*/
        return Arrays.copyOf(data, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        if (size >= 10) {
            data = new Object[(data.length * 3) / 2 + 1];
            data = Arrays.copyOfRange(data, 0, data.length);
        }
        data[size++] = t;
        return true;
    }

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

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] newArray = c.toArray();
        int newSize = newArray.length;
        System.arraycopy(newArray, 0, data, size, newSize);
        size += newSize;
        return newSize != 0 ? true : false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        Object[] newArray = c.toArray();
        int newSize = newArray.length;
        for (int i = index; i < newSize; i++) {
            add(i, (T) newArray[i]);
        }
        return true;
    }

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

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    @Override
    public T get(int index) {
        return indexCheck(index) ? (T) data[index] : null;
    }

    public boolean indexCheck(int index) {
        return (index >= 0 && index <= size) ? true : false;
    }

    @Override
    public T set(int index, T element) {
        if (indexCheck(index)) {
            T previousValue = (T) data[index];
            data[index] = element;
            return previousValue;
        }
        return null;
    }

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

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

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

    public boolean trimToSize() {
        if(data.length > size) {
            Arrays.copyOf(data, size);
            return true;
        }
        return false;
    }
}
