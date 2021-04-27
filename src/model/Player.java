package model;

import java.io.Serializable;

public class Player implements Serializable {

    private Player parent;
    private Player right; // Arbol Binario
    private Player left;
    private Player postPlayer; // In game
    private Player postPlayerInNode; // En cada casilla
    private String nickname;
    private char symbol;
    private int moves;
    private int score;
    private int position;
    private int dice;
    private boolean isWinner;

    public Player(char symbol) {
        this.score = 0;
        this.symbol = symbol;
        this.moves = 0;
        this.position = 1;
        this.isWinner = false;
    }

    public Player getParent() {
        return this.parent;
    }

    public void setParent(Player parent) {
        this.parent = parent;
    }

    public Player getRight() {
        return this.right;
    }

    public void setRight(Player right) {
        this.right = right;
    }

    public Player getLeft() {
        return this.left;
    }

    public void setLeft(Player left) {
        this.left = left;
    }

    public Player getPostPlayer() {
        return this.postPlayer;
    }

    public void setPostPlayer(Player postPlayer) {
        this.postPlayer = postPlayer;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public char getSymbol() {
        return this.symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public int getMoves() {
        return this.moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Player getPostPlayerInNode() {
        return this.postPlayerInNode;
    }

    public void setPostPlayerInNode(Player p) {
        if (postPlayerInNode != null) {
            postPlayerInNode.setPostPlayerInNode(p);
        } else {
            this.postPlayerInNode = p;
        }
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getDice() {
        return this.dice;
    }

    public void setDice(int dice) {
        this.dice = dice;
    }

    public boolean isIsWinner() {
        return this.isWinner;
    }

    public boolean getIsWinner() {
        return this.isWinner;
    }

    public void setIsWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }

    public String getPartner() {
        String msg = String.valueOf(symbol);
        if (postPlayerInNode != null) {
            msg += postPlayerInNode.getPartner();
        }
        return msg;
    }

    @Override
    public String toString() {
        return getPartner();
    }
}
