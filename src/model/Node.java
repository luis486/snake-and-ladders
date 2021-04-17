package model;

public class Node {

    public Node top;
    public Node bottom;
    public Node left;
    public Node right;
    

    public int x;
    public int y;

    public String data;

    public Node(int x, int y, String data) {
        this.x = x;
        this.y = y;
        this.data = data;
    }

    public Node getTop() {
        return this.top;
    }

    public Node getBottom() {
        return this.bottom;
    }

    public Node getLeft() {
        return this.left;
    }
    public Node getRight() {
        return this.right;
    }

    public boolean equals(Node obj) {
        if (obj == null ) {
            return true;
        }
        if (this.x == obj.x && this.y == obj.y) {
            return true;
        }
        return false;
    }
    
}
