package com.example.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameBoardTest {

    private static GameBoard board;

    @BeforeEach
    void setUp() {
        board = new GameBoard();
    }

    @AfterEach
    void tearDown() {
    }

    @BeforeAll
    static void beforeAll() {

    }

    @Test
    void testPlayerTurnCorrectPosition() {
        //Arrange
        boolean expectedValue = true; //Fresh game, no changes have been made
        int testPosition = 5; //Position should be available

        //Act
        boolean result = board.playerTurn(board.getPlayerPositions(), board.getComputerPositions(), testPosition);

        //Assert
        assertEquals(expectedValue, result);
    }

    @Test
    void testPlayerTurnAlreadyTakenPositionFail() {
        //Arrange
        int testPosition = 5; //Position should be available
        board.playerTurn(board.getPlayerPositions(), board.getComputerPositions(), testPosition); //Play on position 5
        boolean expectedValue = false; //Position 5 has already been played
//        board[0][0] = 'X';

        //Act
        boolean result = board.playerTurn(board.getPlayerPositions(), board.getComputerPositions(), testPosition);

        //Assert
        assertEquals(expectedValue, result);
    }

    @Test
    void testPlayerTurnOutOfBoundsPositionMaxFail() {
        //Arrange
        int testPosition = 10; //Position out of bounds
        boolean expectedValue = false; //Position 10 is out of bounds
//        board[0][0] = 'X';

        //Act
        boolean result = board.playerTurn(board.getPlayerPositions(), board.getComputerPositions(), testPosition);

        //Assert
        assertEquals(expectedValue, result);
    }

    @Test
    void testPlayerTurnOutOfBoundsPositionMinFail() {
        //Arrange
        int testPosition = 0; //Position out of bounds
        boolean expectedValue = false; //Position 10 is out of bounds
//        board[0][0] = 'X';

        //Act
        boolean result = board.playerTurn(board.getPlayerPositions(), board.getComputerPositions(), testPosition);

        //Assert
        assertEquals(expectedValue, result);
    }

    @Test
    void testComputerTurnCorrectPosition() {
        //Arrange
        int testPosition = 3; //Fresh game, position should be available
        boolean expectedValue = true;

        //Act
        boolean result = board.computerTurn(board.getPlayerPositions(), board.getComputerPositions(), testPosition);

        //Assert
        assertEquals(expectedValue, result);
    }

    @Test
    void testComputerTurnAlreadyTakenPosition() {
        //Arrange
        int testPosition = 3;
        board.computerTurn(board.getPlayerPositions(), board.getComputerPositions(), testPosition);
        boolean expectedValue = true;
        //Already played on position, but computer should be able to find another position of the 8 available


        //Act
        boolean result = board.computerTurn(board.getPlayerPositions(), board.getComputerPositions(), testPosition);


        //Assert
        assertEquals(expectedValue, result);
    }

    @Test
    void testPlacePositionPlayerCorrectPosition() {
        //Arrange
        int testPosition = 5; //Within limit of 1-9
        boolean expectedValue = true;
        int playerPositionsSizeBefore = board.getPlayerPositions().size(); //0

        //Act
        boolean result = board.placePosition(testPosition, GameBoard.USERS.PLAYER, board.getBoard());

        //Assert
        assertEquals(expectedValue, result);
        assertEquals(playerPositionsSizeBefore, 0); //was 0
        assertEquals(board.getPlayerPositions().size(), 1); //increased by one
    }

    @Test
    void testPlacePositionComputerCorrectPosition() {
        //Arrange
        int testPosition = 5; //Within limit of 1-9
        boolean expectedValue = true;
        int computerPositionsSizeBefore = board.getComputerPositions().size(); //0


        //Act
        boolean result = board.placePosition(testPosition, GameBoard.USERS.COMPUTER, board.getBoard());

        //Assert
        assertEquals(expectedValue, result);
        assertEquals(computerPositionsSizeBefore, 0); //was 0
        assertEquals(board.getComputerPositions().size(), 1); //increased by one
    }

    @Test
    void testPlacePositionComputerOutOfBoundsMinFail() {
        //Arrange
        int testPosition = 0; //Out of bounds (1-9)
        boolean expectedValue = false;

        //Act
        boolean result = board.placePosition(testPosition, GameBoard.USERS.COMPUTER, board.getBoard());

        //Assert
        assertEquals(expectedValue, result);
    }

    @Test
    void testPlacePositionComputerOutOfBoundsMaxFail() {
        //Arrange
        int testPosition = 10; //Out of bounds (1-9)
        boolean expectedValue = false;

        //Act
        boolean result = board.placePosition(testPosition, GameBoard.USERS.COMPUTER, board.getBoard());

        //Assert
        assertEquals(expectedValue, result);
    }

}