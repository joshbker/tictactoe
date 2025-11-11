package me.josh.tictactoe.view;

import me.josh.tictactoe.model.Symbol;

import javax.swing.*;
import java.awt.*;

/**
 * Panel displaying the 3x3 Tic Tac Toe game board.
 * Contains 9 buttons arranged in a grid layout.
 */
public class BoardPanel extends JPanel {

    /** The 9 buttons representing the board cells */
    private final JButton[][] buttons;

    /**
     * Creates a new board panel with a 3x3 grid of buttons.
     */
    public BoardPanel() {
        setLayout(new GridLayout(3, 3, 5, 5)); // 3x3 grid with 5px gaps

        buttons = new JButton[3][3];

        // Create all 9 buttons
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JButton button = new JButton();
                button.setFont(new Font("Arial", Font.BOLD, 48));
                button.setFocusPainted(false);

                buttons[row][col] = button;
                add(button);
            }
        }
    }

    /**
     * Gets the button at a specific position.
     *
     * @param row The row (0-2)
     * @param col The column (0-2)
     * @return The button at that position
     */
    public JButton getButton(int row, int col) {
        return buttons[row][col];
    }

    /**
     * Updates a cell to display the given symbol.
     *
     * @param row The row (0-2)
     * @param col The column (0-2)
     * @param symbol The symbol to display
     */
    public void updateCell(int row, int col, Symbol symbol) {
        buttons[row][col].setText(symbol.toString());
        buttons[row][col].setEnabled(false);
    }

    /**
     * Clears all cells and re-enables all buttons.
     */
    public void reset() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setEnabled(true);
            }
        }
    }

    /**
     * Disables all buttons (used when game is over).
     */
    public void disableAll() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setEnabled(false);
            }
        }
    }

}