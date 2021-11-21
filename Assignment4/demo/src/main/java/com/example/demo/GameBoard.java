package com.example.demo;

import java.util.*;

public class GameBoard {
    private char[][] board;
    private static List<List> winningPositions = new ArrayList<>();
    private static List<Integer> playerPositions = new ArrayList<>(), computerPositions = new ArrayList<>();
    private static Random random = new Random();
    private static final int LOWER_LIMIT = 1, UPPER_LIMIT = 9;

    //private static int moves; //instead use {player}Positions.size()
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
        System.out.println("Starting game..");
        GameBoard ttt = new GameBoard();
        ttt.playGame();
    }

    //todo
    //Play game "main" with gameStatus() check
    private void playGame() {
        //pick first turn
        //while !notFinished -> swap turns
    }


    private void printGameBoard() {
        for (char[] row : board) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }


    //Player turn using scanner
    private void playerTurn() {
        Scanner in = new Scanner(System.in);
        int playerPosition = -1;

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
        System.out.println(String.format("Player picked %d", playerPosition));

        //Place position
        placePosition(playerPosition, USERS.PLAYER);

        //Print board
        printGameBoard();
    }

    //Computers turn using random
    private void computerTurn() {
        int computerPosition = random.nextInt(9) + 1; //Get value between 1 and 9
        while (playerPositions.contains(computerPosition) || computerPositions.contains(computerPosition)) {
            computerPosition = random.nextInt(9) + 1; //redraw value until valid
        }
        System.out.println(String.format("Computer picked %d", computerPosition));

        //Place position
        placePosition(computerPosition, USERS.COMPUTER);

        //Print board
        printGameBoard();
    }

    //Generic "placement" method
    //todo select symbol
    //base on enum users
    //Switch and swap on pos to match gameboard indices (0-9 not equal to indices)
    private void placePosition(int pos, USERS userType) {

    }

    //todo
    //Win check
    //1: player wins
    //2: computer wins
    //0: tie
    //-1: unfinished
    private int gameStatus() {
        return -1;
    }

}
