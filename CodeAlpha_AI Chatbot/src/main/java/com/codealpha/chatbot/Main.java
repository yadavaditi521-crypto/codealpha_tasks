package com.codealpha.chatbot;

import com.codealpha.chatbot.gui.ChatbotFrame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Entry point for the codealpha_AI chatbot application.
 */
public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
                // Fall back to default look and feel.
            }
            new ChatbotFrame().setVisible(true);
        });
    }
}
