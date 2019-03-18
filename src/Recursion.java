public class Recursion {
    public static void main(String[] args) {
        long t = System.currentTimeMillis();
        long ph1 = phiboRecursion(50);
        System.out.println(System.currentTimeMillis()-t);
        t = System.currentTimeMillis();
        long ph2 = phiboCicle(50);
        System.out.println(System.currentTimeMillis()-t);
    }

    public static long phiboCicle(int n){
        if (n < 1) throw new ArithmeticException("Неправильный порядковый номер");
        long a = 0;
        long b = 1;
        long result = 1;
        for (int i = 1; i < n; i++) {
            result = a + b ;
            a = b;
            b = result;
        }
        return result;
    }

    public static int phiboRecursion(int n){
        if (n < 3) return 1;
        return phiboRecursion(n-1) + phiboRecursion(n-2);
    }
}
