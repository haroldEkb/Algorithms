public class Test4 {
    public static void main(String[] args) {
        System.out.println(powCyc(2,30));
        System.out.println(powRec(2,30));
        System.out.println(powFastRec(2,21));
        System.out.println(powFastCyc(2,21));

        int n = 10;
        MyStack<Integer> base = new MyStack<>();
        for (int i = n; i >= 1; i--) {
            base.push(i);
        }
        System.out.println(base);
        MyStack<Integer> destination = new MyStack<>();
        MyStack<Integer> support = new MyStack<>();
        hanoiRec(n, base, destination, support);
        System.out.println(base);
        System.out.println(destination);
    }

    static void hanoiRec(int q, MyStack<Integer> base, MyStack<Integer> destination, MyStack<Integer> support){
        if (q == 0) return;
        hanoiRec(q-1, base, support, destination);
        destination.push(base.pop());
        hanoiRec(q-1,support,destination,base);
    }

    static int powCyc(int number, int power){
        int result = 1;
        for (int i = 0; i < power; i++) {
            result *= number;
        }
        return result;
    }

    static int powRec(int number, int power){
        if (power == 0) return 1;
        else return powRec(number,power-1) * number;
    }

    static int powFastRec(int number, int power){
        if (power == 0) return 1;
        if (power % 2 == 0 ) {
            int t = powFastRec(number, power/2);
            return t*t;
        }
        else return powFastRec(number, power-1) * number;
    }

    static int powFastCyc(int number, int power){
        int result = 1;
        while (0 < power){
            if (power%2 == 0) {
                result = result * result;
                power /= 2;
            }
            else {
                result = result * number;
                power--;
            }
        }
        return result;
    }


}
