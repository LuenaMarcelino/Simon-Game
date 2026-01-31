package memgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DataManager {
    private final List<String> sequence = new ArrayList<>();
    private final List<Integer> topScores = new ArrayList<>();
    private final String[] colors = {"RED", "BLUE", "GREEN", "YELLOW"};
    private final Random random = new Random();
    private int currentScore = 0;

    public void addRandomColor() {
        sequence.add(colors[random.nextInt(colors.length)]);
    }

    public List<String> getSequence() {
        return sequence;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void increaseScore() {
        currentScore++;
    }

    public void resetGame() {
        currentScore = 0;
        sequence.clear();
    }

    public void recordScore() {
        topScores.add(currentScore);
        topScores.sort(Collections.reverseOrder());
        if (topScores.size() > 10) {
            topScores.subList(10, topScores.size()).clear();
        }
    }

    public List<Integer> getTopScores() {
        return topScores;
    }
}