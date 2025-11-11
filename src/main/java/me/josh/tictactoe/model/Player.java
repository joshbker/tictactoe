package me.josh.tictactoe.model;

/**
 * Abstract class representing a player in Tic Tac Toe.
 * Contains common attributes shared by all player types.
 * Subclasses must implement how they choose their moves.
 */
public abstract class Player {

    /** The symbol this player uses on the board (X or O) */
    public final Symbol symbol;

    /** The display name of this player */
    public final String name;

    /**
     * Creates a new player with the given symbol and name.
     *
     * @param symbol The symbol this player will use (X or O)
     * @param name The name to display for this player
     */
    public Player(Symbol symbol, String name) {
        this.symbol = symbol;
        this.name = name;
    }

    /**
     * Determines this player's next move based on the current board state.
     * Subclasses implement their own strategy (human input, AI algorithm, etc.).
     *
     * @param board The current state of the game board
     * @return An array [row, col] indicating the chosen position, or null if no valid move
     */
    public abstract int[] getMove(Symbol[][] board);

    /**
     * Returns a string representation of this player.
     *
     * @return The player's name and symbol, e.g. "Alice (X)"
     */
    @Override
    public String toString() {
        return name + " (" + symbol + ")";
    }

}