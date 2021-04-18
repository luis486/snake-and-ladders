package model;

public class Snakes {

    private char letter;
    private Node start;
    private Node end;


    public Snakes(char letter, Node start, Node end) {
        this.letter = letter;
        this.start = start;
        this.end = end;
    }

    public char getLetter() {
        return this.letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public Node getStart() {
        return this.start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getEnd() {
        return this.end;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

    
}
