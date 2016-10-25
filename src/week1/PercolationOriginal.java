package week1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationOriginal {

    private WeightedQuickUnionUF weightedQuickUnionUF;
    private int[] openSites;
    private int n;

    private int topSite;
    private int bottomSite;

    public PercolationOriginal(int n) {

        if (n <= 0) {
            throw new IllegalArgumentException("n should be greater than 0, but its " + n);
        }

        this.n = n;
        int gridSize = n * n;
        this.topSite = gridSize;
        this.bottomSite = gridSize + 1;

        // Add an additional 2 to the size to allow a top and bottom sites
        weightedQuickUnionUF = new WeightedQuickUnionUF(gridSize+2);
        openSites = new int[gridSize];
    }

    public void open(int i, int j) {

        validatePoint(i, j);

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

    public boolean isOpen(int i, int j) {

        validatePoint(i, j);

        return openSites[coordToSite(i, j)] == 1;
    }

    public boolean isFull(int i, int j) {

        validatePoint(i, j);

        return weightedQuickUnionUF.connected(topSite, coordToSite(i, j));
    }

    public boolean percolates() {

        return weightedQuickUnionUF.connected(topSite, bottomSite);
    }

    private int coordToSite(int i, int j) {

        return (n*(i-1)) + (j-1);
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
            throw new IndexOutOfBoundsException("week3.Point " + i + ", " + j + " is invalid");
        }
    }

    public static void main(String[] args) {

    }
}
