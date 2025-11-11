package me.josh.tictactoe.model.player;

import me.josh.tictactoe.model.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents a computer player that automatically selects moves using an AI algorithm.
 * Currently uses a random move selection strategy.
 */
public class ComputerPlayer extends Player {

    /**
     * Creates a new computer player.
     *
     * @param symbol The symbol this player uses (X or O)
     */
    public ComputerPlayer(Symbol symbol) {
        super(symbol, "Computer");
    }

    /**
     * Chooses a move by randomly selecting from available empty cells.
     *
     * @param board The current game board state
     * @return An array [row, col] for the chosen move, or null if no moves available
     */
    @Override
    public int[] getMove(Symbol[][] board) { // TODO: Make a smart algorithm for this rather than just random
        // Find all empty cells
        List<int[]> availableMoves = new ArrayList<>();

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                if (board[row][col] == Symbol.EMPTY) {
                    availableMoves.add(new int[]{row, col});
                }
            }
        }

        // Return null if no moves available
        if (availableMoves.isEmpty()) {
            return null;
        }

        // Pick a random available move
        int randomIndex = ThreadLocalRandom.current().nextInt(availableMoves.size());
        return availableMoves.get(randomIndex);
    }

}