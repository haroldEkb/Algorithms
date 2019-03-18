import java.util.NoSuchElementException;

public class MyTreeMap<Key extends Comparable<Key>, Value> {
    private class Node{
        Key key;
        Value value;
        Node left;
        Node right;
        int size;
        int height;

        public Node(Key key, Value value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;
            this.height = 0;
        }
    }

    private Node root = null;

    public boolean isEmpty(){
        return root == null;
    }

    public int size(){
        return size(root);
    }

    private int size(Node node){
        if (node == null) return 0;
        return node.size;
    }

    public int height(){
        return height(root);
    }

    private int height(Node node){
        if (node == null) return 0;
        return node.height;
    }

    public Value get(Key key){
        return get(root, key);
    }

    private Value get(Node node, Key key){
        if (key == null) throw new IllegalArgumentException("Такого не может быть!");
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node.value;
        if (cmp < 0) return get(node.left, key);
        else return get(node.right, key);
    }

    public void put(Key key, Value value){
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value){
        if (key == null) throw new IllegalArgumentException("Такого не может быть!");
        if (node == null) {
            return new Node(key, value,1);
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            node.value = value;
        }
        else if (cmp < 0) {
            node.left = put(node.left, key, value);
        }
        else {
            node.right = put(node.right, key, value);
        }

        int tmpHL = height(node.left);
        int tmpHR = height(node.right);
        node.height = tmpHL > tmpHR ? tmpHL + 1: tmpHR + 1;

        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public boolean contains(Key key){
        return get(key) != null;
    }

    private Node min(Node node){
        if (node.left == null) return node;
        return min(node.left);
    }

    public Value min(){
        return min(root).value;
    }

    private Node max(Node node){
        if (node.right == null) return node;
        return min(node.right);
    }

    public Value max(){
        return max(root).value;
    }

    private Node removeMin(Node node){
        if (node.left == null) return node.right;
        node.left = removeMin(node.left);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    private Node removeMax(Node node){
        if (node.right == null) return node.left;
        node.right = removeMax(node.right);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void removeMin(){
        if (isEmpty()) throw new NoSuchElementException("Дерево пустое");
        removeMin(root);
    }

    public void removeMax(){
        if (isEmpty()) throw new NoSuchElementException("Дерево пустое");
        removeMax(root);
    }

    public void remove(Key key){
        remove(root, key);
    }

    private Node remove(Node node, Key key){
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp == 0){
            if (node.left == null){
                return node.right;
            }
            if (node.right == null){
                return node.left;
            }

            Node tmp = node;
            node = max(node.left);
            node.left = removeMax(tmp.left);
            node.right = tmp.right;
            tmp = null;
        }
        else if (cmp < 0) node.left = remove(node.left, key);
        else node.right = remove(node.right, key);

        int tmpHL = height(node.left);
        int tmpHR = height(node.right);
        node.height = tmpHL > tmpHR ? tmpHL + 1: tmpHR + 1;

        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(Node node){
        if (node == null) return true;
        if (Math.abs(height(node.left) - height(node.right)) > 1) return false;
        return isBalanced(node.left) && isBalanced(node.right);
    }

}
