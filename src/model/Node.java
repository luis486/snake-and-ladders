package model;

public class Node {

    private Node top;
    private Node bottom;
    private Node left;
    private Node right;
    private Snakes snake;
    private Ladders ladders;
    private int row;
    private int col;
    public int id;

    public Node(int r, int c, int id) {
        this.row = r;
        this.col = c;
        this.id = id;
    }

    public Snakes getSnake() {
        return this.snake;
    }

    public void setSnake(Snakes snake) {
        this.snake = snake;
    }

    public Ladders getLadders() {
        return this.ladders;
    }

    public void setLadders(Ladders ladders) {
        this.ladders = ladders;
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

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return "[(" + id + ")]";
    }

}
