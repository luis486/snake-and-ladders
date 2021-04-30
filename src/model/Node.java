package model;

public class Node {

    private Node top;
    private Node bottom;
    private Node left;
    private Node right;
    private Snakes snake;
    private Ladders ladders;
    private Player players;
    private boolean visible;
    private int row;
    private int col;
    public int id;
    private boolean statusNode;

    public Node(int r, int c, int id) {
        this.row = r;
        this.col = c;
        this.id = id;
        visible = true;
        statusNode = false;
    }

    public Snakes getSnake() {
        return this.snake;
    }

    public void setSnake(Snakes snake) {
        this.statusNode = true;
        this.snake = snake;
    }

    public Ladders getLadders() {
        return this.ladders;
    }

    public void setLadders(Ladders ladders) {
        this.statusNode = true;
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

    public boolean isStatusNode() {
        return this.statusNode;
    }

    public boolean getStatusNode() {
        return this.statusNode;
    }

    public void setStatusNode(boolean statusNode) {
        this.statusNode = statusNode;
    }

    public Player getPlayers() {
        return this.players;
    }

    public Boolean isVisible() {
        return this.visible;
    }

    public Boolean getVisible() {
        return this.visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public void setPlayers(Player p) {
        this.players = p;
    }

    @Override
    public String toString() {
        String render = " ";
        if (visible == true) {
            render = String.valueOf(id);
        }
        String msg = "";
        if (snake != null) {

            msg += " " + "\033[0;31m" + snake + "\033[0m";
        } else if (ladders != null) {

            msg += " " + "\033[0;35m" + ladders + "\033[0m";
        }
        if (this.players != null) {
            msg += " " + "\033[0;32m" + players.getPartner(players, String.valueOf(players.getSymbol())) + "\033[0m";
        }
        return "[" + " " + render + " " + msg + " " + "]";

    }

}
