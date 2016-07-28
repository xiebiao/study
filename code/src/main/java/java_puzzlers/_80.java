package java_puzzlers;

/**
 * @author xiebiao
 * @date 7/28/16
 */
public class _80 {
    private void greetWorld() throws Exception {
        System.out.println(Inner.class.newInstance());
    }

    //非静态内部类
    public class Inner {

        public String toString() {
            return "Hello world";
        }
    }

    public static void main(String[] args) throws Exception {
        new _80().greetWorld();
    }
}
