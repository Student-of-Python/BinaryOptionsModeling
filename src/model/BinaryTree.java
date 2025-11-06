package model;
public class BinaryTree{
    Node<Double> root;
    final Double U; 
    final Double D;
    public BinaryTree(Node<Double> root, Double U, Double D) {
        this.root = root;
        this.U = U;
        this.D = D; }

    
    public void add(){
        add(this.root);
    }

    public void add(Node<Double> current){
        //Recursively add nodes to the tree
        //Base base --> leaf has no children, add children
        //Case 2: Node has children, recurse down the tree until hit leaf, then add children
        //Since no node has exactly 1 child, this simplifies the logic
        

        //Adds two children for each leave
        if (current == null) {
            return;}

        if (current.left == null && current.right == null) {
         //Node has no children, add two children
            current.left = new Node<>(current.value * this.U);
            current.right = new Node<>(current.value * this.D);
            return;
        }
        
        if ((current.left == null) != (current.right == null)) {
             throw new IllegalStateException("Invalid tree state: node has exactly one child");
            }
    
        // Recursive case - both children exist
        add(current.left);
        add(current.right);
    }



    public void printTree(){
        printTree(this.root, "", true);
    }



    public void printTree(Node<Double> node, String prefix, boolean isLeft){
        if (node == null) return;

        // Print the right subtree first (so it appears on top visually)
        printTree(node.right, prefix + (isLeft ? "│   " : "    "), false);

        // Print current node
        String formattedValue = String.format("%.2f", node.value);
        System.out.println(prefix + (isLeft ? "└── " : "┌── ") + formattedValue);

        // Print the left subtree
        printTree(node.left, prefix + (isLeft ? "    " : "│   "), true);
    }
}
