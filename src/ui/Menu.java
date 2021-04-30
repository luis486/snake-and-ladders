package ui;

import java.util.Scanner;

import javafx.application.Platform;
import model.*;

public class Menu {

    Scanner sc = new Scanner(System.in);

    private static final String SPACE = " ";
    private World world;

    public void showMenu() {
        System.out.println(
                "___________________________________________Bienvenido a su menu de confianza________________________________________________________\n");
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
                System.out.print(
                        "_____________________________________JUGADORES MANUALES___________________________________________________\n");
                chooseManually();
                System.out.print("\n");
                break;
            case 2:
                System.out.print("\n");
                System.out.print(
                        "_____________________________________JUGADORES ALEATORIOS___________________________________________________\n");
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
        System.out.print("\nPor favor indique los parametros del juego de la siguiente manera: " + "\n"
                + "\nEn una misma línea separado con espacios pondra el numero de filas, de columnas,"
                + "cantidad de serpientes, cantidad de escaleras y por ultimo sin espacios, los simbolos de los jugadores (respectivamente)"
                + "\nEscriba aqui: ");
        String parameters = sc.nextLine();
        String[] parts = parameters.split(SPACE);
        if (parts.length != 5) {
            System.out.println("¡Por favor digite los valores indicados!");
            chooseManually();
        }

        int snakes = Integer.parseInt(parts[2]);
        int ladders = Integer.parseInt(parts[3]);
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);

        if (snakes > (n * m) / 5 || ladders > (n * m) / 5) {
            System.out.println(
                    "La cantidad de serpientes o escaleras no pueden sobrepasar el 40% de la cantidad de casillas del juego");
            chooseManually();
        } else {
            createWorldOne(parts);
        }
        assignManually(parts[4], 0);
        System.out.println(world);
        initializeGame(false);

    }

    public void generateAutomatic() {
        System.out.print("\nPor favor indique los parametros del juego de la siguiente manera: " + "\n"
                + "\nEn una misma línea separado con espacios pondra el numero de filas, de columnas,"
                + "cantidad de serpientes, cantidad de escaleras y por último cantidad de jugadores"
                + "\nEscriba aqui: ");
        String parameters = sc.nextLine();
        String[] parts = parameters.split(SPACE);
        if (parts.length != 5) {
            System.out.println("¡Por favor digite un valor valido!");
            generateAutomatic();
        }

        int snakes = Integer.parseInt(parts[2]);
        int ladders = Integer.parseInt(parts[3]);
        int n = Integer.parseInt(parts[0]);
        int m = Integer.parseInt(parts[1]);
        int players = Integer.parseInt(parts[4]);

        if (players > 9) {
            System.out.println("La cantidad de jugadores no puede ser mayor a 9, porque no hay mas símbolos!");
            generateAutomatic();
        }

        if (snakes > (n * m) / 5 || ladders > (n * m) / 5) {
            System.out.println(
                    "La cantidad de serpientes o escaleras no pueden sobrepasar el 40% de la cantidad de casillas del juego");
            generateAutomatic();
        } else {
            createWorldTwo(parts);
        }
        assignAutomatic(Integer.parseInt(parts[4]), 0);
        System.out.println(world);
        initializeGame(false);
    }

    public void createWorldOne(String[] parameters) {
        world = new World(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]),
                Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3]));
    }

    public void createWorldTwo(String[] parameters) {
        world = new World(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]),
                Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3]), Integer.parseInt(parameters[4]));

    }

    public void assignManually(String parameters, int contador) {
        if (contador < parameters.length()) {
            world.addPlayer(parameters.charAt(contador));
            assignManually(parameters, contador + 1);
        }

    }

    public void gameSimulation() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (!world.getFinished()) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            world.generateDice();
                            System.out.println(world);
                        }
                    });
                    gameSimulation();
                }
            }
        }).start();
    }

    public void assignAutomatic(int amount, int contador) {
        if (contador < amount) {
            world.addPlayer(generateRandom(contador + 1));
            assignAutomatic(amount, contador + 1);
        }
    }

    public void initializeGame(boolean render) {
        if (render == false) {
            System.out.println("Por favor ingrese un salto de línea para iniciar");
            String jump = sc.nextLine();
            if (jump.equals("")) {
                System.out.println(world.generateDice());
                world.setVisible(false);
                System.out.println(world);
                initializeGame(world.getFinished());
            } else if (jump.equals("simul")) {
                System.out.println("Usted ha entrado en modo simulación");
                gameSimulation();
            } else if (jump.equals("menu")) {
                System.out.println("Usted ha elegido devolverse al menu principal, gracias por jugar");
                return;
            } else if (jump.equals("num")) {
                world.setVisible(true);
                System.out.println(world);
                initializeGame(render);
            } else {
                System.out.println("El salto de línea debe estar vacío!");
                initializeGame(render);
            }
        } else {
            System.out.println("El jugador " + world.getActual().getSymbol() + " ha ganado con un total de "
                    + world.getActual().getMoves() + " movimientos!");
            calculateWinner();

        }
    }

    public void calculateWinner() {
        System.out.println("Por favor ingrese su nickname: ");
        String nickname = sc.nextLine();
        if (nickname.equals("")) {
            System.out.println("Su nickname no puede estar vacio, por favor, vuelva a intentarlo!");
            calculateWinner();
        } else {
            world.getActual().setNickname(nickname);
            System.out.println("Usted podrá ver su puntaje en la opción (2) del menú, muchas gracias por jugar!");
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
