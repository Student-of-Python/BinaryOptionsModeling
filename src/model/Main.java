package model;

public class Main {
    public static void main(String[] args) {
        Node<Double> root = new Node<>(100.0);
        BinaryTree tree = new BinaryTree(root, 1.1, 0.9);

        // Add two levels
        tree.add();
        tree.add();
        tree.add();

        tree.printTree();
    }
}