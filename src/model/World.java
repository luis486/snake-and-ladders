package model;

public class World {

    private Node firstNode;

    private int numRows;
    private int numCols;

    public World(int n, int m) {
        numRows = n;
        numCols = m;
        createWorld();
        matrixEnum(firstNode);
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
        } else if (rightRow.getLeft().getTop() != null) {
            rightRow.getTop().setId(rightRow.getId() + 1);
            matrixLeftRow(rightRow.getTop());
        }
    }

    public void matrixLeftRow(Node leftRow) {
        if (leftRow.getLeft() != null) {
            leftRow.getLeft().setId(leftRow.getId() + 1);
            matrixLeftRow(leftRow.getLeft());
        } else if (leftRow.getRight().getTop() != null) {
            leftRow.getTop().setId(leftRow.getId() + 1);
            matrixRightRow(leftRow.getTop());
        }
    }

    public void generateSnakes(int n, int m, int snakes) {

        if (snakes > (n * m) / 5) {
            return;
        } else {

        }
    }

    public void generateLadders(int n, int m, int ladders) {
        if (ladders > (n * m) / 4) {
            return;
        } else {

        }
    }

    public int generateDice() {
        int valorEntero = (int) Math.floor(Math.random() * (6 - 1 + 1) + 1);
        return valorEntero;
    }

    public int getId(int n, int m) {
        int result = n * m;
        result--;
        return result;
    }

}
