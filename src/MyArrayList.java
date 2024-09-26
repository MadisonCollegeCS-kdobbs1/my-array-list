import javax.swing.plaf.ComponentInputMapUIResource;
import java.util.*;

public class MyArrayList<T extends Comparable<T>> implements java.util.List<T> {
    int size = 10;
    T[] myArray = (T[]) new Comparable[size];

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() { return (size == 0); }

    @Override
    public boolean contains(Object o) { return indexOf(o) >= 0; }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator<>(this);
    }

    @Override
    public Object[] toArray() {
        return myArray;
    }

    @Override
    public <S> S[] toArray(S[] a) {
        ArrayList<T> copy = new ArrayList<>(this);
        return copy.toArray(a);
    }


    @Override
    public boolean add(T t) {
        add(size, t);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) { return myArray[index]; }

    @Override
    public T set(int index, T element) {
        T temp = myArray[index];
        myArray[index] = element;
        return temp;
    }

    @Override
    public void add(int index, T element) {
        myArray[index] = element;
        size += (int) ((double) size/2);
    }

    @Override
    public T remove(int index) {
        T tempObj = myArray[index];
        myArray[index] = null;

        for (int i = 0; i < size; i++){
            if (i >= index) {
                this.set(i, this.get(i + 1));
            }
        }
        return tempObj;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new MyListIterator<>(this);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return List.of();
    }
}//end class
