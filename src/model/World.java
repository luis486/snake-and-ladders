package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class World {

    private static final String PATH = "data/leaderboard.doc";
    private Node firstNode;
    private Player root;
    private Player one;
    private int numRows;
    private int numCols;

    public World(int n, int m) {
        numRows = n;
        numCols = m;
        createWorld();
        matrixEnum(firstNode);
    }

    public void saveData() throws IOException, ClassNotFoundException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PATH));
        oos.writeObject(root);
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
            msg = current.toString();
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

    public void generateSnakes(int n, int m, int snakes) {

        if (snakes > (n * m) / 5) {
            return;
        } else {

        }
    }

    // -----------------------------------------------LADDERS-----------------------------------------------------

    public void generateLadders(int n, int m, int ladders) {
        if (ladders > (n * m) / 4) {
            return;
        } else {

        }
    }

    // ---------------------------------------------------DICE---------------------------------------------------

    public String generateDice() {
        String msg = "";
        int valorEntero = (int) Math.floor(Math.random() * (6 - 1 + 1) + 1);
        msg = "El valor del dado es " + valorEntero;
        return msg;
    }

    // -----------------------------------------------------PLAYERS---------------------------------------------------

    public void addPlayer(Player player) {
        if (one == null) {
            one = player;
        } else {
            addPlayer(one, player);
        }
    }

    private void addPlayer(Player current, Player newPlayer) {
        if (current.getPostPlayer() == null) {
            current.setPostPlayer(newPlayer);
            newPlayer.setPrePlayer(current);
        } else {
            addPlayer(current.getPostPlayer(), newPlayer);
        }
    }

    public Player searchPlayer(char simbol) {
        return searchPlayer(one, simbol);
    }

    private Player searchPlayer(Player current, char simbol) {
        if (current == null || current.getSymbol() == simbol) {
            return current;
        } else {
            return searchPlayer(current.getPostPlayer(), simbol);
        }
    }

    // --------------------------------------------------BINARY-SEARCH-TREE---------------------------------------------

    public void addWinner(Player player) throws ClassNotFoundException, IOException {
        if (root == null) {
            root = player;
        } else {
            addWinner(root, player);
        }
        saveData();
    }

    private void addWinner(Player current, Player newWinner) {
        if (newWinner.getScore() <= current.getScore()) {
            if (current.getLeft() == null) {
                current.setLeft(newWinner);
                newWinner.setParent(current);
            } else {
                addWinner(current.getLeft(), newWinner);
            }
        } else {
            if (current.getRight() == null) {
                current.setRight(newWinner);
                newWinner.setParent(current);
            } else {
                addWinner(current.getRight(), newWinner);
            }
        }
    }

    public Player searchWinner(int score) {
        return searchWinner(root, score);
    }

    private Player searchWinner(Player current, int score) {
        if (current == null || current.getScore() == score) {
            return current;
        } else if (current.getScore() < score) {
            return searchWinner(current.getRight(), score);
        } else {
            return searchWinner(current.getLeft(), score);
        }
    }

}
