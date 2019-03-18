import java.util.Comparator;
import java.util.Iterator;

public class MyArrayList<Item> implements Iterable{
    private Object[] list = new Object[1];
    private int size = 0;

    public Item get(int index){
        if (index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException();
        }
        return (Item) list[index];
    }

    public void set(Item item, int index){
        if (index >=0 && index < list.length) {
            list[index] = item;
        } else throw new IndexOutOfBoundsException();
    }

    public void add(Item item){
        if (size == list.length){
            resize(2*list.length);
        }
        list[size++] = item;
    }

    private void resize(int capaity){
        Object[] listok = new Object[capaity];
        System.arraycopy(list,0,listok,0,size);
        list = listok;
    }

    public int size(){
        return size;
    }

    public int indexOf(Item item) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) return i;
        }
        return -1;
    }

    public boolean contains(Item item){
        return indexOf(item) != -1;
    }

    public boolean remove(Item item){
        int i = indexOf(item);
        if (i == -1) return false;

        for (int j = i; j < size - 1; i++) {
            list[j] = list[j+1];
        }
        list[size-1] = null;
        size--;

        if (size < list.length/4 && size > 0){
            resize(list.length/2);
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < size; i++) {
            s.append(list[i]);
            s.append(", ");
        }
        return s.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new MyListIterator();
    }

    private class MyListIterator implements Iterator<Item> {
        int cursor = 0;
        @Override
        public boolean hasNext() {
            return cursor != size;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new IndexOutOfBoundsException();
            }
            Item item = (Item) list[cursor];
            cursor++;
            return item;
        }
    }

    private void exch(int i, int j) {
        Object temp = list[i];
        list[i] = list[j];
        list[j] = temp;
    }

    private boolean less(Item item1, Item item2, Comparator<Item> cmp) {
        return cmp.compare(item1, item2) < 0;
    }

    public void selectionSort(Comparator<Item> cmp) {
        for (int i = 0; i < size - 1; i++) {
            int min = i;
            for (int j = i + 1; j < size; j++) {
                if (less((Item) list[j], (Item) list[min], cmp)) {
                    min = j;
                }
            }
            exch(i, min);
        }
    }

    public void insertionSort(Comparator<Item> cmp) {
        for (int i = 0; i < size; i++) {
            for (int j = i; j > 0; j--) {
                if (less((Item) list[j], (Item) list[j - 1], cmp)) {
                    exch(j, j - 1);
                }
                else {
                    break;
                }
            }
        }
    }

    public boolean binarySearch(Item item, Comparator<Item> cmp) {
        int low = 0;
        int high = size - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2; //(low + high) / 2
            if (cmp.compare(item, (Item) list[mid]) < 0) {
                high = mid - 1;
            }
            if (cmp.compare(item, (Item) list[mid]) > 0) {
                low = mid + 1;
            }
            else { // cmp.compare == 0
                return true;
            }
        }
        return false;
    }
}
