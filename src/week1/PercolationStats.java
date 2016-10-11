package week1;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] percolationPoints;
    private int trials;

    public PercolationStats(int n, int trials) {

        if (n <= 0) {
            throw new IllegalArgumentException("n should be greater than 0 its currently " + n);
        }

        if (trials <= 0) {
            throw new IllegalArgumentException("trials should be greater than 0 its currently " + trials);
        }

        this.trials = trials;
        this.percolationPoints = new double[trials];

        int gridSize = n * n;
        Percolation percolation;

        for (int trialCount = 0; trialCount < trials; trialCount++) {

            percolation = new Percolation(n);

            double opened = 0;

            while (!percolation.percolates()) {

                int iVal = StdRandom.uniform(n) + 1;
                int jVal = StdRandom.uniform(n) + 1;

                if (!percolation.isOpen(iVal, jVal)) {
                    percolation.open(iVal, jVal);
                    opened++;
                }
            }

            percolationPoints[trialCount] = opened / gridSize;
        }
    }

    public double mean() {
        return StdStats.mean(percolationPoints);
    }

    public double stddev() {

        if (trials == 1) {
            return  Double.NaN;
        }

        return StdStats.stddev(percolationPoints);
    }

    public double confidenceLo() {
        return mean() - getConfidenceTolerance();
    }

    private double getConfidenceTolerance() {
        return (1.96 * stddev()) / Math.sqrt(percolationPoints.length);
    }

    public double confidenceHi() {
        return  mean() + getConfidenceTolerance();
    }

    public static void main(String[] args) {

        PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));

        StdOut.printf("mean                    = %f\n", percolationStats.mean());
        StdOut.printf("stddev                  = %f\n", percolationStats.stddev());
        StdOut.printf("95%% confidence interval = %f, %f\n", percolationStats.confidenceLo(), percolationStats.confidenceHi());
    }
}
