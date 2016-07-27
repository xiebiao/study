package java_puzzlers;

import java.util.Arrays;
import java.util.Random;

/**
 * @author xiebiao
 * @date 7/26/16
 */
public class _94 {
    private static Random rnd = new Random();

    /**
     * 均等地打乱输入的数组元素顺序
     */
    public static void shuffle(Object[] a) {
        for (int i = 0; i < a.length; i++) {
          //  swap(a, i, rnd.nextInt(a.length));//排列并不均匀
            swap(a, i, i + rnd.nextInt(a.length - i));
        }
    }

    private static void swap(Object[] a, int i, int j) {
        Object tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        Object[] array = {1, 2, 3, 4, 5};
        _94.shuffle(array);
        System.out.println(Arrays.toString(array));
    }
}
