import java.util.*;

public class MyArrayList<T extends Comparable<T>> implements List<T> {
    int size = 10;
    T[] myArray = (T[]) new Comparable[size];


    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return (size == 0); }

    @Override
    public boolean contains(Object o) { return indexOf(o) >= 0; }

    @Override
    public Iterator<T> iterator() { return new MyListIterator<>(this); }

    @Override
    public Object[] toArray() { return myArray; }

    @Override
    public <S> S[] toArray(S[] a) {
        ArrayList<T> copy = new ArrayList<>(this);
        return copy.toArray(a);
    }


    @Override
    public boolean add(T t) {
        for (int i = 0; i < myArray.length; i++) {
            if(myArray[i] == null && i == 0){
                myArray[i] = t;
                return true;
            } else if (myArray[i] == null) {
                myArray[i] = t;
                removeNull();
                return true;
            } else if (i + 1 == myArray.length) {
                size += (size/2);
                myArray = Arrays.copyOf(myArray , size);
                myArray[i+1] = t;
                removeNull();
                return true;
            }
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < myArray.length; i++ ) {
            if (o == myArray[i]){
                this.remove(i);
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        int equalCount = 0;
        for (T element : myArray) {
            for (Object o : c) {
                if (element == o) {
                    equalCount++;
                }
            }
        }
        return equalCount == c.toArray().length;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T element : c) this.add(element);
        return true;

    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        for (T element : c) this.add(index, element);
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        int sizeCheck = size;
        for (T t : myArray) {
            for (Object o : c) {
                if (t == o) {
                    this.remove(t);
                }
            }
        }
        return sizeCheck != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int sizeCheck = size;
        for (T t : myArray) {
            for (Object value : c) {
                if (t != value) {
                    remove(t);
                }
            }
        }
        return sizeCheck != size;
    }

    @Override
    public void clear() {
        size = 0;
        myArray = Arrays.copyOf(myArray , size);
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
        T tempData = myArray[index];
        T placeHolder;
        myArray[index] = element;

       for (int i = 0; i <= size; i++) {
           if (i > index && i < size - 1) {
               placeHolder = myArray[i];
               myArray[i] = tempData;
               tempData = placeHolder;
           } else if (i == size - 1) {
               placeHolder = myArray[i];
               myArray[i] = tempData;
               tempData = placeHolder;

               size += (size/2);
               myArray = Arrays.copyOf(myArray , size);
               myArray[i + 1] = tempData;
           }
       }
       removeNull();
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
        for (int i = 0; i < myArray.length; i++) {
            if ( o == myArray[i]) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = 0; i < myArray.length; i++) {
            if (o == myArray[i]){
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return new MyListIterator<>(this);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return new MyListIterator<>(this, index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        MyArrayList<T> tempArrayList = new MyArrayList<>();
        for (int i = fromIndex; i < toIndex; i++){
            tempArrayList.add(this.get(i));
        }
        return tempArrayList;
    }


    public void removeNull() {
        for (int i = 0; i < myArray.length; i++) {
            if (myArray[i] == null) {
                myArray = Arrays.copyOf(myArray,i);
                size = myArray.length;
                break;
            }
        }
    }
}//end class




class Main {
    public static void main(String[] args) {

        //adding 11 integers to MyArrayList and sorting them.
        //as well as iterating over them
        MyArrayList<Integer> myIntList = new MyArrayList<>();
        myIntList.add(2);
        myIntList.add(4);
        myIntList.add(5);
        myIntList.add(1);
        myIntList.add(6);
        myIntList.add(3);
        myIntList.add(9);
        myIntList.add(8);
        myIntList.add(10);
        myIntList.add(7);
        myIntList.add(11);
        Collections.sort(myIntList);
        System.out.println("\nList Below is sorted using Collections.Sort(myIntList) ensuring Collections.Sort works with MyArrayList and MyListIterator. As well as foreach loop works with MyArrayList<Integer> as well.");
        for (int i : myIntList) {
            System.out.println(i);
        }
        System.out.println("\nList below ensures foreach work with MyArrayList<String>. As well as showing off creating a list with default capacity will not output null.");

        MyArrayList<String> myStrList = new MyArrayList<>();
        myStrList.add("Cat");
        myStrList.add("Dog");
        myStrList.add("Squirrel");
        myStrList.add("Rabbit");
        myStrList.add("Frog");
        myStrList.add("Alligator");
        for(String str : myStrList) {
            System.out.println(str);
        }

    }
}