package ui;

import java.io.IOException;
import java.util.Scanner;
import model.*;

public class Menu {

    Scanner sc = new Scanner(System.in);

    private static final String SPACE = " ";
    private World world;

    // -----------------------------------------------------MENU-METHODS------------------------------------------

    /**
     * Method name: showMenu.
     * Pos: the desired option by the user is successfully chosen.
     */
    public void showMenu() {
        System.out.println("\033[035m___________________________________________BIENVENIDO AL MENÚ DE SERPIENTES Y ESCALERAS___________________________________________\n"+"\033[0m");
        System.out.print("\n¿Qué quieres hacer el día de hoy?" + "\n" + "\n(1) Quiero jugar Snake and Ladders!"+ "\n(2) Deseo ver el tablero de posiciones" + "\n(3) Deseo salir" + "\nEscriba aqui: ");
    }

    /**
     * Method name: createGame.
     * Pre: The option variable must be successfully assigned to operate with the switch sequence of the selection of players.
     * Pos: The desired option by the user is successfully chosen.
     */
    public void createGame() {
        System.out.print("\n¿Cual opcion escojeras?" + "\n" + "\n(1) Quiero escoger los simbolos jugadores"+ "\n(2) Prefiero que se escojan aleatoriamente" + "\nEscriba aqui: ");
        int option = sc.nextInt();
        sc.nextLine();
        switch (option) {
            case 1:
                System.out.print("\n");
                System.out.print("\033[035m___________________________________________JUGADORES MANUALES___________________________________________\n"+"\033[0m");
                chooseManually();
                System.out.print("\n");
                break;
            case 2:
                System.out.print("\n");
                System.out.print("\033[035m___________________________________________JUGADORES ALEATORIOS___________________________________________\n"+"\033[0m");
                generateAutomatic();
                System.out.print("\n");
                break;
        }
    }

    /**
     *Method name: doOperation.
     * Pre: option must be different from null or empty
     * Pos: The method corresponding to the number sent by parameter is executed correctly
     * @param option The operation variable must have the value entered by the user correctly assigned
     */
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
                System.err.println("La opcion ingresada es inválida, por favor elija la opcion que se le pide");
                System.out.print("\n");
                break;
        }
    }

    /**
     *Method name: readOption.
     * Pre: Read the requested number in the game options menu
     * Pos: Stores the read value in an int variable and returns it
     * @return option
     */
    public int readOption() {
        int option = sc.nextInt();
        sc.nextLine();
        return option;
    }

    /**
     *Method name: startProgram.
     * Pre: Calls the showMenu method and reads the option that the user entered by calling the readOption method and successfully stores it in an int variable
     * Pos: Depending on the option that the user entered, the method corresponding to the number is called or an error message is thrown if the option is not in the menu
     */
    public void startProgram() {
        showMenu();
        int option = readOption();
        if (option == 3) {
            System.out.print("\n");
            System.out.print("\033[035m___________________________________________APLICACION CERRADA___________________________________________\n"+"\033[0m");
            System.out.print("\n");
        } else {
            doOperation(option);
            startProgram();
        }
    }

    // ------------------------------------------------------MANUALLY-PLAYERS--------------------------------------

    /**
     *Method name: chooseManually.
     * Pre: If the user decides to choose the option of choosing the participants manually, it is asked for the corresponding parameters in a messageCalls the showMenu method and reads the option that the user entered by calling the readOption method and successfully stores it in an int variable
     * Pos: The game board is immediately generated with the parameters that the user previously entered if not, a message is sent as to why the board was not generated
     */
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
            System.out.println("*********************TABLERO GENERADO POR LOS PARAMETROS ANTERIORMENTE PEDIDOS*********************");
            createWorldManually(parts);
        }
        assignManually(parts[4], 0);
        System.out.println(world);
        initializeGame(false);
    }

    /**
     *Method name: assignManually.
     * Pre: The participants must be added in an array of Strings and the counter must be started at 0 before passing to the assignManually method as a parameters; parameters and count must be different from null or empty
     * Pos: Symbols are successfully assigned to players
     * @param parameters array where the players are
     * @param counter counter that manages the number of players that the game will have
     */
    public void assignManually(String parameters, int counter) {
        if (counter < parameters.length()) {
            world.addPlayer(parameters.charAt(counter));
            assignManually(parameters, counter + 1);
        }
    }

    /**
     *Method name: createWorldManually.
     * Pre: Each parameter requested at the beginning of the chooseManually method is stored in a Strings position of the parameters array except the participants; parameters must be different from null or empty
     * Pos: The current game match is successfully created
     * @param parameters is located the parameters that were requested to the user at startup
     */
    public void createWorldManually(String[] parameters) {
        world = new World(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]),Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3]));

        try {
            world.loadData();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ---------------------------------------------AUTOMATIC-PLAYERS-----------------------------------------

    /**
     *Method name: generateAutomatic.
     * Pre: If the user decides to choose the option of choosing the participants automatic, it is asked for the corresponding parameters in a messageCalls the showMenu method and reads the option that the user entered by calling the readOption method and successfully stores it in an int variable
     * Pos: The game board is immediately generated with the parameters that the user previously entered if not, a message is sent as to why the board was not generated
     */
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
            System.out.println("*********************TABLERO GENERADO POR LOS PARAMETROS ANTERIORMENTE PEDIDOS*********************");
            createWorldAutomatic(parts);
        }
        assignAutomatic(Integer.parseInt(parts[4]), 0);
        System.out.println(world);
        initializeGame(false);
    }

    /**
     *Method name: assignAutomatic.
     * Pre: The amount participants must be added in an int variable and the counter must be started at 0 before passing to the assignAutomatic method as a parameters; amount and count must be different from null or empty
     * Pos: Symbols are successfully assigned to players
     * @param amount maximum number of participants that the game will have
     * @param counter counter that manages the number of players that the game will have
     */
    public void assignAutomatic(int amount, int counter) {
        if (counter < amount) {
            world.addPlayer(generateRandom(counter + 1));
            assignAutomatic(amount, counter + 1);
        }
    }

    /**
     *Method name: generateRandom.
     * Pre: The option variable is assigned the value to choose the symbol; option must be different from null or empty
     * Pos: The symbol is chosen successfully according to the value sent by parameter, the value is returned
     * @param option The option is the number of the symbol that the player will play when choosing the option to randomly generate participants at the start of the program
     * @return option
     */
    public char generateRandom(int option) {
        char car = ' ';
        switch (option) {
            case 1:
                car = '♕';
                break;
            case 2:
                car = '☠';
                break;
            case 3:
                car = '☺';
                break;
            case 4:
                car = '❤';
                break;
            case 5:
                car = '✚';
                break;
            case 6:
                car = '♘';
                break;
            case 7:
                car = '✹';
                break;
            case 8:
                car = '☯';
                break;
            case 9:
                car = '✪';
                break;
        }
        return car;
    }

    /**
     *Method name: createWorldAutomatic.
     * Pre: Each parameter requested at the beginning of the chooseManually method is stored in a Strings position of the parameters array except the participants; parameters must be different from null or empty
     * Pos: The current game match is successfully created
     * @param parameters is located the parameters that were requested to the user at startup
     */
    public void createWorldAutomatic(String[] parameters) {
        world = new World(Integer.parseInt(parameters[0]), Integer.parseInt(parameters[1]),Integer.parseInt(parameters[2]), Integer.parseInt(parameters[3]), Integer.parseInt(parameters[4]));
        try {
            world.loadData();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // ----------------------------------GAME-SIMULATION-------------------------------------------------------

    /**
     *Method name: gameSimulation.
     * Pos: The simulation of the current game board is generated step by step until reaching the end and showing the winner
     */
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

    /**
     *Method name: initializeGame.
     * Pre: The render boolean variable must be false to start the board game
     * Pos: 4 results can be displayed depending on the type of user (simul, menu, enum or another different writing): simulation of the game, return to the start menu, see the numbered board or an error message because it does not correspond to any of the above . When the render variable becomes true it means that there is a winner and it is shown
     * @param render It is the option that shows or not the listed board
     */
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
                System.out.println("*********************USTED ESTA EN UNA SIMULACION DEL JUEGO ACTUAL*********************");
                System.out.println("\n");
                gameSimulation();
            } else if (jump.equalsIgnoreCase("menu")) {
                System.out.println("Usted ha elegido devolverse al menu principal, gracias por jugar");
            } else if (jump.equalsIgnoreCase("num")) {
                System.out.print("\n");
                System.out.println("*********************TABLERO ENUMERADO*********************");
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

    /**
     *Method name: showWinner.
     * Pos: A message is displayed with the winner of the current game
     */
    public void showWinner() {
        System.out.println("\033[032m¡EL JUGADOR " + world.getActual().getSymbol() + " HA GANADO CON UN TOTAL DE "+ world.getActual().getMoves() + " MOVIMIENTOS!"+"\033[0m");
        calculateWinner();
    }

    /**
     *Method name: calculateWinner.
     * Pre: It asked to the user in the calculateWinner method to enter the winner's nickname and this is assigned to the nickname String variable. nickname must be different from null or empty
     * Pos: The winner is successfully saved in the database if not, a message is displayed that it could have happened.
     */
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
             System.out.println("\033[032mGanador guardado exitosamente\n"+"\033[0m");
            System.out.println("*********************USTED PODRÁ VER SU PUNTAJE EN LA OPCIÓN (2) DEL MENÚ DE INICIO*********************");
        }
    }

    /**
     *Method name: showLeaderBoard.
     * Pos: The ranking of the winners of different games is shown
     */
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
        System.out.print("\033[035m___________________________________________PUNTAJES DE JUGADORES___________________________________________\n"+"\033[0m");
        System.out.print("\n");
        System.out.println("Nombre del jugador " + " Puntaje del jugador");
        w.printWinners();
        System.out.println(w.getMessage());
        w.setMessage("");
    }
}
