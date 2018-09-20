package crte.com.radio.test;

public class Tree<T> {
    public T e;
    public Tree<T> left;
    public Tree<T> right;

    public Tree(T e) {
        this.e = e;
        this.left = null;
        this.right = null;
    }
}
