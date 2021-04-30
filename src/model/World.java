package model;

import java.io.*;

public class World {

    private static final String PATH = "data/leaderboard.doc";
    private Node firstNode;
    private Player actual; // Jugador actual al momento
    private Player one; // Lista enlazada de jugadores
    private int numRows;
    private int numCols;
    private int amountSnakes;
    private int amountLadders;
    private int amountPlayers;
    private boolean visible;
    private int sizeMatrix;
    private boolean finished;

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

    public Player getActual() {
        return this.actual;
    }

    public void setActual(Player actual) {
        this.actual = actual;
    }

    public int getAmountSnakes() {
        return this.amountSnakes;
    }

    public void setAmountSnakes(int amountSnakes) {
        this.amountSnakes = amountSnakes;
    }

    public int getAmountLadders() {
        return this.amountLadders;
    }

    public void setAmountLadders(int amountLadders) {
        this.amountLadders = amountLadders;
    }

    public int getAmountPlayers() {
        return this.amountPlayers;
    }

    public void setAmountPlayers(int amountPlayers) {
        this.amountPlayers = amountPlayers;
    }

    public boolean getFinished() {
        return this.finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean getVisible() {
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    // -----------------------------------------PERSISTENCE----------------------------------------------------

    public void saveData() throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH));
        oos.writeObject(one);
        oos.close();
    }

    public void loadData() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(PATH)));

        ois.close();
    }

    // -------------------------------CREATE-AND-PRINT-MATRIX-------------------------------------------------

    private void createWorld() {
        firstNode = new Node(0, 0, 0);
        createRow(0, 0, firstNode);
    }

    private void createRow(int i, int j, Node firstRow) { // Llama a create col para que la columna sobre ese nuevo
                                                          // metodo
        createCol(i, j + 1, firstRow, firstRow.getTop());
        if (i + 1 < numRows) {
            Node firstDownRow = new Node(i + 1, j, 0);
            firstDownRow.setTop(firstRow);
            firstRow.setBottom(firstDownRow);
            createRow(i + 1, j, firstDownRow);
        }
    }

    private void createCol(int i, int j, Node left, Node rowPrev) { // Crear a lo ancho
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

    public String toString() {
        String msg = "";
        msg = printRow(firstNode);
        return msg;
    }

    public String printRow(Node firstRow) {
        String msg = "";
        if (firstRow != null) {
            msg = printCol(firstRow) + "\n";
            msg += printRow(firstRow.getBottom());
        }
        return msg;
    }

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

    public void matrixEnum(Node firstNode) {
        matrixFirstRow(firstNode);
    }

    public void matrixFirstRow(Node firstRow) {
        if (firstRow.getBottom() != null) {
            matrixFirstRow(firstRow.getBottom());
        } else {
            firstRow.setId(1);
            matrixRightRow(firstRow);
        }
    }

    public void matrixRightRow(Node rightRow) {
        if (rightRow.getRight() != null) {
            rightRow.getRight().setId(rightRow.getId() + 1);
            matrixRightRow(rightRow.getRight());
        } else if (rightRow.getTop() != null) {
            rightRow.getTop().setId(rightRow.getId() + 1);
            matrixLeftRow(rightRow.getTop());
        }
    }

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

    public Node validateSnakesOrLadders(Node after) {
        System.out.println(after.getId());
        if (after.getSnake() != null) {
            return validateSnakes(after, after.getId());
        } else if (after.getLadders() != null) {
            return validateLadders(after, after.getId());
        } else {
            return after;
        }
    }

    // -----------------------------------------------LADDERS-----------------------------------------------------

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

    public String generateDice() {
        Player pla = changeActualPlayer(actual);
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
        actual = pla;

        return msg;
    }

    // -----------------------------------------------------PLAYERS---------------------------------------------------

    public void addPlayer(char letter) {
        Player p = new Player(letter, sizeMatrix);
        addPlayer(p);
    }

    public void addPlayer(Player player) {
        if (one == null) {
            one = player;
            actual = player;
            setPlayerInNode(player, firstNode, firstNode);
        } else {
            addPlayer(one, player);
        }
    }

    private void addPlayer(Player current, Player newPlayer) {
        if (current.getPostPlayer() == null) {
            current.setPostPlayer(newPlayer);
            setPlayerInNode(newPlayer, firstNode, firstNode);
        } else {
            addPlayer(current.getPostPlayer(), newPlayer);
        }
    }

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

    public void setPlayerInNode(Player player, Node current, Node firstRow) {
        Node node = searchNodePosition(player.getPosition(), firstNode, firstNode);
        if (node.getPlayers() == null) {
            node.setPlayers(player);
        } else {
            setInBox(player, node.getPlayers());
        }
    }

    public void setInBox(Player player, Player firstPlayer) {
        if (firstPlayer.getPostPlayerInNode() != null) {
            setInBox(player, firstPlayer.getPostPlayerInNode());
        } else {
            firstPlayer.setPostPlayerInNode(player);
        }
    }

    public void setNodeBefore(Node before) {
        if (actual.getPostPlayerInNode() != null) {
            before.setPlayers(actual.getPostPlayerInNode());
        } else {
            before.setPlayers(null);
        }
    }

    public Player changeActualPlayer(Player p) {
        if (p.getPostPlayer() != null) {
            return p.getPostPlayer();
        } else {
            return one;
        }

    }

    // --------------------------------------------------BINARY-SEARCH-TREE---------------------------------------------

    /*
     * public void addWinner(Player player) throws ClassNotFoundException,
     * IOException { if (root == null) { root = player; } else { addWinner(root,
     * player); } saveData(); }
     * 
     * private void addWinner(Player current, Player newWinner) { if
     * (newWinner.getScore() <= current.getScore()) { if (current.getLeft() == null)
     * { current.setLeft(newWinner); newWinner.setParent(current); } else {
     * addWinner(current.getLeft(), newWinner); } } else { if (current.getRight() ==
     * null) { current.setRight(newWinner); newWinner.setParent(current); } else {
     * addWinner(current.getRight(), newWinner); } } }
     */
}