package memgame;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WindowUI {
    protected JFrame window;
    protected JLabel messageLabel;
    protected JLabel scoreDisplay;
    protected JLabel recordsDisplay;
    protected JButton redButton;
    protected JButton blueButton;
    protected JButton greenButton;
    protected JButton yellowButton;
    protected JButton startButton;

    public WindowUI() {
        window = new JFrame("Simon Memory Game");
        window.setSize(450, 650);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout(15, 15));

        messageLabel = new JLabel("Click START to begin", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 18));

        scoreDisplay = new JLabel("Score: 0", SwingConstants.CENTER);
        scoreDisplay.setFont(new Font("Arial", Font.PLAIN, 16));

        JPanel colorGrid = new JPanel(new GridLayout(2, 2, 15, 15));
        redButton = makeColorButton(Color.RED);
        blueButton = makeColorButton(Color.BLUE);
        greenButton = makeColorButton(Color.GREEN);
        yellowButton = makeColorButton(Color.YELLOW);

        colorGrid.add(redButton);
        colorGrid.add(blueButton);
        colorGrid.add(greenButton);
        colorGrid.add(yellowButton);

        startButton = new JButton("START");
        startButton.setFont(new Font("Arial", Font.BOLD, 14));

        recordsDisplay = new JLabel("<html>High Scores:</html>", SwingConstants.CENTER);
        recordsDisplay.setFont(new Font("Arial", Font.PLAIN, 12));

        JPanel topSection = new JPanel(new GridLayout(2, 1));
        topSection.add(messageLabel);
        topSection.add(scoreDisplay);

        JPanel bottomSection = new JPanel(new BorderLayout());
        bottomSection.add(startButton, BorderLayout.NORTH);
        bottomSection.add(recordsDisplay, BorderLayout.CENTER);

        window.add(topSection, BorderLayout.NORTH);
        window.add(colorGrid, BorderLayout.CENTER);
        window.add(bottomSection, BorderLayout.SOUTH);

        window.setVisible(true);
    }

    private JButton makeColorButton(Color color) {
        JButton button = new JButton();
        button.setBackground(color);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return button;
    }

    public void lightUpButton(String colorName) {
        JButton button = findButton(colorName);
        Color originalColor = button.getBackground();
        button.setBackground(originalColor.brighter());

        Timer resetTimer = new Timer(300, e -> button.setBackground(originalColor));
        resetTimer.setRepeats(false);
        resetTimer.start();
    }

    public JButton findButton(String colorName) {
        if (colorName.equals("RED")) return redButton;
        if (colorName.equals("BLUE")) return blueButton;
        if (colorName.equals("GREEN")) return greenButton;
        return yellowButton;
    }

    public void showTopScores(List<Integer> scores) {
        StringBuilder display = new StringBuilder("<html>High Scores:<br/>");
        for (int i = 0; i < scores.size(); i++) {
            display.append(i + 1).append(". ").append(scores.get(i)).append("<br/>");
        }
        recordsDisplay.setText(display.append("</html>").toString());
    }
}