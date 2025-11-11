package me.josh.tictactoe.view;

import javax.swing.*;
import java.awt.*;

/**
 * Main game window containing the board and status panels.
 * Sets up the overall layout and provides access to UI components.
 */
public class GameView extends JFrame {

    /** Panel containing the game board */
    public final BoardPanel boardPanel;

    /** Panel showing game status and controls */
    public final StatusPanel statusPanel;

    /**
     * Creates the main game window.
     */
    public GameView() {
        setTitle("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setResizable(false);

        // Create panels
        boardPanel = new BoardPanel();
        statusPanel = new StatusPanel();

        // Set up layout using GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Add status panel at top
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(statusPanel, gbc);

        // Add board panel in center
        gbc.gridy = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(boardPanel, gbc);

        // Center window on screen
        setLocationRelativeTo(null);
    }

}