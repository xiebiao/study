package java_puzzlers;

import java.math.BigDecimal;

/**
 * 不要使用double类型的
 * @author xiebiao
 * @date 7/17/16
 */
public class _2 {
    public static void main(String[] args) {
        System.out.println(2.00 - 1.10);
        System.out.println(new BigDecimal("2.00").subtract(new BigDecimal("1.10")));
    }
}
