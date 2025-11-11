package me.josh.tictactoe.controller;

import me.josh.tictactoe.model.*;
import me.josh.tictactoe.model.player.ComputerPlayer;
import me.josh.tictactoe.model.player.Player;
import me.josh.tictactoe.view.GameView;

import javax.swing.*;

/**
 * Controller that mediates between the game model and view.
 * Handles user input, updates the model, and refreshes the view.
 * Implements GameObserver to receive notifications of game events.
 */
public class GameController implements GameObserver {

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

        // Register as observer
        game.observers.add(this);

        initializeListeners();
        // Set initial status
        updateStatusMessage();
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
        game.makeMove(row, col);

        // If game isn't over, and it's computer's turn, make computer move
        if (!game.gameOver && game.isCurrentPlayerComputer()) {
            // Small delay so user can see their move before computer responds
            Timer timer = new Timer(500, e -> makeComputerMove());
            timer.setRepeats(false);
            timer.start();
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
    }

    /**
     * Updates the status message based on current game state.
     */
    private void updateStatusMessage() {
        if (game.gameOver) {
            if (game.winner != null) {
                view.statusPanel.setStatus(game.winner.name + " wins!");
            } else if (game.isDraw()) {
                view.statusPanel.setStatus("It's a draw!");
            }
        } else {
            Player current = game.getCurrentPlayer();
            view.statusPanel.setStatus(current.name + "'s turn (" + current.symbol + ")");
        }
    }

    @Override
    public void onMoveMade(int row, int col, Symbol symbol) {
        // Update the cell in the view
        view.boardPanel.updateCell(row, col, symbol);
    }

    @Override
    public void onGameOver(Player winner) {
        // Disable all buttons
        view.boardPanel.disableAll();
        updateStatusMessage();
    }

    @Override
    public void onTurnChanged(Player currentPlayer) {
        updateStatusMessage();
    }

    @Override
    public void onGameReset() {
        view.boardPanel.reset();
        updateStatusMessage();
    }

}