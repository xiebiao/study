package java_puzzlers._91;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author xiebiao
 * @date 7/27/16
 */
public class SeriralKiller {
    public static void main(String[] args) {
        Sub sub = new Sub(666);
        sub.checkInvariant();
        Sub copy = (Sub) deepCopy(sub);
        copy.checkInvariant();
    }

    static public Object deepCopy(Object obj) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(outputStream).writeObject(obj);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            return new ObjectInputStream(inputStream).readObject();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
