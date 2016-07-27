package java_puzzlers;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author xiebiao
 * @date 7/26/16
 */
public class _95 {
    public static void main(String[] args) {
        Integer[] array = {9, 3, 1, 4, 1, 5, 9};
        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                //不要这样写代码
                return i1 < i2 ? -1 : (i2 > i1 ? 1 : 0);
            }
        });
        System.out.println(Arrays.toString(array));
    }
}
