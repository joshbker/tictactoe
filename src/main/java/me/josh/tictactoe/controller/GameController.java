package me.josh.tictactoe.controller;

import me.josh.tictactoe.model.*;
import me.josh.tictactoe.model.player.ComputerPlayer;
import me.josh.tictactoe.model.player.Player;
import me.josh.tictactoe.view.GameView;

import javax.swing.*;

/**
 * Controller that mediates between the game model and view.
 * Handles user input, updates the model, and refreshes the view.
 */
public class GameController {

    private final TicTacToeGame game;
    private final GameView view;

    /**
     * Creates a new game controller.
     *
     * @param game The game model
     * @param view The game view
     */
    public GameController(TicTacToeGame game, GameView view) {
        this.game = game;
        this.view = view;

        initializeListeners();
        updateView();
    }

    /**
     * Sets up all event listeners for the view components.
     */
    private void initializeListeners() {
        // Add listeners to all board buttons
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                final int r = row;
                final int c = col;

                JButton button = view.boardPanel.getButton(r, c);
                button.addActionListener(e -> handleCellClick(r, c));
            }
        }

        // Add listener to new game button
        view.statusPanel.newGameButton.addActionListener(e -> handleNewGame());
    }

    /**
     * Handles a click on a board cell.
     *
     * @param row The row that was clicked
     * @param col The column that was clicked
     */
    private void handleCellClick(int row, int col) {
        // Only process if it's a human player's turn
        if (!game.isCurrentPlayerHuman()) {
            return;
        }

        // Attempt to make the move
        if (game.makeMove(row, col)) {
            updateView();

            // If game isn't over, and it's computer's turn, make computer move
            if (!game.gameOver && game.isCurrentPlayerComputer()) {
                // Small delay so user can see their move before computer responds
                Timer timer = new Timer(500, e -> makeComputerMove());
                timer.setRepeats(false);
                timer.start();
            }
        }
    }

    /**
     * Makes the computer player's move.
     */
    private void makeComputerMove() {
        ComputerPlayer computer = (ComputerPlayer) game.getCurrentPlayer();
        int[] move = computer.getMove(getBoard());

        if (move != null) {
            game.makeMove(move[0], move[1]);
            updateView();
        }
    }

    /**
     * Gets a copy of the current board state for the computer to analyze.
     *
     * @return A 2D array representing the board
     */
    private Symbol[][] getBoard() {
        Symbol[][] board = new Symbol[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = game.getSymbolAt(row, col);
            }
        }
        return board;
    }

    /**
     * Handles the new game button click.
     */
    private void handleNewGame() {
        game.reset();
        view.boardPanel.reset();
        updateView();
    }

    /**
     * Updates the view to reflect the current game state.
     */
    private void updateView() {
        // Update all cells
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Symbol symbol = game.getSymbolAt(row, col);
                if (symbol != Symbol.EMPTY) {
                    view.boardPanel.updateCell(row, col, symbol);
                }
            }
        }

        // Update status message
        if (game.gameOver) {
            if (game.winner != null) {
                view.statusPanel.setStatus(game.winner.name + " wins!");
                view.boardPanel.disableAll();
            } else if (game.isDraw()) {
                view.statusPanel.setStatus("It's a draw!");
                view.boardPanel.disableAll();
            }
        } else {
            Player current = game.getCurrentPlayer();
            view.statusPanel.setStatus(current.name + "'s turn (" + current.symbol + ")");
        }
    }

}