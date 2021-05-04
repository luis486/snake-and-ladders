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

    /**
     * Method name: Node.
     * Pre: The r int variable is the amount of the rows that the current game board will have; r must be different from null or empty
     * Pre: The c int variable is the amount of the columns that the current game board will have; c must be different from null or empty
     * Pre: The id int variable is the identification that the node will have in the current game board; id must be different from null or empty
     * Pos: The node is created correctly
     * @param r
     * @param c
     * @param id
     */
    public Node(int r, int c, int id) {
        this.row = r;
        this.col = c;
        this.id = id;
        visible = true;
        statusNode = false;
    }

    /**
     * Method name: getSnake.
     * Pos: Returns the snake that is in the node
     * @return snake
     */
    public Snakes getSnake() {
        return this.snake;
    }

    /**
     * Method name: setSnake.
     * Pre: The snake Snake variable is the snake that will be in the node; snake must be different from null or empty
     * Pos: The previous snake of the node is changed to the new one
     * @param snake
     */
    public void setSnake(Snakes snake) {
        this.statusNode = true;
        this.snake = snake;
    }

    /**
     * Method name: getLadders.
     * Pos: Returns the ladder that is in the node
     * @return ladders
     */
    public Ladders getLadders() {
        return this.ladders;
    }

    /**
     * Method name: setLadders.
     * Pre: The ladders Ladders variable is the ladder that will be in the node; ladders must be different from null or empty
     * Pos: The previous ladder of the node is changed to the new one
     * @param ladders
     */
    public void setLadders(Ladders ladders) {
        this.statusNode = true;
        this.ladders = ladders;
    }

    /**
     * Method name: getRow.
     * Pos: Returns the row that is in the game board
     * @return row
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Method name: getCol.
     * Pos: Returns the column that is in game board
     * @return col
     */
    public int getCol() {
        return this.col;
    }

    /**
     * Method name: getTop.
     * Pos: Returns the node that is in the top
     * @return top
     */
    public Node getTop() {
        return this.top;
    }

    /**
     * Method name: setTop.
     * Pre: The top Node variable is the new node that will be in the top; top must be different from null or empty
     * Pos: The previous top of the node is changed to the new one
     * @param top
     */
    public void setTop(Node top) {
        this.top = top;
    }

    /**
     * Method name: getBottom.
     * Pos: Returns the node that is in the bottom
     * @return bottom
     */
    public Node getBottom() {
        return this.bottom;
    }

    /**
     * Method name: setBottom.
     * Pre: The bottom Node variable is the new node that will be in the bottom; bottom must be different from null or empty
     * Pos: The previous bottom of the node is changed to the new one
     * @param bottom
     */
    public void setBottom(Node bottom) {
        this.bottom = bottom;
    }

    /**
     * Method name: getLeft.
     * Pos: Returns the node that is in the left
     * @return left
     */
    public Node getLeft() {
        return this.left;
    }

    /**
     * Method name: setLeft.
     * Pre: The left Node variable is the new node that will be in the left; left must be different from null or empty
     * Pos: The previous left of the node is changed to the new one
     * @param left
     */
    public void setLeft(Node left) {
        this.left = left;
    }

    /**
     * Method name: getRight.
     * Pos: Returns the node that is in the right
     * @return right
     */
    public Node getRight() {
        return this.right;
    }

    /**
     * Method name: setRight.
     * Pre: The right Node variable is the new node that will be in the right; right must be different from null or empty
     * Pos: The previous right of the node is changed to the new one
     * @param right
     */
    public void setRight(Node right) {
        this.right = right;
    }

    /**
     * Method name: getId.
     * Pos: Returns the identification of the node
     * @return id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Method name: setId.
     * Pre: The id int variable is the new identification that the node will have in the current game board; id must be different from null or empty
     * Pos: The previous id of the node is changed to the new one
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Method name: isStatusNode.
     * Pos: Returns the current status of the node
     * @return statusNode
     */
    public boolean isStatusNode() {
        return this.statusNode;
    }

    /**
     * Method name: getStatusNode.
     * Pos: Returns the current status of the node
     * @return statusNode
     */
    public boolean getStatusNode() {
        return this.statusNode;
    }

    /**
     * Method name: setStatusNode.
     * Pre: The statusNode boolean variable is the new status that the node will have in the current game board; statusNode must be different from null or empty
     * Pos: The previous statusNode of the node is changed to the new one
     * @param statusNode
     */
    public void setStatusNode(boolean statusNode) {
        this.statusNode = statusNode;
    }

    /**
     * Method name: getPlayers.
     * Pos: Returns all current players in the game
     * @return players
     */
    public Player getPlayers() {
        return this.players;
    }

    /**
     * Method name: getVisible.
     * Pos: The visibility of each of the necessary attributes of the game board (players, ladders, snakes, and the identification of the node)
     * @return visible
     */
    public Boolean getVisible() {
        return this.visible;
    }

    /**
     * Method name: setVisible.
     * Pre: The visible boolean variable is the new visible that the attributes of the game board (players, ladders, snakes, and the identification of the node) will have in the current game board; visible must be different from null or empty
     * Pos: The previous visible of each of the necessary attributes of the game board is changed to the new one
     * @param visible
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    /**
     * Method name: setPlayers.
     * Pre: The p Player variable is the new player that the game will have in the current game board; p must be different from null or empty
     * Pos: The previous p is changed to the new one
     * @param p
     */
    public void setPlayers(Player p) {
        this.players = p;
    }

    /**
     * Method name: toString.
     * Pos: A message is displayed with the current game board with the requested attributes (players, ladders, snakes, and the identification of the node)
     * @return msg
     */
    @Override
    public String toString() {
        String render = " ";
        if (visible == true) {
            render = String.valueOf(id);
        }
        String msg = "";
        if (snake != null) {

            msg += " " + "\033[031m" + snake + "\033[0m";
        } else if (ladders != null) {

            msg += " " + "\033[035m" + ladders + "\033[0m";
        }
        if (this.players != null) {
            msg += " " + "\033[034m" + players.getPartner(players, String.valueOf(players.getSymbol())) + "\033[0m";
        }
        return "[" + " " + render + " " + msg + " " + "]";

    }
}
