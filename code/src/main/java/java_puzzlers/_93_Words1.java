package java_puzzlers;

/**
 * @author xiebiao
 * @date 7/27/16
 */
public class _93_Words1 {
    public static final String FIRST = "the";
    //null并不会当作常量,所以当SECOND被重新赋值，再编译的时候，_93会重新编译
    public static final String SECOND = null;
    // public static final String SECOND = "DDDD";
    public static final String THIRD = "set";
}
