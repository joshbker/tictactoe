package me.josh.tictactoe.model.player;

import me.josh.tictactoe.model.Symbol;

/**
 * Factory for creating different types of players.
 * Encapsulates the logic of player instantiation.
 */
public class PlayerFactory {

    /**
     * Creates a player of the specified type.
     *
     * @param type The type of player to create
     * @param symbol The symbol this player will use (X or O)
     * @param name The player's name (used for human players, ignored for computer)
     * @return A new player instance
     */
    public static Player createPlayer(PlayerType type, Symbol symbol, String name) {
        return switch (type) {
            case HUMAN -> new HumanPlayer(symbol, name);
            case COMPUTER -> new ComputerPlayer(symbol);
        };
    }

    /**
     * Creates a human player with the given name.
     *
     * @param symbol The symbol this player will use
     * @param name The player's name
     * @return A new HumanPlayer
     */
    public static Player createHumanPlayer(Symbol symbol, String name) {
        return new HumanPlayer(symbol, name);
    }

    /**
     * Creates a computer player.
     *
     * @param symbol The symbol this player will use
     * @return A new ComputerPlayer
     */
    public static Player createComputerPlayer(Symbol symbol) {
        return new ComputerPlayer(symbol);
    }

}