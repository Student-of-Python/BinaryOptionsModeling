package model;

/**
 * BackwardInductionTree class represents the value of the option in different points of its life.
 * It will discount from expiration to present day to determine present day value
*/

public class BackwardInductionTree {
    BinaryTree tree;

    boolean call_option = true; 
    double risk_free_rate;
    double strike_price;

    double probability; // neutral risk rate of probability
    double delta_t; //


    /**
     * 
     * @param tree: A binary tree with stock price movement 
     * @param risk_free_rate: Risk free rate, or the garenteed rate of return with no risk
     * @param call_option: True is it is a call option, else it would be marked as put option
     * @param strike_price: Strike price of the option
     * 
     */

    public BackwardInductionTree(BinaryTree tree, double risk_free_rate, boolean call_option, double strike_price){
        this.tree = tree;
        this.risk_free_rate = risk_free_rate;
        this.call_option = call_option;
        this.strike_price = strike_price;

        this.delta_t = 1.0 / (this.tree.getHeight(this.tree.root));
        this.probability = (Math.exp(this.risk_free_rate * this.delta_t) - this.tree.D) / (this.tree.U - this.tree.D);
    }
    
    /** compute_payoffs
     *  At terminal nodes, determine the value of the option
     *  that is, whether it is out of the money or in the money.
     */

    public void compute_payoffs(){
        //Computes the payoff for each terminal node
        for (Node leaf: this.tree.getLeafs()){
            if (this.call_option) leaf.optionValue = Math.max(leaf.stockValue - this.strike_price, 0); //Call option

            else leaf.optionValue = Math.max(this.strike_price - leaf.stockValue, 0); //Put option
        }
    }

    /**backwardInduction
     * 
     * @param node: Current node
     * @return The current, present day value of the option 
     */
    
    public double backwardInduction(Node node){
        //Base Case 1: Node does not exist; no payoff
        if (node == null) return 0;

        //Base Case 2; Terminal node (leaf node) found; return its payoff
        if (node.left == null && node.right == null) return node.optionValue; 

        
        // Gets the payoff, or value of both the right and left child
        //Payoff when leaf node
        //Value when non leaf nodes
        double value_up = backwardInduction(node.right);
        double value_down = backwardInduction(node.left); 
        //Discounts the price with each step
        node.optionValue = Math.exp(-this.risk_free_rate * this.delta_t) * (this.probability*value_up + (1-this.probability)*value_down);
        return node.optionValue;
    }


    //Call backwardInduction in one 
    public double backwardInduction(){
        compute_payoffs();
        return backwardInduction(this.tree.root);
    }
    
    public void printTree(){
        printTree(this.tree.root, "", true);
    }


    public void printTree(Node node, String prefix, boolean isLeft){
        if (node == null) return;

        // Print the right subtree first (so it appears on top visually)
        printTree(node.right, prefix + (isLeft ? "│   " : "    "), false);

        // Print current node
        String formattedValue = String.format("%.2f", node.optionValue);
        System.out.println(prefix + (isLeft ? "└── " : "┌── ") + formattedValue);

        // Print the left subtree
        printTree(node.left, prefix + (isLeft ? "    " : "│   "), true);
    }

}

