package nio;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 * http://www.importnew.com/2000.html
 * @author xiebiao
 * @date 9/25/16
 */
public class FilerWatcher {
    public static void main(String[] args) {
        Path dir = Paths.get(".");
        try {
            WatchService watchService = dir.getFileSystem().newWatchService();
            dir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            WatchKey key = watchService.take();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
        }
    }
}
