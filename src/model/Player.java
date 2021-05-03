package model;

import java.io.Serializable;

public class Player implements Serializable {

    private Player parent;
    private Player right;
    private Player left;
    private Player postPlayer;
    private Player postPlayerInNode;
    private String nickname;
    private int moves;
    private int score;
    private int position;
    private int dice;
    private int lastPosition;
    private char symbol;
    private boolean isWinner;

    /**
     * Method name: Player.
     * Pre: The symbol char variable is a symbol that the player will have in the current game board; symbol must be different from null or empty
     * Pre: The lastPosition int variable it is the starting position that the player will have in the current game board; lastPosition must be different from null or empty
     * Pos: The player is created correctly
     * @param symbol
     * @param lastPosition
     */
    public Player(char symbol, int lastPosition) {
        this.score = 0;
        this.symbol = symbol;
        this.moves = 0;
        this.position = 1;
        this.isWinner = false;
        this.nickname = "";
        this.lastPosition = lastPosition;
    }

    /**
     * Method name: getParent.
     * Pos: Returns the player who has the other players in the node
     * @return parent
     */
    public Player getParent() {
        return this.parent;
    }

    /**
     * Method name: setParent.
     * Pre: The parent Parent variable is the parent that will be in the node; parent must be different from null or empty
     * Pos: The previous parent of the node is changed to the new one
     * @param parent
     */
    public void setParent(Player parent) {
        this.parent = parent;
    }

    /**
     * Method name: getRight.
     * Pos: Returns the player which is to the right of another player in the node
     * @return right
     */
    public Player getRight() {
        return this.right;
    }

    /**
     * Method name: setRight.
     * Pre: The right Player variable is the player who will be to the right of another player in the current game board; right must be different from null or empty
     * Pos: The previous player of the right side of the node is changed to the new one
     * @param right
     */
    public void setRight(Player right) {
        this.right = right;
    }

    /**
     * Method name: getLeft.
     * Pos: Returns the player which is to the left of another player in the node
     * @return left
     */
    public Player getLeft() {
        return this.left;
    }

    /**
     * Method name: setLeft.
     * Pre: The left Player variable is the player who will be to the left of another player in the current game board; left must be different from null or empty
     * Pos: The previous player of the left side of the node is changed to the new one
     * @param left
     */
    public void setLeft(Player left) {
        this.left = left;
    }

    /**
     * Method name: getPostPlayer.
     * Pos: Returns the player next to the other player
     * @return postPlayer
     */
    public Player getPostPlayer() {
        return this.postPlayer;
    }

    /**
     * Method name: setPostPlayer.
     * Pre: The postPlayer Player variable is the player who will be to the next of another player in the current game board; postPlayer must be different from null or empty
     * Pos: The previous player of the postPlayer side of the node is changed to the new one
     * @param postPlayer
     */
    public void setPostPlayer(Player postPlayer) {
        this.postPlayer = postPlayer;
    }

    /**
     * Method name: getNickname.
     * Pos: Returns the player nickname
     * @return nickname
     */
    public String getNickname() {
        return this.nickname;
    }

    /**
     * Method name: setNickname.
     * Pre: The nickname String variable is the nickname player wich is playing on the current board; nickname must be different from null or empty
     * Pos: The nickname player is changed to the new one
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Method name: getSymbol.
     * Pos: Returns the player symbol
     * @return nickname
     */
    public char getSymbol() {
        return this.symbol;
    }

    /**
     * Method name: setSymbol.
     * Pre: The symbol char variable is the symbol player wich is playing on the current board; symbol must be different from null or empty
     * Pos: The symbol player is changed to the new one
     * @param symbol
     */
    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    /**
     * Method name: getMoves.
     * Pos: Returns the player's movements that it had in the game
     * @return moves
     */
    public int getMoves() {
        return this.moves;
    }

    /**
     * Method name: setMoves.
     * Pre: The moves int variable are the movements that the player has on the current board; moves must be different from null or empty
     * Pos: The moves player is changed to the new one
     * @param moves
     */
    public void setMoves(int moves) {
        this.moves = moves;
    }

    /**
     * Method name: getScore.
     * Pos: Returns the player's score that it had in the game
     * @return score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Method name: setScore.
     * Pre: The score int variable are the score that the player has on the current board; score must be different from null or empty
     * Pos: The score player is changed to the new one
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Method name: getPostPlayerInNode.
     * Pos: Returns the player next to the other player in the node
     * @return postPlayerInNode
     */
    public Player getPostPlayerInNode() {
        return this.postPlayerInNode;
    }

    /**
     * Method name: setPostPlayerInNode.
     * Pre: The p Player variable is the player node who will be to the next of another player in the current game board; postPlayer must be different from null or empty
     * Pos: The previous player of the postPlayer side of the node is changed to the new one
     * @param p
     */
    public void setPostPlayerInNode(Player p) {
        this.postPlayerInNode = p;
    }

    /**
     * Method name: getLastPosition.
     * Pos: Returns the player last position in the current game board
     * @return lastPosition
     */
    public int getLastPosition() {
        return this.lastPosition;
    }

    /**
     * Method name: setPostPlayerInNode.
     * Pre: The lastPosition int variable is the player last position that will have in the current game board; lastPosition must be different from null or empty
     * Pos: The previous player last position is changed to the new one
     * @param lastPosition
     */
    public void setLastPosition(int lastPosition) {
        this.lastPosition = lastPosition;
    }

    /**
     * Method name: getPosition.
     * Pos: Returns the player lposition in the current game board
     * @return position
     */
    public int getPosition() {
        return this.position;
    }

    /**
     * Method name: setPosition.
     * Pre: The position int variable is the player last position that the player will have in the current game board; lastPosition must be different from null or empty
     * Pos: The previous player last position is changed to the new one
     * @param position
     */
    public void setPosition(int position) {
        winner(position);
        this.position = position;
    }

    /**
     * Method name: getDice.
     * Pos: Returns the number that came out of the die
     * @return dice
     */
    public int getDice() {
        return this.dice;
    }

    /**
     * Method name: setDice.
     * Pre: The dice int variable is the random number that will be thrown and will help for the player to move between the game board; dice must be different from null or empty
     * Pos: The dice number is changed to the new one
     * @param dice
     */
    public void setDice(int dice) {
        this.dice = dice;
    }

    /**
     * Method name: getIsWinner.
     * Pos: Returns the winner player of the game
     * @return isWinner
     */
    public boolean getIsWinner() {
        return this.isWinner;
    }

    /**
     * Method name: setIsWinner.
     * Pre: The isWinner boolean variable it is the condition that is sent if the player is the winner or not of the current game board; isWinner must be different from null or empty
     * Pos: The isWinner condition is changed to the new one
     * @param isWinner
     */
    public void setIsWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }

    /**
     * Method name: dice.
     * Pre: The dice int variable is the number that is sent by parameter to allow the player to continue to the other square; dice must be different from null or empty
     * Pos: The player is successfully assigned the number to advance one square
     * @param d
     */
    public void dice(int d) {
        dice = d;
        int result = position + d;
        winner(result);
        moves++;
        setPostPlayerInNode(null);
    }

    /**
     * Method name: winner.
     * Pre: The result int variable which means the final score obtained by the possible winner of the current game; result must be different from null or empty
     * Pos: The result is successfully to the winner of the game
     * @param result
     */
    public void winner(int result) {
        if (result == lastPosition) {
            setIsWinner(true);
            position += dice;
        } else if (result < lastPosition) {
            position += dice;
        }
    }

    /**
     * Method name: winner.
     * Pre: The first Player variable it means if the next player that is sent by parameter is the next  to participate on the current game; first must be different from null or empty
     * Pre: The msg String variable will show the symbols that will appear on the current boarda game; msg must be different from null or empty
     * Pos: You have the symbol of the current player who is playing
     * @param first
     * @param msg
     * @return msg
     */
    public String getPartner(Player first, String msg) {
        if (first.getPostPlayerInNode() != null) {
            msg += " " + first.getPostPlayerInNode().getSymbol();
            return getPartner(first.getPostPlayerInNode(), msg);
        } else {
            return msg;
        }
    }

    /**
     * Method name: toString.
     * Pos: Returns the player's score
     * @return toString
     */
    public String toString() {
        score = moves * lastPosition;
        return String.valueOf(score);
    }
}
