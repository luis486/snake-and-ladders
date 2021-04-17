package ui;

import java.util.Scanner;

public class Menu {

    Scanner sc = new Scanner(System.in);
 

    public void showMenu() {
        System.out.println("Bienvenido a su menu de confianza");
        System.out.println("Por favor digite una opcion");
        System.out.println("(1) Quiero jugar Snake and Ladders!");
        System.out.println("(2) Deseo ver el tablero de posiciones");
        System.out.println("(3) Deseo salir");
    }

    public void doOperation(int option) {
        switch (option) {
        case 1:
            ;
            break;

        case 2:
            ;
            break;
        case 3:
            System.out.println("Gracias por usar esta aplicacion!");
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
        int option;
        do {
            showMenu();
            option = readOption();
            doOperation(option);
        } while (option != 3);
    }
    
}
