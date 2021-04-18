package ui;

import java.util.Scanner;

import model.World;

public class Menu {

    Scanner sc = new Scanner(System.in);
    
    private static final String SPACE =" ";
    private World world;

    public void showMenu() {
        System.out.println("Bienvenido a su menu de confianza");
        System.out.println("Por favor digite una opcion");
        System.out.println("(1) Quiero jugar Snake and Ladders!");
        System.out.println("(2) Deseo ver el tablero de posiciones");
        System.out.println("(3) Deseo salir");
    }

    public void createGame() {
        System.out.println("Por favor indique los parametros del juego de la siguiente manera: ");
        System.out.println("En una misma línea separado con espacios pondra el numero de filas, de columnas,"
        + "cantidad de serpientes, cantidad de escaleras y por ultimo sin espacios, los simbolos de los jugadores (respectivamente)");
        String parametros = sc.nextLine();
        createWorld(parametros);  
        return;
    }


    public void createWorld(String parametros){
        String[] parts = parametros.split(SPACE);
        world = new World(Integer.parseInt(parts[0]),Integer.parseInt(parts[1]));
        System.out.println(world);
    }

    public void doOperation(int option) {
        switch (option) {
        case 1:
        createGame();
            break;

        case 2:
            ;
            break;
        default:
            System.out.println("Error, opcion invalida, por favor digite otra opcion");
        }
    }

    public int readOption() {
        int option = sc.nextInt();
        sc.nextLine();
        return option;
    }

    public void startProgram() {
        showMenu();
        int option = readOption();

        if (option == 3){
            System.out.println("Gracias por usar esta aplicacion");
        }else{
            doOperation(option);
            startProgram();
        }
      
    }
    
}
