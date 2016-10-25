public class Board {

    private int[][] blocks;

    // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {

        this.blocks = blocks;
    }

    // board dimension n
    public int dimension() {
        return blocks.length;
    }

    // number of blocks out of place
    public int hamming() {

        int hamming = 0;

        int expected = 1;

        //TODO will hamming work if last node is not a 9??
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {

                if (blocks[i][j] != expected && blocks[i][j] != 0) {
                    hamming++;
                }
                expected++;
            }
        }

        return hamming;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {

        return 0;
    }

    // is this board the goal board?
    public boolean isGoal() {

        int expected = 1;

        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {

                if (blocks[i][j] != expected) {

                    if (blocks[i][j] == 0) {
                        return (i == blocks.length-1) && (j == blocks[i].length-1);
                    } else {
                        return false;
                    }

//                    if ((i == blocks.length-1) && (j == blocks[i].length-1)) {
//                        return false;
//                    } else {
//                        return false;
//                    }

                }

                expected++;
            }
        }

        return true;
    }

    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        return null;
    }

    // does this board equal y?
    public boolean equals(Object y) {

        return false;

    }

    // all neighboring boards
    public Iterable<Board> neighbors() {

        return null;

    }

    // string representation of this board (in the output format specified below)
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(dimension() + "\n");

        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {

                sb.append(String.format("%2d ", blocks[i][j]));
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}