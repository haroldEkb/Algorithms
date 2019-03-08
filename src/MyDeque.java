import java.util.NoSuchElementException;

public class MyDeque<Item> {
    private Object[] deque = new Object[1];
    private int size = 0;
    private int start = 0;
    private int end = 0;

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private void resize(int capacity) {
        Object[] temp = new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = deque[(start + i) % deque.length];
        }
        deque = temp;
        start = 0;
        end = size;
    }

    public void insertLeft(Item item){
        if (size == deque.length){
            resize(deque.length*2);
        }
        deque[start--] = item;
        if (start<0) start = deque.length - 1;
        size++;
    }

    public void insertRight(Item item){
        if (size == deque.length){
            resize(deque.length*2);
        }
        deque[end] = item;
        end++;
        size++;
    }

    public Item peekFront(){
        if (isEmpty()){
            throw new NoSuchElementException("Deque is empty");
        }
        return (Item) deque[start + 1 == deque.length ? 0: start + 1];
    }

    public Item peekBack(){
        if (isEmpty()){
            throw new NoSuchElementException("Deque is empty");
        }
        return (Item) deque[end - 1 < 0 ? deque.length - 1: end - 1];
    }

    public Item removeLeft(){
        Item item = peekFront();
        size--;
        start++;
        start %= deque.length;
        if (size == deque.length/4 && size>0) resize(deque.length/2);
        return item;
    }

    public Item removeRight(){
        Item item = peekBack();
        size--;
        end--;
        if (end == -1) end = deque.length - 1;
        if (size == deque.length/4 && size>0) resize(deque.length/2);
        return item;
    }
}
