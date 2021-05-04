package model;

public class Ladders {

    private int number;
    private Node start;
    private Node end;

    /**
     * Method name: Ladders.
     * Pre: number must be different from null or empty
     * Pos: The number of ladders is created correctly
     * @param number The number int variable is the number of ladders that the current game board will have
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
     * Pre: number must be different from null or empty
     * Pos: The previous number of ladders is changed to the new one
     * @param number The number int variable is the new number of ladders that the board will have
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
     * Pre: start must be different from null or empty
     * Pos: The previous start node of the ladder is changed to the new one
     * @param start The start node variable is the beginning of the ladder that it will have
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
     * Pre: end must be different from null or empty
     * Pos: The previous end node of the ladder is changed to the new one
     * @param end The end node variable is the end of the ladder that will have
     */
    public void setEnd(Node end) {
        this.end = end;
    }

    /**
     * Method name: toString.
     * Pos: The total amount of stairs that the game will have is returned
     * @return toString
     */
    @Override
    public String toString() {
        return String.valueOf(getNumber());
    }
}
