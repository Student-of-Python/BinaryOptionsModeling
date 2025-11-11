package model;
public class Node{
    Node left; 
    Node right;
    double optionValue; // Whether any money is won
    double stockValue; //Stock price given U, D
    public Node(double stockValue) {
        this.stockValue = stockValue;
        this.left = null;
        this.right = null;
    }
}