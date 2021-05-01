package ui;

import java.io.IOException;
import java.util.Scanner;
import model.*;

public class Menu {

    Scanner sc = new Scanner(System.in);

    private static final String SPACE = " ";
    private World world;

    // -----------------------------------------------------MENU-METHODS------------------------------------------

    public void showMenu() {
        System.out.println("\033[035m___________________________________________Bienvenido a su menu de confianza________________________________________________________\n"+"\033[0m");
        System.out.print("\nPor favor digite una opcion" + "\n" + "\n(1) Quiero jugar Snake and Ladders!"
                + "\n(2) Deseo ver el tablero de posiciones" + "\n(3) Deseo salir" + "\nEscriba aqui: ");
    }

    public void createGame() {
        System.out.print("\n¿Cual opcion escojeras?" + "\n" + "\n(1) Quiero escoger los simbolos jugadores"
                + "\n(2) Prefiero que se escojan aleatoriamente" + "\nEscriba aqui: ");
        int option = sc.nextInt();
        sc.nextLine();
        switch (option) {
            case 1:
                System.out.print("\n");
                System.out.print("\033[035m_____________________________________JUGADORES MANUALES___________________________________________________\n"+"\033[0m");
                chooseManually();
                System.out.print("\n");
                break;
            case 2:
                System.out.print("\n");
                System.out.print("\033[035m_____________________________________JUGADORES ALEATORIOS___________________________________________________\n"+"\033[0m");
                generateAutomatic();
                System.out.print("\n");
                break;
        }
    }

    public void doOperation(int option) {
        switch (option) {
            case 1:
                createGame();
                break;
            case 2:
                showLeaderBoard();
                break;
            default:
                System.out.print("\n");
                System.err.println("La opcion ingresada es invalida, por favor elija opcion que se le pide");
                System.out.print("\n");
                break;
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
            System.out.print("\n");
            System.out.print("\033[035m_____________________________________APLICACION CERRADA________________________________________________________\n"+"\033[0m");
            System.out.print("\n");
        } else {
            doOperation(option);
            startProgram();
        }
    }

    // ------------------------------------------------------MANUALLY-PLAYERS--------------------------------------

    public void chooseManually() {
        System.out.print("\nPor favor indique los parametros del juego de la siguiente manera: " + "\n"
                + "\nEn una misma línea separado con espacios pondra el numero de filas, de columnas,"
                + "cantidad de serpientes, cantidad de escaleras y por ultimo sin espacios, los simbolos de los jugadores (respectivamente)"
                + "\nEscriba aqui: ");
        String parameters = sc.nextLine();
        String[] parts = parameters.split(SPACE);
        if (parts.length != 5) {
            System.err.println("¡Por favor digite los valores indicados!");
            chooseManually();
        }

        int snakes = Integer.parseInt(parts[2]);
        int ladders = Integer.parseInt(parts[3]);
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);

        if (snakes > (n * m) / 5 || ladders > (n * m) / 5) {
            System.err.println("La cantidad de serpientes o escaleras no pueden sobrepasar el 40% de la cantidad de casillas del juego");
            chooseManually();
        } else {
            System.out.println("\n");
            System.out.println("************TABLERO GENERADO POR LOS PARAMETROS ANTERIORMENTE PEDIDOS**********");
            createWorldManually(parts);
        }
        assignManually(parts[4], 0);
        System.out.println(world);
        initializeGame(false);
    }

    public void assignManually(String parameters, int contador) {
        if (contador < parameters.length()) {
            world.addPlayer(parameters.charAt(contador));
            assignManually(parameters, contador + 1);
        }
    }

    public void createWorldManually(String[] parameters) {
        world = new World(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]),
                Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3]));

        try {
            world.loadData();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------AUTOMATIC-PLAYERS-----------------------------------------

    public void generateAutomatic() {
        System.out.print("\nPor favor indique los parametros del juego de la siguiente manera: " + "\n"
                + "\nEn una misma línea separado con espacios pondra el numero de filas, de columnas,"
                + "cantidad de serpientes, cantidad de escaleras y por último cantidad de jugadores"
                + "\nEscriba aqui: ");
        String parameters = sc.nextLine();
        String[] parts = parameters.split(SPACE);
        if (parts.length != 5) {
            System.err.println("¡Por favor digite un valor valido!");
            generateAutomatic();
        }

        int snakes = Integer.parseInt(parts[2]);
        int ladders = Integer.parseInt(parts[3]);
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        int players = Integer.parseInt(parts[4]);

        if (players > 9) {
            System.err.println("La cantidad de jugadores no puede ser mayor a 9, porque no hay mas símbolos!");
            generateAutomatic();
        }

        if (snakes > (n * m) / 5 || ladders > (n * m) / 5) {
            System.err.println("La cantidad de serpientes o escaleras no pueden sobrepasar el 40% de la cantidad de casillas del juego");
            generateAutomatic();
        } else {
            System.out.println("\n");
            System.out.println("************TABLERO GENERADO POR LOS PARAMETROS ANTERIORMENTE PEDIDOS**********");
            createWorldAutomatic(parts);
        }
        assignAutomatic(Integer.parseInt(parts[4]), 0);
        System.out.println(world);
        initializeGame(false);
    }

    public void assignAutomatic(int amount, int contador) {
        if (contador < amount) {
            world.addPlayer(generateRandom(contador + 1));
            assignAutomatic(amount, contador + 1);
        }
    }

    public char generateRandom(int option) {
        char car = ' ';
        switch (option) {
            case 1:
                car = '#';
                break;
            case 2:
                car = '$';
                break;
            case 3:
                car = '%';
                break;
            case 4:
                car = '&';
                break;
            case 5:
                car = '/';
                break;
            case 6:
                car = '(';
                break;
            case 7:
                car = ')';
                break;
            case 8:
                car = '.';
                break;
            case 9:
                car = '*';
                break;
        }
        return car;
    }

    public void createWorldAutomatic(String[] parameters) {
        world = new World(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]),
                Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3]), Integer.parseInt(parameters[4]));
        try {
            world.loadData();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ----------------------------------GAME-SIMULATION-------------------------------------------------------

    public void gameSimulation() {
        if (!world.getFinished()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(world.generateDice());
            System.out.println(world);
            gameSimulation();

        } else {
            showWinner();
        }
    }

    // ---------------------------------------------GAME-INITIALIZE--------------------------------------------

    public void initializeGame(boolean render) {
        if (render == false) {
            System.out.println("\033[036mPor favor ingrese un salto de línea (enter) para continuar"+"\033[0m");
            String jump = sc.nextLine();
            if (jump.equals("")) {
                System.out.println(world.generateDice());
                world.setVisible(false);
                System.out.println(world);
                initializeGame(world.getFinished());
            } else if (jump.equalsIgnoreCase("simul")) {
                System.out.println("\n");
                System.out.println("************USTED ESTA EN UNA SIMULACION DEL JUEGO ACTUAL************");
                System.out.println("\n");
                gameSimulation();

            } else if (jump.equalsIgnoreCase("menu")) {
                System.out.println("Usted ha elegido devolverse al menu principal, gracias por jugar");
                return;
            } else if (jump.equalsIgnoreCase("num")) {
                System.out.print("\n");
                System.out.println("************TABLERO ENUMERADO************");
                world.setVisible(true);
                System.out.println(world);
                initializeGame(render);
            } else {
                System.out.print("\n");
                System.err.println("El salto de línea debe estar vacío!");
                System.out.print("\n");
                initializeGame(render);
            }
        } else {
            showWinner();
        }
    }

    // -------------------------------------------------WINNERS---------------------------------------------------

    public void showWinner() {
        System.out.println("\033[032m¡EL JUGADOR " + world.getActual().getSymbol() + " HA GANADO CON UN TOTAL DE "
                + world.getActual().getMoves() + " MOVIMIENTOS!"+"\033[0m");
        calculateWinner();
    }

    public void calculateWinner() {
        System.out.print("Por favor ingrese su nickname: ");
        String nickname = sc.nextLine();
        System.out.print("\n");
        if (nickname.equals("")) {
            System.err.println("Su nickname no puede estar vacio, por favor, vuelva a intentarlo!\n");
            calculateWinner();
        } else {
            world.getActual().setNickname(nickname);
            try {
                world.addWinner(world.getActual());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                world.saveData();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                world.loadData();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("************USTED PODRÁ VER SU PUNTAJE EN LA OPCIÓN (2) DEL MENÚ DE INICIO************");
        }
    }

    public void showLeaderBoard() {
        World w = new World();
        try {
            w.loadData();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print("\n");
        System.out.print("\033[035m_____________________________________PUNTAJES DE JUGADORES________________________________________________________\n"+"\033[0m");
        System.out.print("\n");
        System.out.println("Nombre del jugador " + " Puntaje del jugador");
        w.printWinners();
        System.out.println(w.getMessage());
        w.setMessage("");

    }

}
