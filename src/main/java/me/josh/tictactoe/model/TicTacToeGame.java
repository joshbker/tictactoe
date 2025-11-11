package me.josh.tictactoe.model;

import me.josh.tictactoe.model.player.ComputerPlayer;
import me.josh.tictactoe.model.player.HumanPlayer;
import me.josh.tictactoe.model.player.Player;

/**
 * Core game logic for Tic Tac Toe.
 * Manages the board state, players, turn tracking, and win/draw detection.
 */
public class TicTacToeGame {

    /** The 3x3 game board */
    private final Symbol[][] board;

    /** The first player */
    public final Player player1;

    /** The second player */
    public final Player player2;

    /** The player whose turn it currently is */
    private Player currentPlayer;

    /** Whether the game has ended */
    public boolean gameOver = false;

    /** The winning player, or null if game is not won */
    public Player winner = null;

    /**
     * Creates a new Tic Tac Toe game with the given players.
     * Player 1 goes first.
     *
     * @param player1 The first player (goes first)
     * @param player2 The second player
     */
    public TicTacToeGame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;

        // Initialize empty board
        board = new Symbol[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = Symbol.EMPTY;
            }
        }
    }

    /**
     * Attempts to make a move at the specified position for the current player.
     *
     * @param row The row (0-2)
     * @param col The column (0-2)
     * @return true if the move was valid and made, false otherwise
     */
    public boolean makeMove(int row, int col) {
        // Check if game is already over
        if (gameOver) {
            return false;
        }

        // Validate move
        if (!isValidMove(row, col)) {
            return false;
        }

        // Make the move
        board[row][col] = currentPlayer.symbol;

        // Check for win
        if (checkWin()) {
            gameOver = true;
            winner = currentPlayer;
            return true;
        }

        // Check for draw
        if (isBoardFull()) {
            gameOver = true;
            return true;
        }

        // Switch turns
        switchPlayer();

        return true;
    }

    /**
     * Checks if a move at the given position is valid.
     *
     * @param row The row (0-2)
     * @param col The column (0-2)
     * @return true if the position is empty and in bounds
     */
    public boolean isValidMove(int row, int col) {
        // Check bounds
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }

        // Check if cell is empty
        return board[row][col] == Symbol.EMPTY;
    }

    /**
     * Checks if the current player has won the game.
     *
     * @return true if current player has three in a row
     */
    private boolean checkWin() {
        Symbol s = currentPlayer.symbol;

        // Check rows
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == s && board[row][1] == s && board[row][2] == s) {
                return true;
            }
        }

        // Check columns
        for (int col = 0; col < 3; col++) {
            if (board[0][col] == s && board[1][col] == s && board[2][col] == s) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == s && board[1][1] == s && board[2][2] == s) {
            return true;
        }
        if (board[0][2] == s && board[1][1] == s && board[2][0] == s) {
            return true;
        }

        return false;
    }

    /**
     * Checks if the board is completely filled.
     *
     * @return true if no empty cells remain
     */
    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == Symbol.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Switches the current player to the other player.
     */
    private void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    /**
     * Resets the game to initial state with empty board.
     * Player 1 goes first again.
     */
    public void reset() {
        // Clear board
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = Symbol.EMPTY;
            }
        }

        currentPlayer = player1;
        gameOver = false;
        winner = null;
    }

    /**
     * Gets the symbol at a specific board position.
     *
     * @param row The row (0-2)
     * @param col The column (0-2)
     * @return The symbol at that position
     */
    public Symbol getSymbolAt(int row, int col) {
        return board[row][col];
    }

    /**
     * Gets the current player whose turn it is.
     *
     * @return The current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Checks if the current player is a human player.
     *
     * @return true if current player is human
     */
    public boolean isCurrentPlayerHuman() {
        return currentPlayer instanceof HumanPlayer;
    }

    /**
     * Checks if the current player is a computer player.
     *
     * @return true if current player is computer
     */
    public boolean isCurrentPlayerComputer() {
        return currentPlayer instanceof ComputerPlayer;
    }

    /**
     * Checks if the game ended in a draw.
     *
     * @return true if game is over with no winner
     */
    public boolean isDraw() {
        return gameOver && winner == null;
    }

}