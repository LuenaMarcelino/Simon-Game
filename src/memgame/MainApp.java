package memgame;

import javax.swing.SwingUtilities;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DataManager data = new DataManager();
            WindowUI ui = new WindowUI();
            new GameLogic(data, ui);
        });
    }
}