package model;

public class Player {
    
    private String nickname;
    private char symbol;


    public Player(String nickname, char symbol) {
        this.nickname = nickname;
        this.symbol = symbol;
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

}
