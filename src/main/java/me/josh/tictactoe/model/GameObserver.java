package me.josh.tictactoe.model;

import me.josh.tictactoe.model.player.Player;

/**
 * Observer interface for game events.
 * Implements the Observer pattern to decouple model from view/controller.
 */
public interface GameObserver {

    /**
     * Called when a move is made on the board.
     *
     * @param row The row where the move was made
     * @param col The column where the move was made
     * @param symbol The symbol that was placed
     */
    void onMoveMade(int row, int col, Symbol symbol);

    /**
     * Called when the game ends.
     *
     * @param winner The winning player, or null if it's a draw
     */
    void onGameOver(Player winner);

    /**
     * Called when the turn switches to another player.
     *
     * @param currentPlayer The player whose turn it now is
     */
    void onTurnChanged(Player currentPlayer);

    /**
     * Called when the game is reset.
     */
    void onGameReset();

}