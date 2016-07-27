package java_puzzlers;

/**
 * @author xiebiao
 * @date 7/27/16
 */
public class _92_Twisted {
    private final String name;

    _92_Twisted(String name) {
        System.out.println("init:" + name);
        this.name = name;
    }

    private String name() {
        return name;
    }

    private void reproduce() {
        new _92_Twisted("reproduce") {
            void printName() {
                System.out.println(name);
            }
        }.printName();
    }

    public static void main(String[] args) {
        new _92_Twisted("main").reproduce();
    }
}
