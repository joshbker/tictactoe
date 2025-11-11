package me.josh.tictactoe.model;

/**
 * Enum representing the symbols used in Tic Tac Toe
 */
public enum Symbol {
    X,
    O,
    EMPTY;

    /**
     * Converts symbol to a display string
     * @return "X", "O", or " " for empty cells
     */
    @Override
    public String toString() {
        return switch (this) {
            case X -> "X";
            case O -> "O";
            case EMPTY -> " ";
        };
    }

    /**
     * Gets the opposite symbol (useful for turn switching)
     * @return O if this is X, X if this is O, EMPTY if this is EMPTY
     */
    public Symbol opposite() {
        return switch (this) {
            case X -> O;
            case O -> X;
            default -> EMPTY;
        };
    }

}