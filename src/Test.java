import java.util.Comparator;
import java.util.Random;

public class Test {
    static int expCount = 0;
    public static void main(String[] args) {
        System.out.println("# Вставка Выбор");
        experiment(50000, 100000); //1
        experiment(50000, 100000); //2
        experiment(50000, 1000); //3
        experiment(50000, 1000); //4
        experiment(50000, 10000000); //5
        experiment(50000, 10000000); //6
        experiment(100000, 10000000); //7
        experiment(100000, 10000000); //8

    }

    private static void experiment(int number, int max){
        MyArrayList<Integer> list1 = new MyArrayList<>();
        MyArrayList<Integer> list2 = new MyArrayList<>();

        Random random = new Random();
        long insertSortTime;
        long selectSortTime;

        randomList(random, list1, list2, number, max);

        selectSortTime = System.currentTimeMillis();
        list1.selectionSort(new IntCmp());
        selectSortTime = System.currentTimeMillis() - selectSortTime;

        insertSortTime = System.currentTimeMillis();
        list2.insertionSort(new IntCmp());
        insertSortTime = System.currentTimeMillis() - insertSortTime;

        System.out.println(++expCount + " " + insertSortTime + " " + selectSortTime);
    }

    private static void randomList(Random random, MyArrayList<Integer> list1, MyArrayList<Integer> list2,int number, int max){
        int t;
        for (int i = 0; i < number; i++) {
            t = random.nextInt(max+1);
            list1.add(t);
            list2.add(t);
        }
    }

    public static class IntCmp implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }
}
