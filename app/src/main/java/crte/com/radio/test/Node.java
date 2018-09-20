package crte.com.radio.test;

public class Node<T> {
    public T e;
    public Node<T> next;

    public Node(T e) {
        this.e = e;
        this.next = null;
    }
}
