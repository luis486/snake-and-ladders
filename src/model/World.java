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
    private int sizeMatrix;

    public World(int n, int m, int as, int al) {
        numRows = n;
        numCols = m;
        amountSnakes = as;
        amountLadders = al;
        sizeMatrix = n * m;
        createWorld();
        matrixEnum(firstNode);
        generateSnakes(as, 0, 'A');
        generateLadders(al, 0, 1);
    }

    public void saveData() throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH));
        oos.writeObject(one);
        oos.close();
    }

    public void loadData() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(PATH)));

        ois.close();
    }

    private void createWorld() {
        firstNode = new Node(0, 0, 0);
        createRow(0, 0, firstNode);
    }

    private void createRow(int i, int j, Node firstRow) { // Llama a create col para que la columna sobre ese nuevo
                                                          // método
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

    // -----------------------------------------------LADDERS-----------------------------------------------------

    public void generateLadders(int ladders, int control, int ladderName) {

        int idHead = (int) (Math.random() * (sizeMatrix) + 1);
        int idEnd = (int) (Math.random() * (sizeMatrix) + 1);

        if (control < ladders) {
            if (idHead != sizeMatrix && idHead - idEnd > numCols) {
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

    // ---------------------------------------------------DICE---------------------------------------------------

    public String generateDice() {
        String msg = "";
        int valorEntero = (int) Math.floor(Math.random() * (6) + 1);
        msg = "El valor del dado es " + valorEntero;
        return msg;
    }

    // -----------------------------------------------------PLAYERS---------------------------------------------------

    public void addPlayer(char letter) {
        Player p = new Player(letter);
        addPlayer(p);
    }

    public void addPlayer(Player player) {
        if (one == null) {
            one = player;
            setPlayerInNode(player, firstNode, firstNode);

        } else {
            addPlayer(one, player);
        }
    }

    private void addPlayer(Player current, Player newPlayer) {
        if (current.getPostPlayer() == null) {
            setPlayerInNode(newPlayer, firstNode, firstNode);
            current.setPostPlayer(newPlayer);

            System.out.println(newPlayer.getSymbol());
        } else {
            addPlayer(current.getPostPlayer(), newPlayer);
        }
    }

    public void setPlayerInNode(Player player, Node current, Node firstRow) {
        if (current.getId() == player.getPosition()) {
            current.setPlayers(player);
            System.out.println("Hola" + current.getId());

        } else if (current.getRight() != null) {
            setPlayerInNode(player, current.getRight(), firstRow);
        } else if (firstRow.getBottom() != null)
            setPlayerInNode(player, firstRow.getBottom(), firstRow.getBottom());
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
