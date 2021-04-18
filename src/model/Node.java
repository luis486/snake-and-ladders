package model;

public class Node {

    private Node top;
    private Node bottom;
    private Node left;
    private Node right;
    
    private int row;
    private int col;

    public int id;

    public Node(int r, int c) {
        this.row = r;
        this.col = c;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public Node getTop() {
        return this.top;
    }

    public void setTop(Node top) {
        this.top = top;
    }

    public Node getBottom() {
        return this.bottom;
    }

    public void setBottom(Node bottom) {
        this.bottom = bottom;
    }

    public Node getLeft() {
        return this.left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return this.right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public String toString(){
        return "[("+row+","+col+")]";
    }
  
}
