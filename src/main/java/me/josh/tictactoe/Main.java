package me.josh.tictactoe;

import me.josh.tictactoe.controller.GameController;
import me.josh.tictactoe.model.*;
import me.josh.tictactoe.model.player.*;
import me.josh.tictactoe.view.GameView;

import javax.swing.*;

/**
 * Entry point for the Tic Tac Toe application.
 * Creates and wires together the Model, View, and Controller.
 */
public final class Main {

    public static void main(String[] args) {
        // Run on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> {
            // Create players
            Player human = PlayerFactory.createPlayer(PlayerType.HUMAN, Symbol.X, "Player");
            Player computer = PlayerFactory.createPlayer(PlayerType.COMPUTER, Symbol.O, null);

            // Create model
            TicTacToeGame game = new TicTacToeGame(human, computer);

            // Create view
            GameView view = new GameView();

            // Create controller (wires model and view together)
            new GameController(game, view);

            // Show the window
            view.setVisible(true);
        });
    }

}
