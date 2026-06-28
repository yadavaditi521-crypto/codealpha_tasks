package com.codealpha.chatbot.gui;

import com.codealpha.chatbot.engine.ChatbotEngine;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Swing GUI for real-time interaction with the codealpha_AI chatbot.
 */
public final class ChatbotFrame extends JFrame {

    private static final Color USER_COLOR = new Color(37, 99, 235);
    private static final Color BOT_COLOR = new Color(5, 150, 105);
    private static final Color BACKGROUND = new Color(248, 250, 252);

    private final ChatbotEngine chatbotEngine;
    private final JTextArea chatArea;
    private final JTextField inputField;

    public ChatbotFrame() {
        super("codealpha_AI chatbot");
        this.chatbotEngine = new ChatbotEngine();
        this.chatArea = createChatArea();
        this.inputField = createInputField();

        configureFrame();
        appendBotMessage(chatbotEngine.getWelcomeMessage());
    }

    private void configureFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(720, 520));
        setLocationRelativeTo(null);
        getContentPane().setBackground(BACKGROUND);
        setLayout(new BorderLayout(12, 12));

        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(16, 16, 0, 16));
        header.setBackground(BACKGROUND);

        var title = new javax.swing.JLabel("codealpha_AI chatbot");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(15, 23, 42));

        var subtitle = new javax.swing.JLabel("Java FAQ Assistant • NLP + Rule-Based Matching");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitle.setForeground(new Color(100, 116, 139));

        header.add(title, BorderLayout.NORTH);
        header.add(subtitle, BorderLayout.SOUTH);

        JScrollPane scrollPane = new JScrollPane(chatArea);
        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(8, 16, 8, 16),
                BorderFactory.createLineBorder(new Color(226, 232, 240))
        ));

        JPanel inputPanel = createInputPanel();

        add(header, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);
    }

    private JTextArea createChatArea() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setFont(new Font("Consolas", Font.PLAIN, 14));
        area.setBackground(Color.WHITE);
        area.setMargin(new Insets(12, 12, 12, 12));
        return area;
    }

    private JTextField createInputField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(203, 213, 225)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        field.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ENTER) {
                    sendMessage();
                }
            }
        });
        return field;
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new BorderLayout(8, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(0, 16, 16, 16));
        panel.setBackground(BACKGROUND);

        JButton sendButton = new JButton("Send");
        sendButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sendButton.setBackground(USER_COLOR);
        sendButton.setForeground(Color.WHITE);
        sendButton.setFocusPainted(false);
        sendButton.addActionListener(event -> sendMessage());

        panel.add(inputField, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);
        return panel;
    }

    private void sendMessage() {
        String message = inputField.getText().trim();
        if (message.isEmpty()) {
            return;
        }

        appendUserMessage(message);
        inputField.setText("");

        SwingUtilities.invokeLater(() -> {
            String response = chatbotEngine.processMessage(message);
            appendBotMessage(response);
        });
    }

    private void appendUserMessage(String message) {
        appendMessage("You", message, USER_COLOR);
    }

    private void appendBotMessage(String message) {
        appendMessage("Bot", message, BOT_COLOR);
    }

    private void appendMessage(String sender, String message, Color color) {
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
        chatArea.append(String.format("[%s] %s:%n", time, sender));
        chatArea.setForeground(color);
        chatArea.append(message + "\n\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }
}
