package java_puzzlers._88;

import java.util.Arrays;
import java.util.List;

/**
 * @author xiebiao
 * @date 7/28/16
 */
public class Pair<T> {
    private final T first;
    private final T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T first() {
        return first;
    }

    public T second() {
        return second;
    }

    public List<String> stringList() {
        return Arrays.asList(String.valueOf(first), String.valueOf(second));
    }

    public static void main(String[] args) {
        //由于Pair在定义的时候用的泛型，但是这里在实例化Pair的时候，没有指定Pair的类型
        //则Pair为原始类型
        //Pair  p = new Pair<Object>(23, "skudoo");
        //fixed：指定类型
        Pair<Object> p = new Pair(23, "skudoo");
        System.out.println(p.first() + " " + p.second());
        for (String s : p.stringList()) {
            System.out.println(s);

        }
    }
}
