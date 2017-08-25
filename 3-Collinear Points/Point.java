import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    // x-coordinate of this point.
    private final int x;

    // y-coordinate of this point.
    private final int y;

    // Constructs the point (x, y)
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Draws this point.
    public void draw() {
        StdDraw.point(x, y);
    }

    // Draws the line segment from this point to that point.
    public void drawTo(Point input) {
        StdDraw.line(this.x, this.y, input.x, input.y);
    }

    // String representation.
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    // Compare two points by y-coordinates, breaking ties by x-coordinates.
    // Returns a negative integer if this point is less than the argument
    // point, a positive integer if this point is greater than the
    // argument point or zero if both are equal.
    public int compareTo(Point input) {
        if (this.y < input.y) return -1;
        if (this.y > input.y) return  1;
        if (this.x < input.x) return -1;
        if (this.x > input.x) return  1;
        return 0;
    }

    // The slope between this point and that point.
    public double slopeTo(Point input) {
        if (compareTo(input) == 0) return Double.NEGATIVE_INFINITY;
        if (x == input.x) return Double.POSITIVE_INFINITY;
        if (y == input.y) return 0.0;
        return (double) (input.y - this.y) / (input.x - this.x);
    }

    // Compare two points by slopes they make with this point.
    public Comparator<Point> slopeOrder() {
        return new Comparator<Point>() {
            public int compare(Point arg1, Point arg2) {
                double slope = slopeTo(arg1) - slopeTo(arg2);
                if (slope > 0) return 1;
                if (slope < 0) return -1;
                return 0;
            }
        };
    }

    // Unit test.
    public static void main(String[] args) {
        Point point  = new Point(0, 0);

        Point p0 = new Point(0, 0);
        Point p1 = new Point(0, 2);
        Point p2 = new Point(2, 0);
        Point p3 = new Point(3, 4);
        Point p4 = new Point(4, 3);

        System.out.println(point.compareTo(p0));
        System.out.println(point.compareTo(p1));
        System.out.println(point.compareTo(p2));
        System.out.println(point.compareTo(p3));
        System.out.println(point.compareTo(p4));
        System.out.println("--X----X----X--");
        System.out.println(point.slopeTo(p0));
        System.out.println(point.slopeTo(p1));
        System.out.println(point.slopeTo(p2));
        System.out.println(point.slopeTo(p3));
        System.out.println(point.slopeTo(p4));
    }
}
