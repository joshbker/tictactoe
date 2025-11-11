package me.josh.tictactoe.model.player;

import me.josh.tictactoe.model.Symbol;

/**
 * Represents a human player who makes moves through the GUI.
 * Move selection is handled by the controller responding to user input,
 * so getMove() is not typically used for human players.
 */
public class HumanPlayer extends Player {

    /**
     * Creates a new human player.
     *
     * @param symbol The symbol this player uses (X or O)
     * @param name The player's name
     */
    public HumanPlayer(Symbol symbol, String name) {
        super(symbol, name);
    }

    /**
     * Human players don't autonomously choose moves - they respond to GUI input.
     * This method returns null as moves are provided through the controller.
     *
     * @param board The current game board state
     * @return null (human moves are handled by controller)
     */
    @Override
    public int[] getMove(Symbol[][] board) {
        return null;
    }

}