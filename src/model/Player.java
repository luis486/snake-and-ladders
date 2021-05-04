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
     * Pre: symbol must be different from null or empty
     * Pre: lastPosition must be different from null or empty
     * Pos: The player is created correctly
     * @param symbol The symbol char variable is a symbol that the player will have in the current game board
     * @param lastPosition The lastPosition int variable it is the starting position that the player will have in the current game board
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
     * Pre: parent must be different from null or empty
     * Pos: The previous parent of the node is changed to the new one
     * @param parent The parent Parent variable is the parent that will be in the node
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
     * Pre: right must be different from null or empty
     * Pos: The previous player of the right side of the node is changed to the new one
     * @param right The right Player variable is the player who will be to the right of another player in the current game board
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
     * Pre: left must be different from null or empty
     * Pos: The previous player of the left side of the node is changed to the new one
     * @param left The left Player variable is the player who will be to the left of another player in the current game board
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
     * Pre: postPlayer must be different from null or empty
     * Pos: The previous player of the postPlayer side of the node is changed to the new one
     * @param postPlayer The postPlayer Player variable is the player who will be to the next of another player in the current game board
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
     * Pre: nickname must be different from null or empty
     * Pos: The nickname player is changed to the new one
     * @param nickname The nickname String variable is the nickname player wich is playing on the current board
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
     * Pre: symbol must be different from null or empty
     * Pos: The symbol player is changed to the new one
     * @param symbol The symbol char variable is the symbol player wich is playing on the current board
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
     * Pre: moves must be different from null or empty
     * Pos: The moves player is changed to the new one
     * @param moves The moves int variable are the movements that the player has on the current board
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
     * Pre: score must be different from null or empty
     * Pos: The score player is changed to the new one
     * @param score The score int variable are the score that the player has on the current board
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
     * Pre: p must be different from null or empty
     * Pos: The previous player of the postPlayer side of the node is changed to the new one
     * @param p The p Player variable is the player node who will be to the next of another player in the current game board
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
     * Method name: setLastPosition.
     * Pre: lastPosition must be different from null or empty
     * Pos: The previous player last position is changed to the new one
     * @param lastPosition The lastPosition int variable is the player last position that will have in the current game board
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
     * Pre: position must be different from null or empty
     * Pos: The previous player last position is changed to the new one
     * @param position The position int variable is the player last position that the player will have in the current game board
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
     * Pre: dice must be different from null or empty
     * Pos: The dice number is changed to the new one
     * @param dice The dice int variable is the random number that will be thrown and will help for the player to move between the game board
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
     * Pre: isWinner must be different from null or empty
     * Pos: The isWinner condition is changed to the new one
     * @param isWinner The isWinner boolean variable it is the condition that is sent if the player is the winner or not of the current game board
     */
    public void setIsWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }

    /**
     * Method name: dice.
     * Pre: d must be different from null or empty
     * Pos: The player is successfully assigned the number to advance one square
     * @param d The d int variable is the number that is sent by parameter to allow the player to continue to the other square
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
     * Pre: result must be different from null or empty
     * Pos: The result is successfully to the winner of the game
     * @param result The result int variable which means the final score obtained by the possible winner of the current game
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
     * Method name: getPartner.
     * Pre: first must be different from null or empty
     * Pre:msg must be different from null or empty
     * Pos: You have the symbol of the current player who is playing
     * @param first The first Player variable it means if the next player that is sent by parameter is the next  to participate on the current game
     * @param msg  The msg String variable will show the symbols that will appear on the current boarda game
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
