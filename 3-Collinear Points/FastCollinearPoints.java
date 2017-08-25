import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private final ArrayList<LineSegment> segmentList = new ArrayList<>();

    // Finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] pointsArg) {

        if (pointsArg == null) throw new IllegalArgumentException();
        
        int length = pointsArg.length;

        for (int i = 0; i < length; i++) {
            if (pointsArg[i] == null) throw new IllegalArgumentException();
        }

        Point[] points = Arrays.copyOf(pointsArg, length);
        for (int i = 0; i < length; i++) {
            Point point = pointsArg[i];
            Arrays.sort(points);
            Arrays.sort(points, point.slopeOrder());

            int min = 0;
            while (min < length && point.slopeTo(points[min]) == Double.NEGATIVE_INFINITY) min++;
            if (min != 1) throw new IllegalArgumentException(); // Check duplicate points

            int max = min;

            while (min < length) {
                while (max < length && point.slopeTo(points[max]) == point.slopeTo(points[min])) max++;
                if (max - min >= 3) {
                    Point pointMin = points[min];
                    if (!(points[min].compareTo(point) < 0)) pointMin = point;
                    if (point == pointMin)
                    segmentList.add(new LineSegment(pointMin, points[max - 1]));
                }
                min = max;
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
