package me.josh.tictactoe.view;

import javax.swing.*;
import java.awt.*;

/**
 * Panel displaying game status information and controls.
 * Shows whose turn it is, game results, and provides a reset button.
 */
public class StatusPanel extends JPanel {

    /** Label showing current game status */
    private final JLabel statusLabel;

    /** Button to start a new game */
    public final JButton newGameButton;

    /**
     * Creates a new status panel.
     */
    public StatusPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        statusLabel = new JLabel("Game starts!");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));

        newGameButton = new JButton("New Game");

        add(statusLabel);
        add(newGameButton);
    }

    /**
     * Updates the status label with a message.
     *
     * @param message The message to display
     */
    public void setStatus(String message) {
        statusLabel.setText(message);
    }

}