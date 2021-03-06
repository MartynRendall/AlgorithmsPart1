package week3;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] points) {

        if (points == null) {
            throw new NullPointerException("points cannot be null");
        }

        findDuplicates(points);

        ArrayList<LineSegment> newSegments = new ArrayList<>();

        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsCopy);

        for (int p = 0; p < pointsCopy.length - 3; p++) {
            for (int q = p + 1; q < pointsCopy.length - 2; q++) {
                for (int r = q + 1; r < pointsCopy.length - 1; r++) {
                    for (int s = r + 1; s < pointsCopy.length; s++) {

                        if (pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[r])
                                && pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[s])) {

                            newSegments.add(new LineSegment(pointsCopy[p], pointsCopy[s]));
                        }
                    }
                }
            }
        }

        lineSegments = newSegments.toArray(new LineSegment[newSegments.size()]);
    }

    private void findDuplicates(Point[] points) {

        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {

                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Duplicated entries in given points.");
                }
            }
        }
    }

    public int numberOfSegments() {

        return lineSegments.length;
    }

    public LineSegment[] segments() {

        return Arrays.copyOf(lineSegments, numberOfSegments());
    }
}