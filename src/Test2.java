public class Test2 {
    public static void main(String[] args) {
        String s1 = "123456789qwertyu";
    }

    private static String reverse(String str){
        MyStack<Character> stack = new MyStack<>();

        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append(stack.pop());
        }
        System.out.println(sb);
        return sb.toString();
    }
}
