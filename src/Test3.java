public class Test3 {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        list.addFirst(null);
        System.out.println(list.size());
        list.remove(null);
        System.out.println(list.size());

        list.addLast(1);
        list.addFirst(2);
        list.addFirst(5);
        list.remove(2);
        System.out.println(list);

        for (int i = 0; i < 10; i++) {
            list.addLast(i);
        }
        System.out.println(list);

        list.set(4,6);
        list.add(6, 10);
        list.remove(8);

        System.out.println(list);

        for (Integer i:list) {
            list.remove(i);
        }

        System.out.println();
    }
}
