import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyQueue<Item> {
    private Object[] queue = new Object[1];
    private int size = 0;
    private int start = 0;
    private int end = 0;

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    private void resize(int capacity) {
        Object[] temp = new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = queue[(start + i) % queue.length];
            queue = temp;
            start = 0;
            end = size;
        }
    }

    public void enqueue(Item item){
        if (size == queue.length){
            resize(queue.length*2);
        }
        queue[end++] = item;
        end %= queue.length;
        size++;
    }

    public Item peek(){
        if (isEmpty()){
            throw new NoSuchElementException("Queue is empty");
        }
        return (Item) queue[start];
    }

    public Item dequeue(){
        Item item = peek();
        size--;
        start++;
        start %= queue.length;
        if (size == queue.length/4 && size>0) resize(queue.length/2);
        return item;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(((Item) queue[(start + i) % queue.length]).toString());
            sb.append(", ");
        }
        return sb.toString();
    }
}
