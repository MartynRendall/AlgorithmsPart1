import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF weightedQuickUnionUF;
    private int[] openSites;
    private int[][] coordToSiteMapping;
    private int n;
    private int topSite;
    private int bottomSite;

    public Percolation(int n) {

        this.n = n;
        int gridSize = n * n;
        this.topSite = gridSize;
        this.bottomSite = gridSize + 1;

        // Add an additional 2 to the size to allow a top and bottom sites
        weightedQuickUnionUF = new WeightedQuickUnionUF(gridSize+2);
        openSites = new int[gridSize];

        coordToSiteMapping = new int[n][n];

        int siteCounter = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                coordToSiteMapping[i][j] = siteCounter;
                siteCounter++;
            }
        }
    }

    public int coordToSite(int i, int j) {

        return coordToSiteMapping[i-1][j-1];
    }

    public void open(int i, int j) {

        openSites[coordToSite(i, j)] = 1;

        // if its a top row i.e j= 1?? then we should connect this block to the topSite
        if (i == 1) {
            connectPoints(topSite, coordToSite(i, j));
        }

        // if its a bottom row i.e i = n?? then we should connect this block to the bottomSite
        if (i == n) {
            connectPoints(bottomSite, coordToSite(i, j));
        }

        connectToNeighbours(i, j);
    }

    private void connectToNeighbours(int i, int j) {

        // is site above open?
        connectIfOpen(i-1, j, coordToSite(i, j));

        // is site below open?
        connectIfOpen(i+1, j, coordToSite(i, j));

        // is site to left open?
        connectIfOpen(i, j-1, coordToSite(i, j));

        // is site to right open?
        connectIfOpen(i, j+1, coordToSite(i, j));
    }

    private void connectIfOpen(int neighbourI, int neighbourJ, int openedPoint) {

        if (isValidPoint(neighbourI, neighbourJ)) {
            if (isOpen(neighbourI, neighbourJ)) {

                connectPoints(openedPoint, coordToSite(neighbourI, neighbourJ));
            }
        }
    }

    private void connectPoints(int pointA, int pointB) {

        weightedQuickUnionUF.union(pointA, pointB);
    }

    private boolean isValidPoint(int i, int j) {

        return (i > 0 && i <= n) && (j > 0 && j <= n);
    }

    private void validatePoint(int i, int j) {

        if (!isValidPoint(i, j)) {
            throw new IllegalArgumentException("Point " + i + ", " + j + " is invalid");
        }
    }

    public boolean isOpen(int i, int j) {

        return openSites[coordToSite(i, j)] == 1;
    }

    public boolean isFull(int i, int j) {

        return weightedQuickUnionUF.connected(topSite, coordToSite(i, j));
    }

    public boolean percolates() {

        return weightedQuickUnionUF.connected(topSite, bottomSite);
    }

    public static void main(String[] args) {

    }
}
