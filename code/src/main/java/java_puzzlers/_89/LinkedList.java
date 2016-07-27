package java_puzzlers._89;

/**
 * @author xiebiao
 * @date 7/28/16
 */
public class LinkedList<E> {
    private Node head = null;

    private class Node {
        E value;
        Node next;

        Node(E value) {
            this.value = value;
            this.next = head;
            head = this;
        }
    }
}
