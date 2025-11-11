package model;

public class Main {
    public static void main(String[] args) {
        Node root = new Node(100.0);
        BinaryTree tree = new BinaryTree(root, 0.1, 3);

        tree.add_layers();
        
        BackwardInductionTree back_tree = new BackwardInductionTree(tree, 0.05, true, 110);


        tree.printTree();

        back_tree.compute_payoffs();
        System.out.println("Payout: " + back_tree.backwardInduction());

        tree.printTree();
    }
}