package memgame;

import javax.swing.*;

public class GameLogic {
    private DataManager data;
    private WindowUI ui;
    private int playerStep = 0;
    private boolean canClick = false;

    public GameLogic(DataManager data, WindowUI ui) {
        this.data = data;
        this.ui = ui;

        ui.startButton.addActionListener(e -> beginGame());
        ui.redButton.addActionListener(e -> processClick("RED"));
        ui.blueButton.addActionListener(e -> processClick("BLUE"));
        ui.greenButton.addActionListener(e -> processClick("GREEN"));
        ui.yellowButton.addActionListener(e -> processClick("YELLOW"));
    }

    private void beginGame() {
        data.resetGame();
        ui.scoreDisplay.setText("Score: 0");
        nextLevel();
    }

    private void nextLevel() {
        canClick = false;
        playerStep = 0;
        data.addRandomColor();
        showSequence();
    }

    private void showSequence() {
        new Thread(() -> {
            ui.messageLabel.setText("Watch carefully...");
            try {
                for (String color : data.getSequence()) {
                    Thread.sleep(600);
                    SwingUtilities.invokeLater(() -> ui.lightUpButton(color));
                    Thread.sleep(600);
                }
            } catch (InterruptedException ignored) {
            }

            SwingUtilities.invokeLater(() -> {
                ui.messageLabel.setText("Your turn!");
                canClick = true;
            });
        }).start();
    }

    private void processClick(String color) {
        if (!canClick) return;

        ui.lightUpButton(color);

        if (color.equals(data.getSequence().get(playerStep))) {
            playerStep++;

            if (playerStep == data.getSequence().size()) {
                data.increaseScore();
                ui.scoreDisplay.setText("Score: " + data.getCurrentScore());

                Timer delayTimer = new Timer(700, e -> nextLevel());
                delayTimer.setRepeats(false);
                delayTimer.start();
            }
        } else {
            endGame();
        }
    }

    private void endGame() {
        canClick = false;
        data.recordScore();
        ui.showTopScores(data.getTopScores());

        JOptionPane.showMessageDialog(ui.window,
                "Game Over!\nYour Score: " + data.getCurrentScore(),
                "Simon Game",
                JOptionPane.INFORMATION_MESSAGE);

        ui.messageLabel.setText("Click START to play again");
    }
}