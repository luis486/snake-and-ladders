package model;

public class Ladders {

    private int number;
    private Node start;
    private Node end;
    private int amountLadders;

    public Ladders(int amountLadders) {
        this.amountLadders = amountLadders;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
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
