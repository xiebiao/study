package java_puzzlers;

/**
 * @author xiebiao
 * @date 7/26/16
 */
public class _1 {

    public static boolean isOddRight(int i) {
        return i % 2 != 0;
    }

    public static boolean isOddWrong(int i) {
        //除以2的余数等于1
        return i % 2 == 1;
    }

    public static void main(String[] args) {
        System.out.println(_1.isOddWrong(-3));
    }
}
