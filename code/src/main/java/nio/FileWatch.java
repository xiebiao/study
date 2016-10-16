package nio;

/**
 * @author xiebiao
 * @date 10/16/16
 */
public interface FileWatch {
    void watch(String filePath, Action action);
}
