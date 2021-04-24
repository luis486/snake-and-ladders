package model;

public class Player {
    
    private String nickname;
    private char symbol;
    private int moves;
    private int score;

    public Player(char symbol) {
        this.score = 0;
        this.symbol = symbol;
        this.moves = 0;
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
