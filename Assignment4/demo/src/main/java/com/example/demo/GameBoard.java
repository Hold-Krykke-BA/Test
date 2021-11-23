package com.example.demo;

import java.util.*;

public class GameBoard {
    private char[][] board;
    private static List<List> winningPositions = new ArrayList<>();
    private static List<Integer> playerPositions = new ArrayList<>(), computerPositions = new ArrayList<>();
    private static Random random = new Random();
    private static final int LOWER_LIMIT = 1, UPPER_LIMIT = 9;

    enum USERS {COMPUTER, PLAYER}

    public GameBoard() {
        //The 2d board array consists of the visualization of the board. When players chose tiles 1-9 we assign them to the proper indices only.
        //Players should not be able to assign to indices containing spacers such as |, -, +.
        //Row, Column
        this.board = new char[][]{
                {' ', '|', ' ', '|', ' '}, // [0][0], [0][2], [0][4]
                {'-', '+', '-', '+', '-'}, // Nothing here
                {' ', '|', ' ', '|', ' '}, // [2][0], [2][2], [2][4]
                {'-', '+', '-', '+', '-'}, // Nothing here
                {' ', '|', ' ', '|', ' '}  // [4][0], [4][2], [4][4]
        };
        //Left to right <>
        List topRow = Arrays.asList(1, 2, 3);
        List middleRow = Arrays.asList(4, 5, 6);
        List bottomRow = Arrays.asList(7, 8, 9);
        //Top to bottom <>
        List leftColumn = Arrays.asList(1, 4, 7);
        List middleColumn = Arrays.asList(2, 5, 8);
        List rightColumn = Arrays.asList(3, 6, 9);
        //Diagonally <>
        List leftDiagonal = Arrays.asList(1, 5, 9);
        List rightDiagonal = Arrays.asList(3, 5, 7);

        winningPositions.add(topRow);
        winningPositions.add(middleRow);
        winningPositions.add(bottomRow);
        winningPositions.add(leftColumn);
        winningPositions.add(middleColumn);
        winningPositions.add(rightColumn);
        winningPositions.add(leftDiagonal);
        winningPositions.add(rightDiagonal);
    }

    public static void main(String[] args) {
        System.out.println("Starting a game of Tic Tac Toe. Player plays as 'X', computer as 'O'.");
        GameBoard ttt = new GameBoard();
        ttt.playGame();
    }

    //Play game "main" with gameStatus() check
    private void playGame() {
        boolean playerStart = random.nextBoolean();
        System.out.println("Picking starting position at random. " + (playerStart ? "Player starts" : "Computer starts"));

        while (true) { //Runs until System.exit in gameStatus();
            if (playerStart) {
                playerTurn(playerPositions, computerPositions);
                computerTurn(playerPositions, computerPositions);
            } else {
                computerTurn(playerPositions, computerPositions);
                playerTurn(playerPositions, computerPositions);
            }
        }
    }


    private void printGameBoard() {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }


    /**
     * Let the player take a turn. Optionally supply a position manually or supply one using the input scanner.
     *
     * @param playerPositions
     * @param computerPositions
     * @param position
     */
    public void playerTurn(List<Integer> playerPositions, List<Integer> computerPositions, int... position) {
        Scanner in = new Scanner(System.in);
        int playerPosition = position.length > 0 ? position[0] : -1; //Use method supplied position or start out of bounds.

        //Get position until valid (Not taken and between limits)
        while (playerPositions.contains(playerPosition) ||
                computerPositions.contains(playerPosition) ||
                !(playerPosition >= LOWER_LIMIT && playerPosition <= UPPER_LIMIT)) {
            try {
                System.out.print("Please enter a valid position (1-9): ");
                playerPosition = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Input not allowed, try again.");
                in.next();
            }
        }
        System.out.println(String.format("--------------\nPlayer picked %d\n--------------", playerPosition));

        //Place position
        placePosition(playerPosition, USERS.PLAYER, this.board);

        //Check status and print board
        gameStatus();
    }

    /**
     * Let the computer take its turn. Optionally supply a position manually or the program will find one.
     *
     * @param playerPositions   List of playerPositions
     * @param computerPositions List of computerPositions
     * @param position          optional position to use. If invalid, the program will find another one.
     */
    public void computerTurn(List<Integer> playerPositions, List<Integer> computerPositions, int... position) {
        int computerPosition = position.length > 0 ? position[0] : random.nextInt(9) + 1; //Use supplied optional position or get value between 1 and 9
        while (playerPositions.contains(computerPosition) || computerPositions.contains(computerPosition)) {
            computerPosition = random.nextInt(9) + 1; //redraw value until valid
        }
        System.out.println(String.format("Computer picked %d", computerPosition));

        //Place position
        placePosition(computerPosition, USERS.COMPUTER, this.board);

        //Check status and print board
        gameStatus();
    }

    //Generic "placement" method
    //base on enum users
    //Switch and swap on pos to match gameboard indices (0-9 not equal to indices)

    /**
     * Generic placement method based on userType. Places a position between 1-9 on the board at the correct indices as limited by the game rules.
     *
     * @param pos the position to place on
     * @param userType the @userType to use {@link USERS}
     * @param board the board to place the position on.
     */
    public void placePosition(int pos, USERS userType, char[][] board) {
        char symbol;

        if (userType == USERS.PLAYER) {
            symbol = 'X';
            playerPositions.add(pos);
        } else {
            symbol = 'O';
            computerPositions.add(pos);
        }

        switch (pos) {
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;
                break;
            case 5:
                board[2][2] = symbol;
                break;
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[4][0] = symbol;
                break;
            case 8:
                board[4][2] = symbol;
                break;
            case 9:
                board[4][4] = symbol;
                break;
            default: //Should never hit here as we validate before
                System.out.println("Please Enter a valid position");
                break;
        }
    }

    //Win check
    //1: player wins
    //2: computer wins
    //0: tie
    //-1: unfinished
    private void gameStatus() {
        printGameBoard();
        for (List position : winningPositions) {
            if (playerPositions.containsAll(position)) {
                System.out.println("Game over! Player wins.");
                System.exit(0);
            } else if (computerPositions.containsAll(position)) {
                System.out.println("Game over! Computer wins.");
                System.exit(0);
            }
        }
        if (playerPositions.size() + computerPositions.size() == 9) {
            System.out.println("Game over! It's a tie.");
            System.exit(0);
        }
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public List<List> getWinningPositions() {
        return winningPositions;
    }

    public static void setWinningPositions(List<List> winningPositions) {
        GameBoard.winningPositions = winningPositions;
    }

    public List<Integer> getPlayerPositions() {
        return playerPositions;
    }

    public static void setPlayerPositions(List<Integer> playerPositions) {
        GameBoard.playerPositions = playerPositions;
    }

    public List<Integer> getComputerPositions() {
        return computerPositions;
    }

    public static void setComputerPositions(List<Integer> computerPositions) {
        GameBoard.computerPositions = computerPositions;
    }
}
