package java_puzzlers._91;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author xiebiao
 * @date 7/27/16
 */
public class Super implements Serializable {
    final Set<Super> set = new HashSet<>();

}
