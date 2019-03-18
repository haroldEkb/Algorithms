import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<Item> implements Iterable<Item>{
    private Node first = null;
    private Node last = null;
    private int size;

    private class Node{
        private Node next;
        private Node previous;
        private Item item;

        private Node(Node previous, Item item, Node next) {
            this.item = item;
            this.next = next;
            this.previous = previous;
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public Item getFirst(){
        if (isEmpty()) throw new NoSuchElementException("List is Empty");
        return first.item;
    }

    public void addFirst(Item item){
        Node oldFirst = first;
        first = new Node(null, item, oldFirst);
        if (isEmpty()) last = first;
        else {
            oldFirst.previous = first;
        }
        size++;
    }

    public Item removeFirst(){
        if (isEmpty()) throw new NoSuchElementException("List is Empty");
        Node oldFirst = first;
        Item item = oldFirst.item;
        first = oldFirst.next;
        oldFirst.next = null;
        oldFirst.previous = null;
        oldFirst.item =null;
        size--;
        if (isEmpty()) last = null;
        else {
            first.previous = null;
        }
        return item;
    }

    public Item getLast(){
        if (isEmpty()) throw new NoSuchElementException("List is Empty");
        return last.item;
    }

    public void addLast(Item item){
        Node oldLast = last;
        last = new Node(oldLast, item, null);
        if (isEmpty()) first = last;
        else {
            oldLast.next = last;
        }
        size++;
    }

    public Item removeLast(){
        if (isEmpty()) throw new NoSuchElementException("List is Empty");
        Node oldLast = last;
        Item item = oldLast.item;
        last = oldLast.previous;
        oldLast.next = null;
        oldLast.previous = null;
        oldLast.item =null;
        size--;
        if (isEmpty()) first = null;
        else {
            last.next = null;
        }
        return item;
    }

    public Item get(int index){
        if (index > size - 1 || index < 0) throw new IndexOutOfBoundsException();
        Item item;
        Node node;
        if (index < size/2) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size; i > index; i--) {
                node = node.previous;
            }
        }
        return node.item;
    }

    public void set(int index, Item item){
        if (index > size - 1 || index < 0) throw new IndexOutOfBoundsException();
        Node node;
        if (index < size/2) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size; i > index; i--) {
                node = node.previous;
            }
        }
        node.item = item;
    }

    public int indexOf(Item item){
        int current = 0;
        Node node = first;
        while (node != null && !node.item.equals(item)){
            current++;
            node = node.next;
        }
        return -1;
    }

    public boolean contains(Item item){
        return indexOf(item) != -1;
    }

    public Item remove(Item item){
        Node current = first;
        if (item == null){
            while (current != null && current.item != null){
                current = current.next;
            }
        } else while (current != null && !current.item.equals(item)){
            current = current.next;
        }

        if (current == null) return null;
        if (current == first) return removeFirst();
        if (current == last) return removeLast();

        Node next = current.next;
        Node previous = current.previous;

        previous.next = next;
        current.previous = null;

        next.previous = previous;
        current.next = null;

        size--;
        return current.item;
    }

    public void add(int index, Item item){
        if (index > size || index < 0) throw new IndexOutOfBoundsException();
        if (index == 0) {
            addFirst(item);
            return;
        }
        if (index == size){
            addLast(item);
            return;
        }
        Node node;
        if (index < size/2) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size; i > index; i--) {
                node = node.previous;
            }
        }
        Node newNode = new Node(node.previous, item, node);
        node.previous.next = newNode;
        node.previous = newNode;
        size++;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node node = first;
        for (int i = 0; i < size; i++) {
            sb.append(node.item);
            sb.append(", ");
            node = node.next;
        }
        return sb.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new MyLinkedListIterator();
    }

    private class MyLinkedListIterator implements Iterator<Item>{

        Node node = first;

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = node.item;
            node = node.next;
            return item;
        }
    }
}
