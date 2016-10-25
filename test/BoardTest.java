
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BoardTest {

    Board testee;

    @Before
    public void setup() {

        int[][] blocks = getBlocks3x3();

        testee = new Board(blocks);
    }

    private int[][] getBlocks3x3() {
        int[][] blocks = new int[3][3];

        blocks[0][0] = 0;
        blocks[0][1] = 1;
        blocks[0][2] = 3;
        blocks[1][0] = 4;
        blocks[1][1] = 2;
        blocks[1][2] = 5;
        blocks[2][0] = 7;
        blocks[2][1] = 8;
        blocks[2][2] = 6;
        return blocks;
    }

    private int[][] getBlocks3x3Tester() {
        int[][] blocks = new int[3][3];

        blocks[0][0] = 8;
        blocks[0][1] = 1;
        blocks[0][2] = 3;
        blocks[1][0] = 4;
        blocks[1][1] = 0;
        blocks[1][2] = 2;
        blocks[2][0] = 7;
        blocks[2][1] = 6;
        blocks[2][2] = 5;
        return blocks;
    }

    private int[][] getGoalBoard() {

        int[][] blocks = new int[3][3];

        blocks[0][0] = 1;
        blocks[0][1] = 2;
        blocks[0][2] = 3;
        blocks[1][0] = 4;
        blocks[1][1] = 5;
        blocks[1][2] = 6;
        blocks[2][0] = 7;
        blocks[2][1] = 8;
        blocks[2][2] = 0;
        return blocks;
    }

    @Test
    public void testDimension() {

        assertEquals(3, testee.dimension());
    }

    @org.junit.Ignore
    @Test
    public void testEquals() {

        assertTrue(testee.equals(getBlocks3x3()));
    }

    @Test
    public void testHamming() {

        assertEquals(4, testee.hamming());
    }

    @Test
    public void testHamming2() {

        testee = new Board(getBlocks3x3Tester());

        assertEquals(5, testee.hamming());
    }

    @Test
    public void testToString() {

        StringBuilder sb = new StringBuilder();

        sb.append("3\n");
        sb.append(String.format("%2d %2d %2d \n", 0, 1, 3));
        sb.append(String.format("%2d %2d %2d \n", 4, 2, 5));
        sb.append(String.format("%2d %2d %2d \n", 7, 8, 6));

        assertEquals(sb.toString(), testee.toString());
    }

    @Test
    public void testIsGoalFalse() {

        assertFalse(testee.isGoal());
    }

    @Test
    public void testIsGoalTrue() {

        testee = new Board(getGoalBoard());
        assertTrue(testee.isGoal());
    }
}
