package model;
public class Node<E>{
    Node<E> left;
    Node<E> right;
    E value;

    public Node(E value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}