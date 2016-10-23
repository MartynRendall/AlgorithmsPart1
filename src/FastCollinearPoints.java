import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Collections;

public class FastCollinearPoints {

    private List<LineSegment> lineSegments = new ArrayList<>();
    private Map<Double, List<Point>> matchedSlopes = new HashMap<>();

    public FastCollinearPoints(Point[] points) {

        if (points == null) {
            throw new NullPointerException("points cannot be null");
        }

        findDuplicates(points);

        Point[] pointsCopy = Arrays.copyOf(points, points.length);

        for (Point startPoint : points) {

            Arrays.sort(pointsCopy, startPoint.slopeOrder());
            List<Point> currentMatchingPoints = new ArrayList<>();

            double previousSlope = Double.NEGATIVE_INFINITY;
            double currentSlope = 0;

            for (int q = 1; q < pointsCopy.length; q++) {

                currentSlope = startPoint.slopeTo(pointsCopy[q]);

                if (currentSlope == previousSlope) {
                    currentMatchingPoints.add(pointsCopy[q]);
                } else {

                    if (currentMatchingPoints.size() >= 3) {
                        currentMatchingPoints.add(startPoint);
                        addLineSegment(currentMatchingPoints, previousSlope);
                    }

                    currentMatchingPoints.clear();
                    currentMatchingPoints.add(pointsCopy[q]);

                }

                previousSlope = currentSlope;
            }

            if (currentMatchingPoints.size() >= 3) {
                currentMatchingPoints.add(startPoint);
                addLineSegment(currentMatchingPoints, currentSlope);
            }
        }
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

    private void addLineSegment(List<Point> matchingPoints, double slopeValue) {

        Collections.sort(matchingPoints);

        List<Point> points = matchedSlopes.get(slopeValue);

        Point startPoint = matchingPoints.get(0);
        Point endPoint = matchingPoints.get(matchingPoints.size() - 1);

        if (points == null) {

            points = new ArrayList<>();
            points.add(endPoint);

            lineSegments.add(new LineSegment(startPoint, endPoint));
            matchedSlopes.put(slopeValue, points);
        } else {

            for (Point point : points) {
                if (point.compareTo(endPoint) == 0) {
                    return;
                }
            }
            points.add(endPoint);
            lineSegments.add(new LineSegment(startPoint, endPoint));
        }
    }

    public int numberOfSegments() {

        return lineSegments.size();
    }

    public LineSegment[] segments() {

        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }
}

