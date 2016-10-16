package nio;

import java.nio.file.WatchEvent;

/**
 * @author xiebiao
 * @date 10/16/16
 */
public interface Action {
    void nofity(WatchEvent.Kind kind);
}
