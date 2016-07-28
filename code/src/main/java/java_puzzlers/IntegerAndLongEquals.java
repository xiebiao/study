package java_puzzlers;

/**
 * <code>Integer</code>解包时处理int的范围是-128,127之间
 *
 * @author xiebiao
 * @date 7/28/16
 */
public class IntegerAndLongEquals {
    public static void main(String[] args) {
        Integer a = 129;
        Integer b = 129;
        System.out.println(a == b);//false
        a = 127;
        b = 127;
        System.out.println(a == b);//true
        a = -127;
        b = -127;
        System.out.println(a == b);//true
        a = -129;
        b = -129;
        System.out.println(a == b);//false
        Long c = 129L;
        Long d = 129L;
        System.out.println(c == d);//false
    }
}
