package model;

import java.io.Serializable;

public class Player implements Serializable {

    private Player parent;
    private Player right;
    private Player left;
    private Player prePlayer;
    private Player postPlayer;
    private String nickname;
    private int amountPlayers;
    private char symbol;
    private int moves;
    private int score;

    public Player(char symbol) {
        this.score = 0;
        this.symbol = symbol;
        this.moves = 0;
    }

    public Player(int amountPlayers) {
        this.amountPlayers = amountPlayers;
    }

    public int getAmountPlayers() {
        return this.amountPlayers;
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

    public Player getPrePlayer() {
        return this.prePlayer;
    }

    public void setPrePlayer(Player prePlayer) {
        this.prePlayer = prePlayer;
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

}
