import java.util.Random;

public class Test5 {
    public static void main(String[] args) {
        MyTreeMap<Integer, Character> tree;
        Random random = new Random();
        int count = 0;
        int j = 0;
        for (int i = 0; i < 2000000;i++){
            tree = new MyTreeMap<>();
            while (tree.height() < 7){
                j = random.nextInt(201) - 100;
                tree.put(j, 'a');
            }
            tree.remove(j);
            if (tree.isBalanced()) count++;
        }
        System.out.println(count);
    }
}
