package ui;

import java.util.Scanner;

import model.*;

public class Menu {

    Scanner sc = new Scanner(System.in);

    private static final String SPACE = " ";
    private World world;
    private Snakes snakes;
    private Ladders ladders;
    private Player players;

    public void showMenu() {
        System.out.println("___________________________________________Bienvenido a su menu de confianza________________________________________________________\n");
        System.out.print("\nPor favor digite una opcion"+
                            "\n"+
                            "\n(1) Quiero jugar Snake and Ladders!"+
                            "\n(2) Deseo ver el tablero de posiciones"+
                            "\n(3) Deseo salir"+
                            "\nEscriba aqui: ");
    }

    public void createGame() {
        System.out.print("\n¿Cual opcion escojeras?"+
                            "\n"+
                            "\n(1) Quiero escoger los simbolos jugadores"+
                            "\n(2) Prefiero que se escojan aleatoriamente"+
                            "\n(3) Deseo salir"+
                            "\nEscriba aqui: ");
        int option = sc.nextInt();
        sc.nextLine();
        switch (option) {
            case 1:
                System.out.print("\n");
                System.out.print("_____________________________________JUGADORES MANUALES___________________________________________________\n");
                chooseManually();
                System.out.print("\n");
                break;
            case 2:
                System.out.print("\n");
                System.out.print("_____________________________________JUGADORES ALEATORIOS___________________________________________________\n");
                generateAutomatic();
                System.out.print("\n");
                break;
            default:
                System.out.println("La opcion ingresada es invalida, por favor elija otra opción");
                createGame();
                break;
        }
    }

    public void chooseManually() {
        System.out.print("\nPor favor indique los parametros del juego de la siguiente manera: "+
                            "\n"+
                            "\nEn una misma línea separado con espacios pondra el numero de filas, de columnas,"
                            + "cantidad de serpientes, cantidad de escaleras y por ultimo sin espacios, los simbolos de los jugadores (respectivamente)"+
                            "\nEscriba aqui: ");
        String parametros = sc.nextLine();
        String[] parts = parametros.split(SPACE);
        if(parametros.isEmpty() || parts.length<4){
            System.err.println("¡Por favor digite un valor valido!");
            chooseManually();
        }
        createWorldOne(parts);
        
    }

    public void generateAutomatic() {
        System.out.print("\nPor favor indique los parametros del juego de la siguiente manera: "+
                            "\n"+
                            "\nEn una misma línea separado con espacios pondra el numero de filas, de columnas,"
                            + "cantidad de serpientes, cantidad de escaleras y por último cantidad de jugadores"+
                            "\nEscriba aqui: ");
        String parametros = sc.nextLine();
        String[] parts = parametros.split(SPACE);
        if(parts.length==0 || parts.length<5){
            System.err.println("¡Por favor digite un valor valido!");
            generateAutomatic();
        }
        createWorldTwo(parts);
    }

    public void createWorldOne(String[] parameters) {
        world = new World(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]));
        snakes = new Snakes(Integer.parseInt(parameters[2]));
        ladders = new Ladders(Integer.parseInt(parameters[3]));

        System.out.println(world);
    }

    public void createWorldTwo(String[] parameters) {
        world = new World(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]));
        snakes = new Snakes(Integer.parseInt(parameters[2]));
        ladders = new Ladders(Integer.parseInt(parameters[3]));
        players = new Player(Integer.parseInt(parameters[4]));

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

        if (option == 3) {
            System.out.println("Gracias por usar esta aplicacion");
        } else {
            doOperation(option);
            startProgram();
        }

    }

}
