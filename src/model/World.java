package model;

public class World {

    private static Node firstNode;
    

    public static void generate(int x, int y) {

        World.firstNode = GenerateNodes(1, 1, x, y, World.firstNode, 1, null, 0);
        //printConnections(World.firstNode);
        String map = printMap(x, 1, x, y, "");
        System.out.println(map);
    }

    public static String getMapNumberFromGrid(int x, int y, int weidth, int height) {
        String ret = "";
        int yMultiplier = ( (y - 1) * (weidth));
        int xOffset = 0;
        if (y % 2 == 0) {
            xOffset = (weidth - x) +1;
        } else {
            xOffset = x;
            
        }
        int gridNumber =  yMultiplier + xOffset;
        ret ="["+ gridNumber +"]";
        return ret;
    }

    public static String printMap(int x, int y,  int weidth, int height, String map) {
        if (y > height) {
            return map;
        }
        if (y < 1) {
            return map;
        }
        map =   getMapNumberFromGrid(x, y, weidth, height) + map; 
        x--;
        if (x > weidth) {
            map =   "\n" + map; 
            x = 1;
            y = y + 1;
        }
        if (x == 0) {
            map =   "\n" + map; 
            x = weidth;
            y = y + 1;
        }
        map = printMap( x, y,  weidth, height, map);
        return map;
    }

    public static boolean canGenerate(int x, int y, int weidth, int height) {
        if ( x < 1 || y < 1 ) {
            return false;
        }

        if (x > weidth || y > height ) {
            return false;
        }
        return true;
    }

    public static Node GenerateNodes(int x, int y, int weidth, int height, Node node, int direction, Node parent, int counter) {
        if ( !canGenerate( x,  y,  weidth,  height)) {
            return null;
        }
        counter++;
        if (counter > 50000) {
            return null;
        }
        node = new Node(x, y, "");
        int x1 = x + 1;
        int x2 = x - 1;
        int y1 = y + 1;
        int y2 = y - 1;

       if (direction == 1) {
            node.bottom =  parent;
            if ( canGenerate( x2,  y,  weidth,  height)) {
                node.left   =  new Node(x2, y, "");
            }
        } else {
            node.left =  parent;
            if ( canGenerate( x,  y2,  weidth,  height)) {
                node.bottom   =  new Node(x2, y, "");
            }
        }
        node.top    = GenerateNodes(x, y1,  weidth,  height, node.top, 1, node, counter);
        node.right  = GenerateNodes(x1, y,  weidth,  height, node.right, 2, node,counter);
        return node;
    }
   
    
}
