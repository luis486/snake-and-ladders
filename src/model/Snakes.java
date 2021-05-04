package model;

public class Snakes {

    private char letter;
    private Node start;
    private Node end;

    /**
     * Method name: Snakes.
     * Pre: letter must be different from null or empty
     * Pos: The snakes is created correctly
     * @param letter The letter char variable is a letter that the snake have in the current game board
     */
    public Snakes(char letter) {
        this.letter = letter;
    }

    /**
     * Method name: getLetter.
     * Pos: Returns the letter that snake it has in the node
     * @return letter
     */
    public char getLetter() {
        return this.letter;
    }

    /**
     * Method name: setLetter.
     * Pre: letter must be different from null or empty
     * Pos: The previous letter of the snake is changed to the new one
     * @param letter The letter char variable is the letter that the snake will have in the node
     */
    public void setLetter(char letter) {
        this.letter = letter;
    }
    
    /**
     * Method name: getStart.
     * Pos: Returns the beginning node of the snake
     * @return start
     */
    public Node getStart() {
        return this.start;
    }

    /**
     * Method name: setStart.
     * Pre: start must be different from null or empty
     * Pos: The previous start node of the snake is changed to the new one
     * @param start The start node variable is the beginning of the snake that it will have
     */
    public void setStart(Node start) {
        this.start = start;
    }

    /**
     * Method name: getEnd.
     * Pos: Returns the final node of the snake
     * @return end
     */
    public Node getEnd() {
        return this.end;
    }

    /**
     * Method name: setEnd.
     * Pre: end must be different from null or empty
     * Pos: The previous end node of the snake is changed to the new one
     * @param end The end node variable of the snake that it will have
     */
    public void setEnd(Node end) {
        this.end = end;
    }

    /**
     * Method name: toString.
     * Pos: Returns The letter of the snake that the node have
     * @return toString
     */
    @Override
    public String toString() {
        return String.valueOf(getLetter());
    }
}
