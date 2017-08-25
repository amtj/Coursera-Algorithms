import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private final ArrayList<LineSegment> segmentList = new ArrayList<>();

    // Finds all line segments containing 4 points.
    public BruteCollinearPoints(Point[] pointsArg) {

        if (pointsArg == null) throw new IllegalArgumentException();

        int length = pointsArg.length;

        for (int i = 0; i < length; i++) {
            if (pointsArg[i] == null) throw new IllegalArgumentException();
        }

        Point[] points = Arrays.copyOf(pointsArg, length);
        Arrays.sort(points);

        for (int i = 1; i < length; i++) {
            if (points[i].compareTo(points[i - 1]) == 0) throw new IllegalArgumentException();
        }

        for (int i = 0; i < length; i++) {
            Point point = points[i];

            for (int j = i + 1; j < length; j++) {
                Point p1 = points[j];

                for (int k = j + 1; k < length; k++) {
                    Point p2 = points[k];
                    if (point.slopeTo(p1) != point.slopeTo(p2)) continue;

                    for (int m = k + 1; m < length; m++) {
                        Point p3 = points[m];

                        if (point.slopeTo(p1) == point.slopeTo(p3))
                        segmentList.add(new LineSegment(point, p3));
                    }
                }
            }
        }
    }

    // The number of line segments.
    public int numberOfSegments() {
        return segmentList.size();
    }

    // The line segments.
    public LineSegment[] segments() {
        LineSegment[] segments = new LineSegment[segmentList.size()];
        return segmentList.toArray(segments);
    }
}
