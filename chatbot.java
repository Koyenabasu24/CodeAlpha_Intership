

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ChatbotApp {
    private static Map<String, String> knowledgeBase = new HashMap<>();

    public static void main(String[] args) {
        // Add some FAQ knowledge (training data)
        knowledgeBase.put("hello", "Hi there! How can I help you today?");
        knowledgeBase.put("hi", "Hello! What’s up?");
        knowledgeBase.put("how are you", "I’m just a bot, but I’m doing great 😊");
        knowledgeBase.put("bye", "Goodbye! Have a nice day!");
        knowledgeBase.put("your name", "I am a simple Java Chatbot 🤖");
        knowledgeBase.put("what is java", "Java is a high-level, object-oriented programming language.");

        // Create GUI
        JFrame frame = new JFrame("Java Chatbot");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);

        JTextField inputField = new JTextField();
        JButton sendButton = new JButton("Send");

        JScrollPane scrollPane = new JScrollPane(chatArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(inputField, BorderLayout.CENTER);
        panel.add(sendButton, BorderLayout.EAST);

        frame.add(panel, BorderLayout.SOUTH);

        // Function to handle user input
        ActionListener sendAction = e -> {
            String userInput = inputField.getText().toLowerCase().trim();
            chatArea.append("You: " + userInput + "\n");

            String response = getResponse(userInput);
            chatArea.append("Bot: " + response + "\n\n");

            inputField.setText("");
        };

        inputField.addActionListener(sendAction);
        sendButton.addActionListener(sendAction);

        frame.setVisible(true);
    }

    // NLP-ish response function
    private static String getResponse(String input) {
        for (String key : knowledgeBase.keySet()) {
            if (input.contains(key)) {
                return knowledgeBase.get(key);
            }
        }
        return "Sorry, I don’t understand that. Can you rephrase?";
    }
}