package memgame;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class UnitTests {

    private DataManager data;

    @Before
    public void setUp() {
        data = new DataManager();
    }

    // UNIT TEST 1: Test adding random colors
    @Test
    public void testAddRandomColor() {
        assertEquals(0, data.getSequence().size());

        data.addRandomColor();
        assertEquals(1, data.getSequence().size());

        data.addRandomColor();
        assertEquals(2, data.getSequence().size());

        String color = data.getSequence().get(0);
        assertTrue(color.equals("RED") || color.equals("BLUE") ||
                color.equals("GREEN") || color.equals("YELLOW"));
    }

    // UNIT TEST 2: Test score increase and reset
    @Test
    public void testScoreManagement() {
        assertEquals(0, data.getCurrentScore());

        data.increaseScore();
        assertEquals(1, data.getCurrentScore());

        data.increaseScore();
        assertEquals(2, data.getCurrentScore());

        data.resetGame();
        assertEquals(0, data.getCurrentScore());
    }
}
