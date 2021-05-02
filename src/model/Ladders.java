package model;

public class Ladders {

    private int number;
    private Node start;
    private Node end;

    /**
     * Method name: Ladders.
     * Pre: The number int variable is the number of ladders that the current game board will have; number must be different from null or empty
     * Pos: The number of ladders is created correctly
     * @param number
     */
    public Ladders(int number) {
        this.number = number;
    }

    /**
     * Method name: getNumber.
     * Pos: Returns the total number of ladders
     * @return number
     */
    public int getNumber() {
        return this.number;
    }

    /**
     * Method name: setNumber.
     * Pre: The number int variable is the new number of ladders that the board will have; number must be different from null or empty
     * Pos: The previous number of ladders is changed to the new one
     * @param number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Method name: getStart.
     * Pos: Returns the beginning node of the ladder
     * @return start
     */
    public Node getStart() {
        return this.start;
    }

    /**
     * Method name: setStart.
     * Pre: The start node variable is the beginning of the ladder that will have; start must be different from null or empty
     * Pos: Returns the initial node of the ladder
     * @param start
     */
    public void setStart(Node start) {
        this.start = start;
    }

    /**
     * Method name: getEnd.
     * Pos: Returns the final node of the ladder
     * @return end
     */
    public Node getEnd() {
        return this.end;
    }

    /**
     * Method name: setEnd.
     * Pre: The end node variable is the end of the ladder that will have; end must be different from null or empty
     * Pos: Returns the initial node of the ladder
     * @param end
     */
    public void setEnd(Node end) {
        this.end = end;
    }

    /**
     * Method name: toString.
     * Pos: The total amount of stairs that the game will have is returned
     * @return number
     */
    @Override
    public String toString() {
        return String.valueOf(getNumber());
    }
}
