package model;

import java.io.*;

public class World {

    public final String PATH = "data/leaderboard.lmh";
    private Node firstNode;
    private Player actual;
    private Player one;
    private Player root;
    private String message = "";
    private int numRows;
    private int numCols;
    private int amountSnakes;
    private int amountLadders;
    private int amountPlayers;
    private int sizeMatrix;
    private boolean visible;
    private boolean finished;

    public World() {
    }

    /**
     * Method name: World.
     * Pre: n must be different from null or empty
     * Pre: m must be different from null or empty
     * Pre: as must be different from null or empty
     * Pre: al must be different from null or empty
     * Pre: ap must be different from null or empty
     * Pos: The current game is created successfully
     * @param n The n int variable is the number of rows that the game board will have
     * @param m The m int variable is the number of columns that the game board will have
     * @param as The as int variable is the number of snakes that the game board will have
     * @param al The al int variable is the number of ladders that the game board will have
     * @param ap The ap int variable is the number of players that the game board will have
     */
    public World(int n, int m, int as, int al, int ap) {
        numRows = n;
        numCols = m;
        amountSnakes = as;
        amountLadders = al;
        amountPlayers = ap;
        sizeMatrix = n * m;
        finished = false;
        visible = true;
        createWorld();
        matrixEnum(firstNode);
        generateSnakes(as, 0, 'A');
        generateLadders(al, 0, 1);
    }

    /**
     * Method name: World.
     * Pre: n must be different from null or empty
     * Pre: m must be different from null or empty
     * Pre: as must be different from null or empty
     * Pre: al must be different from null or empty
     * Pre: The ap int variable is the number of players that the game board will have; ap must be different from null or empty
     * Pos: The current game is created successfully
     * @param n The n int variable is the number of rows that the game board will have
     * @param m The m int variable is the number of columns that the game board will have
     * @param as The as int variable is the number of snakes that the game board will have
     * @param al The al int variable is the number of ladders that the game board will have
     */
    public World(int n, int m, int as, int al) {
        numRows = n;
        numCols = m;
        amountSnakes = as;
        amountLadders = al;
        sizeMatrix = n * m;
        finished = false;
        visible = true;
        createWorld();
        matrixEnum(firstNode);
        generateSnakes(as, 0, 'A');
        generateLadders(al, 0, 1);
    }

    /**
     * Method name: getActual.
     * Pos: Returns the actual player of the game
     * @return actual
     */
    public Player getActual() {
        return this.actual;
    }

    /**
     * Method name: setActual.
     * Pre: actual must be different from null or empty
     * Pos: The previous player is changed to the new one
     * @param actual The actual Player variable is the new player who will occupy the current position in the current game
     */
    public void setActual(Player actual) {
        this.actual = actual;
    }

    /**
     * Method name: getAmountSnakes.
     * Pos: Returns the total number of snakes
     * @return amountSnakes
     */
    public int getAmountSnakes() {
        return this.amountSnakes;
    }

    /**
     * Method name: setAmountSnakes.
     * Pre: amountSnakes must be different from null or empty
     * Pos: The previous amount of snakes is changed to the new one
     * @param amountSnakes The amountSnakes int variable is the new number of snakes that the board will have
     */
    public void setAmountSnakes(int amountSnakes) {
        this.amountSnakes = amountSnakes;
    }

    /**
     * Method name: getAmountLadders.
     * Pos: Returns the total number of ladders
     * @return amountLadders
     */
    public int getAmountLadders() {
        return this.amountLadders;
    }

    /**
     * Method name: setAmountLadders.
     * Pre: amountLadders must be different from null or empty
     * Pos: The previous amount of ladders is changed to the new one
     * @param amountLadders The amountLadders int variable is the new number of ladders that the board will have
     */
    public void setAmountLadders(int amountLadders) {
        this.amountLadders = amountLadders;
    }

    /**
     * Method name: getAmountPlayers.
     * Pos: Returns the total number of players
     * @return amountPlayers
     */
    public int getAmountPlayers() {
        return this.amountPlayers;
    }

    /**
     * Method name: setAmountPlayers.
     * Pre: amountPlayers must be different from null or empty
     * Pos: The previous amount of players is changed to the new one
     * @param amountPlayers The amountPlayers int variable is the new number of players that the board will have
     */
    public void setAmountPlayers(int amountPlayers) {
        this.amountPlayers = amountPlayers;
    }

     /**
     * Method name: getFinished.
     * Pos: Returns the condition to finish the game
     * @return finished
     */
    public boolean getFinished() {
        return this.finished;
    }

    /**
     * Method name: setFinished.
     * Pre: finished must be different from null or empty
     * Pos: The previous finished is changed to the new one
     * @param finished The finished boolean variable is the new condition to finish or not the current game board
     */
    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    /**
     * Method name: getVisible.
     * Pos: Returns the visibility of the items that the square has
     * @return visible
     */
    public boolean getVisible() {
        return this.visible;
    }

    /**
     * Method name: setVisible.
     * Pre: visible must be different from null or empty
     * Pos: The previous visible is changed to the new one
     * @param visible The visible boolean variable is the new condition to show or not the items that the square has of the current game board
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Method name: getMessage.
     * Pos: Returns a message with the nickname of the players of previous games
     * @return visible
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Method name: setMessage.
     * Pre: message must be different from null or empty
     * Pos: The previous message is changed to the new one
     * @param message The message String variable is a new update message to show the new winners of the game
     */
    public void setMessage(String message) {
        this.message = message;
    }

    // -----------------------------------------PERSISTENCE----------------------------------------------------

    /**
     * Method name: saveData.
     * Pos: Game winners are saved
     * @throws java.io.IOException catch the exception that can be generated in the method 
     * @throws java.lang.ClassNotFoundException It will jump error if it does not find the class
     */
    public void saveData() throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH));
        oos.writeObject(root);
        oos.close();
    }

    /**
     * Method name: loadData.
     * Pos: A table is loaded with the winners of previous games
     * @throws java.io.IOException catch the exception that can be generated in the method 
     * @throws java.lang.ClassNotFoundException It will jump error if it does not find the class
     */
    public void loadData() throws IOException, ClassNotFoundException {
        File file = new File(PATH);
        if (file.length() > 0) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(PATH)));
            root = (Player) ois.readObject();
            ois.close();
        }
    }

    // -------------------------------CREATE-AND-PRINT-MATRIX-------------------------------------------------

    /**
     * Method name: createWorld.
     * Pos: The first node is created to start forming the matrix of linked lists
     */
    private void createWorld() {
        firstNode = new Node(0, 0, 0);
        createRow(0, 0, firstNode);
    }

    /**
     * Method name: createRow.
     * Pre: i must be different from null or empty
     * Pre: j must be different from null or empty
     * Pre: firstRow must be different from null or empty
     * Pos: The row with its respective column and its linked is successfully created
     * @param i The i int variable it will create the table row
     * @param j The j int variable it will create the complete column of the row
     * @param firstRow The firstRow Node variable it will be made a double link of the top and bottom
     */
    private void createRow(int i, int j, Node firstRow) {
        createCol(i, j + 1, firstRow, firstRow.getTop());
        if (i + 1 < numRows) {
            Node firstDownRow = new Node(i + 1, j, 0);
            firstDownRow.setTop(firstRow);
            firstRow.setBottom(firstDownRow);
            createRow(i + 1, j, firstDownRow);
        }
    }

    /**
     * Method name: createCol.
     * Pre: i must be different from null or empty
     * Pre: j must be different from null or empty
     * Pre: left must be different from null or empty
     * Pre: rowPrev must be different from null or empty
     * Pos: The column with its respective row and its linked is successfully created
     * @param i The i int variable it will identify the table row
     * @param j The j int variable it will create the complete column of the row
     * @param left The left Node variable is in charge of making the respective links of the current square
     * @param rowPrev The rowPrev Node variable it will be made a double link of the right and left
     */
    private void createCol(int i, int j, Node left, Node rowPrev) {
        if (j < numCols) {
            Node current = new Node(i, j, 0);
            current.setLeft(left);
            left.setRight(current);

            if (rowPrev != null) {
                rowPrev = rowPrev.getRight();
                current.setTop(rowPrev);
                rowPrev.setBottom(current);
            }
            createCol(i, j + 1, current, rowPrev);
        }
    }

    /**
     * Method name: toString.
     * Pos: Returns a message with the visualization of the matrix
     * @return msg
     */
    @Override
    public String toString() {
        String msg = "";
        msg = printRow(firstNode);
        return msg;
    }

    /**
     * Method name: printRow.
     * Pre: firstRow must be different from null or empty
     * Pos: Returns a message with the visualization of the row
     * @param firstRow The firstRow Node variable bring the location of the row node to be displayed in the matrix
     * @return msg
     */
    public String printRow(Node firstRow) {
        String msg = "";
        if (firstRow != null) {
            msg = printCol(firstRow) + "\n";
            msg += printRow(firstRow.getBottom());
        }
        return msg;
    }

    /**
     * Method name: printCol.
     * Pre: current must be different from null or empty
     * Pos: Returns a message with the visualization of the row
     * @param current The current Node variable bring the location of the column node to be displayed in the matrix
     * @return msg
     */
    public String printCol(Node current) {
        String msg = "";
        if (current != null) {
            if (visible == false) {
                current.setVisible(false);
            } else {
                current.setVisible(true);
            }
            msg += current.toString();
            msg += printCol(current.getRight());

        }
        return msg;
    }

    /**
     * Method name: matrixEnum.
     * Pre: firstNode must be different from null or empty
     * Pos: Performs node enumeration
     * @param firstNode The firstNode Node variable bring the location of the node to be displayed enumerated in the matrix
     */
    public void matrixEnum(Node firstNode) {
        matrixFirstRow(firstNode);
    }

    /**
     * Method name: matrixFirstRow.
     * Pre: The firstRow Node variable the identifier 1 is given to make the count of the square
     * Pos: The square is left with an identifier
     * @param firstRow Node variable the identifier
     */
    public void matrixFirstRow(Node firstRow) {
        if (firstRow.getBottom() != null) {
            matrixFirstRow(firstRow.getBottom());
        } else {
            firstRow.setId(1);
            matrixRightRow(firstRow);
        }
    }

    /**
     * Method name: matrixRightRow.
     * Pre: rightRow must be different from null or empty
     * Pos: The square is left with an identifier
     * @param rightRow The rightRow Node variable is increased to the current identifier that the node has
     */
    public void matrixRightRow(Node rightRow) {
        if (rightRow.getRight() != null) {
            rightRow.getRight().setId(rightRow.getId() + 1);
            matrixRightRow(rightRow.getRight());
        } else if (rightRow.getTop() != null) {
            rightRow.getTop().setId(rightRow.getId() + 1);
            matrixLeftRow(rightRow.getTop());
        }
    }

    /**
     * Method name: matrixLeftRow.
     * Pre: leftRow must be different from null or empty
     * Pos: The square is left with an identifier
     * @param leftRow The leftRow Node variable is increased to the current identifier that the node has
     */
    public void matrixLeftRow(Node leftRow) {
        if (leftRow.getLeft() != null) {
            leftRow.getLeft().setId(leftRow.getId() + 1);
            matrixLeftRow(leftRow.getLeft());
        } else if (leftRow.getTop() != null) {
            leftRow.getTop().setId(leftRow.getId() + 1);
            matrixRightRow(leftRow.getTop());
        }
    }

    // ----------------------------------------------SNAKES-------------------------------------------------------

    /**
     * Method name: generateSnakes.
     * Pre: snakes must be different from null or empty
     * Pre: control must be different from null or empty
     * Pre: snakeName must be different from null or empty
     * Pos: The snake is created correctly in the game board
     * @param snakes The snakes int variable is the number of snakes that the game board will have
     * @param control The control int variable it is a counter that controls the method to create the snakes one by one
     * @param snakeName The snakeName char variable It is the identification(name) that the snake will have on the game board
     */
    public void generateSnakes(int snakes, int control, char snakeName) {

        int idHead = (int) (Math.random() * (sizeMatrix) + 1);
        int idEnd = (int) (Math.random() * (sizeMatrix) + 1);

        if (control < snakes) {
            if (idHead != sizeMatrix && idHead - idEnd > numCols) {
                Node nodeHead = searchNode(idHead, firstNode, firstNode);
                Node nodeEnd = searchNode(idEnd, firstNode, firstNode);
                Snakes newSnake = new Snakes(snakeName);
                if (nodeHead != null && nodeEnd != null) {
                    nodeHead.setSnake(newSnake);
                    nodeEnd.setSnake(newSnake);
                    newSnake.setStart(nodeHead);
                    newSnake.setEnd(nodeEnd);

                    generateSnakes(snakes, control + 1, (char) (snakeName + 1));

                } else {
                    generateSnakes(snakes, control, snakeName);
                }

            } else {
                generateSnakes(snakes, control, snakeName);
            }
        }
    }

    /**
     * Method name: validateSnakes.
     * Pre: current must be different from null or empty
     * Pre: id must be different from null or empty
     * Pos: If the identifier matches the node that has the snake, a message will be sent to the player, if not, the node is returned
     * @param current The current int variable is the node that will be used to know if there is a snake located
     * @param id The id int variable is the identifier of a square to be sent
     * @return Node
     */
    public Node validateSnakes(Node current, int id) {
        if (current.getSnake().getStart().getId() == id) {
            System.out.println("El jugador " + actual.getSymbol()
                    + " cayó en el principio de una serpiente, retrocederá a la casilla "
                    + current.getSnake().getEnd().getId());
            return current.getSnake().getEnd();
        } else {
            return current;
        }
    }

    /**
     * Method name: searchNode.
     * Pre: id must be different from null or empty
     * Pre: current must be different from null or empty
     * Pre: firstRow must be different from null or empty
     * Pos: The node is successfully located
     * @param id The id int variable is the identification of the node of the game board
     * @param current The current Node variable is the node that will be used to know where it is located on the game board
     * @param firstRow The firstRow Node variable is the node that will be used to know in which position the node is located on the game board (up, down, right or left)
     * @return Node
     */
    public Node searchNode(int id, Node current, Node firstRow) {
        if (current.getId() == id && current.getStatusNode() == false) {
            return current;
        } else if (current.getRight() != null) {
            return searchNode(id, current.getRight(), firstRow);
        } else if (firstRow.getBottom() != null)
            return searchNode(id, firstRow.getBottom(), firstRow.getBottom());
        else {
            return null;
        }
    }

    // ----------------------------------------VALIDATION-SNAKE-AND-LADDERS-----------------------------------

    /**
     * Method name: validateSnakesOrLadders.
     * Pre: after must be different from null or empty
     * Pos: The node is returned with the validation
     * @param after The after Node variable is the node that will be used to validate if there is a ladder or snake located
     * @return Node
     */
    public Node validateSnakesOrLadders(Node after) {
        if (after.getSnake() != null) {
            return validateSnakes(after, after.getId());
        } else if (after.getLadders() != null) {
            return validateLadders(after, after.getId());
        } else {
            return after;
        }
    }

    // -----------------------------------------------LADDERS-----------------------------------------------------

    /**
     * Method name: generateLadders.
     * Pre: ladders must be different from null or empty
     * Pre: control must be different from null or empty
     * Pre: ladderName must be different from null or empty
     * Pos: The ladder is created correctly in the game board
     * @param ladders The ladders int variable is the number of ladders that the game board will have
     * @param control The control int variable it is a counter that controls the method to create the ladders one by one
     * @param ladderName The ladderName int variable It is the identification(number) that the ladder will have on the game board
     */
    public void generateLadders(int ladders, int control, int ladderName) {

        int idHead = (int) (Math.random() * (sizeMatrix) + 1);
        int idEnd = (int) (Math.random() * (sizeMatrix) + 1);

        if (control < ladders) {
            if (idEnd != 1 && idHead - idEnd > numCols) {
                Node nodeHead = searchNode(idHead, firstNode, firstNode);
                Node nodeEnd = searchNode(idEnd, firstNode, firstNode);
                Ladders newLadder = new Ladders(ladderName);
                if (nodeHead != null && nodeEnd != null) {
                    nodeHead.setLadders(newLadder);
                    nodeEnd.setLadders(newLadder);
                    newLadder.setStart(nodeHead);
                    newLadder.setEnd(nodeEnd);

                    generateLadders(ladders, control + 1, (ladderName + 1));
                } else {
                    generateLadders(ladders, control, ladderName);
                }

            } else {
                generateLadders(ladders, control, ladderName);
            }
        }
    }

    /**
     * Method name: validateLadders.
     * Pre: current must be different from null or empty
     * Pre: id must be different from null or empty
     * Pos: If the identifier matches the node that has the ladder, a message will be sent to the player, if not, the node is returned
     * @param current The current int variable is the node that will be used to know if there is a ladder located
     * @param id The id int variable is the identifier of a square to be sent
     * @return Node
     */
    public Node validateLadders(Node current, int id) {
        if (current.getLadders().getEnd().getId() == id) {
            System.out.println(
                    "El jugador " + actual.getSymbol() + " cayó en el final de una escalera, avanzará a la casilla "
                            + current.getLadders().getStart().getId());
            return current.getLadders().getStart();
        } else {
            return current;
        }
    }

    // ---------------------------------------------------DICE---------------------------------------------------

    /**
     * Method name: generateDice.
     * Pos: The number of the dice that the player will have to advance through the game board is generated, the player will be notified with a message
     * @return msg
     */
    public String generateDice() {
        Player player = changeActualPlayer(actual);
        String msg = "";
        int valorEntero = (int) Math.floor(Math.random() * (6) + 1);
        Node before = searchNodePosition(actual.getPosition(), firstNode, firstNode);
        setNodeBefore(before);
        actual.dice(valorEntero);
        Node after = searchNodePosition(actual.getPosition(), firstNode, firstNode);
        Node validate = validateSnakesOrLadders(after);

        if (validate.getPlayers() == null) {
            actual.setPosition(validate.getId());
            validate.setPlayers(actual);
        } else {
            actual.setPosition(validate.getId());
            setInBox(actual, validate.getPlayers());
        }

        if (actual.getIsWinner() == true) {
            setFinished(true);
        }

        msg = "El jugador " + actual.getSymbol() + " ha lanzado el dado y ha obtenido: " + valorEntero;
        actual = player;

        return msg;
    }

    // -----------------------------------------------------PLAYERS---------------------------------------------------

    /**
     * Method name: addPlayer.
     * Pre: letter must be different from null or empty
     * Pos: The player is successfully added to the game
     * @param letter The letter char variable is the identification that the player will have in the form of a symbol to play on the board
     */
    public void addPlayer(char letter) {
        Player p = new Player(letter, sizeMatrix);
        addPlayer(p);
    }

    /**
     * Method name: addPlayer.
     * Pre: The player Player variable is the player that is sent by parameter to identify if it is the first player to be added or is a player after the first
     * Pos: The player is successfully added
     * @param player Possible player to be added
     */
    public void addPlayer(Player player) {
        if (one == null) {
            one = player;
            actual = player;
            setPlayerInNode(player, firstNode, firstNode);
        } else {
            addPlayer(one, player);
        }
    }

    /**
     * Method name: addPlayer.
     * Pre: The current Player variable is the current player that is sent by parameter to identify if there is any next player
     * Pre: newPlayer must be different from null or empty
     * Pos: The player is successfully added
     * @param current Possible player to be added next to another player
     * @param newPlayer The newPlayer Player variable is the new player who will take the next position
     */
    private void addPlayer(Player current, Player newPlayer) {
        if (current.getPostPlayer() == null) {
            current.setPostPlayer(newPlayer);
            setPlayerInNode(newPlayer, firstNode, firstNode);
        } else {
            addPlayer(current.getPostPlayer(), newPlayer);
        }
    }

    /**
     * Method name: searchNodePosition.
     * Pre: id must be different from null or empty
     * Pre: current must be different from null or empty
     * Pre: firstRow must be different from null or empty
     * Pos: The node position is successfully located
     * @param id The id int variable is the identification of the node of the game board
     * @param current The current Node variable is the node that will be used to know where it is located (right or left) on the game board
     * @param firstRow The firstRow Node variable is the node that will be used to know in which position the node is located on the game board (up or down)
     * @return Node
     */
    public Node searchNodePosition(int id, Node current, Node firstRow) {
        if (current.getId() == id) {
            return current;
        } else if (current.getRight() != null) {
            return searchNodePosition(id, current.getRight(), firstRow);
        } else if (firstRow.getBottom() != null)
            return searchNodePosition(id, firstRow.getBottom(), firstRow.getBottom());
        else {
            return null;
        }
    }

    /**
     * Method name: setPlayerInNode.
     * Pre: The player Player variable is the player who will be placed in the game square
     * Pre: current must be different from null or empty
     * Pre: firstRow must be different from null or empty
     * Pos: The node position is successfully located
     * @param player player who will get into the node
     * @param current The current Node variable is the node where the player will be located
     * @param firstRow The firstRow Node variable is the node where the player will be located
     */
    public void setPlayerInNode(Player player, Node current, Node firstRow) {
        Node node = searchNodePosition(player.getPosition(), firstNode, firstNode);
        if (node.getPlayers() == null) {
            node.setPlayers(player);
        } else {
            setInBox(player, node.getPlayers());
        }
    }

    /**
     * Method name: setInBox.
     * Pre: The player Player variable is the player who will be placed in the game square
     * Pre: current must be different from null or empty
     * Pos: The player is successfully placed in the corresponding square
     * @param player player who will get into the node
     * @param firstPlayer The firstPlayer Player variable is the node where the player will be located
     */
    public void setInBox(Player player, Player firstPlayer) {
        if (firstPlayer.getPostPlayerInNode() != null) {
            setInBox(player, firstPlayer.getPostPlayerInNode());
        } else {
            firstPlayer.setPostPlayerInNode(player);
        }
    }

    /**
     * Method name: setNodeBefore.
     * Pre: before must be different from null or empty
     * Pos: The before player is successfully placed in the corresponding square
     * @param before The before Node variable is the node that will be used to position the player before the player who rolled the dice and advanced on the board
     */
    public void setNodeBefore(Node before) {
        if (actual.getPostPlayerInNode() != null) {
            before.setPlayers(actual.getPostPlayerInNode());
        } else {
            before.setPlayers(null);
        }
    }

    /**
     * Method name: changeActualPlayer.
     * Pre: player must be different from null or empty
     * Pos: The player is successfully exchanged for the new one
     * @param player The player Player variable is the player who is sent by parameter to change the before player for the new one on the game board
     * @return player
     */
    public Player changeActualPlayer(Player player) {
        if (player.getPostPlayer() != null) {
            return player.getPostPlayer();
        } else {
            return one;
        }
    }

    // --------------------------------------------------BINARY-SEARCH-TREE---------------------------------------------

    /**
     * Method name: addWinner.
     * Pre: The player Player variable it is the player who won the current game on the board, if it is the first winner of the program, it will be the first to go in root, otherwise a verification will be made to know where the player will be
     * Pos: The winner is successfully added
     * @param player possible game winner
     * @throws java.lang.ClassNotFoundException It will jump error if it does not find the class
     * @throws java.io.IOException catch the exception that may be thrown in the method
     */
    public void addWinner(Player player) throws ClassNotFoundException, IOException {
        if (root == null) {
            root = player;
        } else {
            addWinner(root, player);
        }
        saveData();
        loadData();
    }

    /**
     * Method name: addWinner.
     * Pre: The current Player variable is the current winner who is in the program, it is compared with the winner of the current game and their scores are compared to know whether or not the score of the current player who won the game or the previous one is higher
     * Pre: The newWinner Player variable is the winner of the current game and will be compared with the last current winner of the program to know if his score is higher or not and place where it corresponds
     * Pos: The winner is successfully added
     * @param current last player that the program has registered
     * @param newWinner possible game winner
     */
    private void addWinner(Player current, Player newWinner) {
        if (newWinner.getScore() >= current.getScore()) {
            if (current.getLeft() == null) {
                current.setLeft(newWinner);
            } else {
                addWinner(current.getLeft(), newWinner);
            }
        } else {
            if (current.getRight() == null) {
                current.setRight(newWinner);
            } else {
                addWinner(current.getRight(), newWinner);
            }
        }
    }

    /**
     * Method name: printWinners.
     * Pre: To show the winners there must be at least one winner; root must be different from null or empty
     * Pos: A message is displayed with the current winners, if there is not a winner, an error message is sent
     */
    public void printWinners() {
        if (root != null) {
            printWinners(root);
        } else {
            message = "¡No hay jugadores aún!";
        }
    }

    /**
     * Method name: printWinners.
     * Pre: player must be different from null or empty
     * Pos: A message is displayed with the current winners
     * @param player The player Player variable is the winning player that will be displayed in the message
     */
    private void printWinners(Player player) {
        if (player == null) {
            return;
        } else {
            printWinners(player.getLeft());
            message += "      " + player.getNickname() + "            " + player.toString() + "\n";
            printWinners(player.getRight());
        }
    }
}