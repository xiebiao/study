package nio;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

/**
 * http://www.importnew.com/2000.html
 *
 * @author xiebiao
 * @date 9/25/16
 */
public class FilerWatcher implements FileWatch {
    private String filePath;

    public FilerWatcher(String filePath) {
        this.filePath = filePath;
    }

    public static void main(String[] args) {

    }

    @Override
    public void watch(String filePath, Action action) {
        Path dir = Paths.get(filePath);
        try {
            WatchService watchService = dir.getFileSystem().newWatchService();
            dir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
            WatchKey key = watchService.take();
            while (true) {
                List<WatchEvent<?>> watchEvents = key.pollEvents();
                for (WatchEvent watchEvent : watchEvents) {
                    if (watchEvent.kind() == StandardWatchEventKinds.ENTRY_CREATE) {
                        //文件创建
                        action.nofity(watchEvent.kind());
                    } else if (watchEvent.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                        //文件修改
                        action.nofity(watchEvent.kind());
                    }
                }
                key.reset();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
        }
    }
}
