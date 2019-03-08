import java.util.NoSuchElementException;

public class MyStack<Item> {
    private Object[] stack = new Object[1];
    private int size;

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    private void resize(int capacity){
        Object[] temp = new Object[capacity];
        for (int i = 0; i < size; i++) {
            temp[i] = stack[i];
        }
        stack = temp;
    }

    public void push(Item item){
        if (size == stack.length){
            resize(stack.length*2);
        }
        stack[size++] = item;
    }

    public Item peek(){
        if (isEmpty()) throw new NoSuchElementException("Stack is empty");

        return (Item) stack[size-1];
    }

    public Item pop(){
        Item item = peek();
        size--;
        if (size == stack.length/4 && size>0){
            resize(stack.length/2);
        }
        return item;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(((Item) stack[i]).toString());
            sb.append(", ");
        }
        return sb.toString();
    }


}
