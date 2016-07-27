package java_puzzlers;

/**
 * @author xiebiao
 * @date 7/26/16
 */
public class _3 {
    //---------- wrong
    //int与int相乘存储在int中，但是这里发生里溢出
    static final long MICROS_PRE_DAY = 24 * 60 * 60 * 1000 * 1000;//
    static final long MILLIS_PRE_DAY = 24 * 60 * 60 * 1000;
    //----------- right
    static final long MICROS_PRE_DAY_FIXED = 24L * 60 * 60 * 1000 * 1000;
    static final long MILLIS_PRE_DAY_FIXED = 24L * 60 * 60 * 1000;

    public static void main(String[] args) {
        System.out.println(MICROS_PRE_DAY / MILLIS_PRE_DAY);
        System.out.println(MICROS_PRE_DAY_FIXED / MILLIS_PRE_DAY_FIXED);
    }
}
