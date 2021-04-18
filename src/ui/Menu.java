package ui;

import java.util.Scanner;

import model.World;

public class Menu {

    Scanner sc = new Scanner(System.in);
    
 

    public void showMenu() {
        System.out.println("Bienvenido a su menu de confianza");
        System.out.println("Por favor digite una opcion");
        System.out.println("(1) Quiero jugar Snake and Ladders!");
        System.out.println("(2) Deseo ver el tablero de posiciones");
        System.out.println("(3) Deseo salir");
    }

    public void createGame() {

        //
        /*
        
        System.out.println("Indique los parametros del juego: ");
        String parametros = sc.nextLine();
        String[] arrOfStr = parametros.split(" ", 6);
        for (int i = 0; i < arrOfStr.length; i++) {
            System.out.println(arrOfStr[i]);
        }
        */
        
        return;
    }

    public void doOperation(int option) {
        switch (option) {
        case 1:
        World world = new World(5, 5); ;
        System.out.println(world);
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
