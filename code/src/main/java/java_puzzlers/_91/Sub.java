package java_puzzlers._91;

/**
 * @author xiebiao
 * @date 7/27/16
 */
public class Sub extends Super {
    private int id;

    public Sub(int id) {
        this.id = id;
        set.add(this);
    }

    public void setId(int id) {
        this.id = id;
        set.add(this);
    }

    public void checkInvariant() {
        if (!set.contains(this)) {
            throw new AssertionError();
        }
    }

    public int hashCode() {
        return this.id;
    }

    public boolean equals(Object o) {
        return (o instanceof Sub) && (id == ((Sub) o).id);
    }
}
