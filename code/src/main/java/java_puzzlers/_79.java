package java_puzzlers;

/**
 * @author xiebiao
 * @date 7/28/16
 */
public class _79 {
    public void eat() {
        System.out.println("Eat");
    }

    //bad name
    public void sleep() {
        System.out.println("Sleep");
    }

    public void live() {
        new Thread() {
            public void run() {
                while (true) {
                    eat();
                    //
                    //  sleep();
                }
            }
        }.start();
    }
}
