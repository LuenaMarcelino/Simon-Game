package memgame;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class IntegrationTests {

    private DataManager data;

    @Before
    public void setUp() {
        data = new DataManager();
    }

    // INTEGRATION TEST 1: Test complete game flow
    @Test
    public void testGameFlow() {
        data.resetGame();

        // Round 1
        data.addRandomColor();
        assertEquals(1, data.getSequence().size());
        data.increaseScore();
        assertEquals(1, data.getCurrentScore());

        // Round 2
        data.addRandomColor();
        assertEquals(2, data.getSequence().size());
        data.increaseScore();
        assertEquals(2, data.getCurrentScore());

        // Round 3
        data.addRandomColor();
        assertEquals(3, data.getSequence().size());
        data.increaseScore();
        assertEquals(3, data.getCurrentScore());
    }

    // INTEGRATION TEST 2: Test high score system
    @Test
    public void testHighScores() {
        // Game 1: score 5
        for (int i = 0; i < 5; i++) {
            data.increaseScore();
        }
        data.recordScore();
        assertEquals(1, data.getTopScores().size());

        // Game 2: score 10
        data.resetGame();
        for (int i = 0; i < 10; i++) {
            data.increaseScore();
        }
        data.recordScore();

        // Check sorting (10 should be first, 5 second)
        assertEquals(10, (int)data.getTopScores().get(0));
        assertEquals(5, (int)data.getTopScores().get(1));
    }
}