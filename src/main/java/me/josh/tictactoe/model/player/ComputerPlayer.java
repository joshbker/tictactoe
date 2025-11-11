package me.josh.tictactoe.model.player;

import me.josh.tictactoe.model.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Represents a computer player that automatically selects moves using an AI algorithm.
 * Uses a strategic approach: win if possible, block opponent, prefer center, then corners, then sides.
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
     * Chooses a move using strategic AI logic.
     *
     * @param board The current game board state
     * @return An array [row, col] for the chosen move, or null if no moves available
     */
    @Override
    public int[] getMove(Symbol[][] board) {
        // 1. Try to win
        int[] winMove = findWinningMove(board, this.symbol);
        if (winMove != null) return winMove;

        // 2. Block opponent from winning
        Symbol opponentSymbol = this.symbol.opposite();
        int[] blockMove = findWinningMove(board, opponentSymbol);
        if (blockMove != null) return blockMove;

        // 3. Take center if available
        if (board[1][1] == Symbol.EMPTY) return new int[]{1, 1};

        // 4. Take a corner if available
        int[] cornerMove = findCornerMove(board);
        if (cornerMove != null) return cornerMove;

        // 5. Take any side
        return findSideMove(board);
        // No moves available
    }

    /**
     * Finds a move that would complete three in a row for the given symbol.
     *
     * @param board The current board state
     * @param symbol The symbol to find a winning move for
     * @return The winning move [row, col], or null if none exists
     */
    private int[] findWinningMove(Symbol[][] board, Symbol symbol) {
        // Check all rows
        for (int row = 0; row < 3; row++) {
            int[] move = checkLine(board, symbol, new int[][]{{row, 0}, {row, 1}, {row, 2}});
            if (move != null) return move;
        }

        // Check all columns
        for (int col = 0; col < 3; col++) {
            int[] move = checkLine(board, symbol, new int[][]{{0, col}, {1, col}, {2, col}});
            if (move != null) return move;
        }

        // Check diagonal (top-left to bottom-right)
        int[] move = checkLine(board, symbol, new int[][]{{0, 0}, {1, 1}, {2, 2}});
        if (move != null) return move;

        // Check diagonal (top-right to bottom-left)
        move = checkLine(board, symbol, new int[][]{{0, 2}, {1, 1}, {2, 0}});

        return move;
    }

    /**
     * Checks if a line has two of the given symbol and one empty space.
     * Returns the empty space if found.
     *
     * @param board The current board state
     * @param symbol The symbol to check for
     * @param positions The three positions that form a line
     * @return The empty position [row, col] if this line can win, null otherwise
     */
    private int[] checkLine(Symbol[][] board, Symbol symbol, int[][] positions) {
        int symbolCount = 0;
        int emptyCount = 0;
        int[] emptyPos = null;

        for (int[] pos : positions) {
            int row = pos[0];
            int col = pos[1];

            if (board[row][col] == symbol) {
                symbolCount++;
            } else if (board[row][col] == Symbol.EMPTY) {
                emptyCount++;
                emptyPos = new int[]{row, col};
            }
        }

        // If we have 2 of our symbol and 1 empty, this is a winning/blocking move
        if (symbolCount == 2 && emptyCount == 1) return emptyPos;

        return null;
    }

    /**
     * Finds a random available position from a given list of positions.
     *
     * @param board The current board state
     * @param positions The positions to check
     * @return A random available position [row, col], or null if none available
     */
    private int[] findAvailablePosition(Symbol[][] board, int[][] positions) {
        List<int[]> availablePositions = new ArrayList<>();

        for (int[] pos : positions) {
            if (board[pos[0]][pos[1]] == Symbol.EMPTY) {
                availablePositions.add(pos);
            }
        }

        if (availablePositions.isEmpty()) {
            return null;
        }

        // Return a random available position
        int randomIndex = ThreadLocalRandom.current().nextInt(availablePositions.size());
        return availablePositions.get(randomIndex);
    }

    /**
     * Finds an available corner position.
     *
     * @param board The current board state
     * @return A random available corner [row, col], or null if none available
     */
    private int[] findCornerMove(Symbol[][] board) {
        int[][] cornerPositions = {{0, 0}, {0, 2}, {2, 0}, {2, 2}};
        return findAvailablePosition(board, cornerPositions);
    }

    /**
     * Finds an available side position (not corner or center).
     *
     * @param board The current board state
     * @return A random available side [row, col], or null if none available
     */
    private int[] findSideMove(Symbol[][] board) {
        int[][] sidePositions = {{0, 1}, {1, 0}, {1, 2}, {2, 1}};
        return findAvailablePosition(board, sidePositions);
    }

}