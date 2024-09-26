import java.util.ListIterator;

public class MyListIterator<T extends Comparable<T>> implements ListIterator<T> {
    T setPointer;
    int cursor = 0;
    MyArrayList<T> list;

    public MyListIterator(MyArrayList<T> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return cursor != list.size;
    }

    @Override
    public T next() {
        cursor++;
        setPointer = list.get(cursor - 1);
        return list.get(cursor - 1);
    }

    @Override
    public boolean hasPrevious() {
        return cursor != 0;
    }

    @Override
    public T previous() {
        cursor--;
        setPointer = list.get(cursor + 1);
        return list.get(cursor + 1);
    }

    @Override
    public int nextIndex() {
        return cursor + 1;
    }

    @Override
    public int previousIndex() {
        return cursor - 1;
    }

    @Override
    public void remove() {}

    @Override
    public void set(T t) {
        list.set(list.lastIndexOf(setPointer), t);
    }

    @Override
    public void add(T t) {}
}
