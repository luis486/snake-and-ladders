package model;
public class World {

    private Node firstNode;
    private int numRows;
    private int numCols;

    public World(int n, int m){
        numRows = n;
        numCols = m;
        createWorld();
    }

    private void createWorld(){
        firstNode = new Node(0,0);
        createRow(0,0,firstNode);
    }

    private void createRow(int i, int j, Node firstRow){ //Llama a create col para que la columna sobre ese nuevo método
        createCol(i,j+1,firstRow);
        if(i+1 < numRows){
            Node firstDownRow = new Node(i+1,j);
            firstDownRow.setTop(firstRow);
            firstRow.setBottom(firstDownRow);
            createRow(i+1, j, firstDownRow);
        }
    }
    
    private void createCol(int i, int j, Node left){ //Crear a lo ancho
        if(j<numCols){
            Node current = new Node(i,j);
            current.setLeft(left);
            left.setRight(current);
            
            /*if(rowPrev != null){
                rowPrev = rowPrev.getRight();
                current.setTop(rowPrev);
                rowPrev.setBottom(current);
            }*/

            createCol(i,j+1, current);
        }
    }

    public String toString(){
        String msg = "";
        msg = printRow(firstNode);
        return msg;
    }
    
    public String printRow(Node firstRow){
        String msg = "";
        if(firstRow != null){
            msg = printCol(firstRow) + "\n";
            msg += printRow(firstRow.getBottom());
        }
        return msg;
    }

    public String printCol(Node current){
        String msg = "";
        if(current != null){
            msg = current.toString();
            msg += printCol(current.getRight());
        }

        return msg;
    }
   
    
}
