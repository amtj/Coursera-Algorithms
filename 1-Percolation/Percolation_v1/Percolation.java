/* Amitoj Singh created this file on August 3, 2017.

Percolation: Given a composite systems comprised of randomly distributed
insulating and metallic materials: what fraction of the materials need to be
metallic so that the composite system is an electrical conductor? Given a
porous landscape with water on the surface (or oil below), under what
conditions will the water be able to drain through to the bottom (or the oil
to gush through to the surface)? Scientists have defined an abstract process
known as percolation to model such situations.

The Problem: In a famous scientific problem, researchers are interested in
the following question: if sites are independently set to be open with
probability p (and therefore blocked with probability 1 − p), what is the
probability that the system percolates? When p equals 0, the system does not
percolate; when p equals 1, the system percolates.
*/
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    // Variable to keep count of open sites.
    private int count = 0;

    // Variable to keep count of maximum rows/columns.
    private final int n;

    // Variables to keep value of top and bottom element.
    // top is element coonected to all top row elements.
    // bottom is element coonected to all bottom row elements.
    private final int top, bottom;

    // Declare weightedQuickUnionUF object.
    private final WeightedQuickUnionUF weightedQuickUnionUF;

    // Declare the grid.
    private boolean[][] grid;

    // Create n-by-n grid, with all sites blocked(false).
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("Invalid number");

        // Object to store grid.
        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2);

        // Stores count of maximum rows/columns.
        this.n = n;

        // Stores a grid of size n-by-n.
        // Default value if false.
        grid = new boolean[n][n];

        top = 0;
        bottom = n * n + 1;
    }

    // Returns a int value for a site.
    private int siteNumber(int row, int col) {
        validate(row, col);
        return (n * (row - 1) + col);
    }

    // Open site (row, col) if it is not open already.
    // true for open. false for closed.
    public void open(int row, int col) {
        validate(row, col);
        if (!grid[row-1][col-1]) {
            grid[row-1][col-1] = true;
            count++;

            if (row == 1) {
                weightedQuickUnionUF.union(siteNumber(row, col), top);
            }

            if (row == n) {
                weightedQuickUnionUF.union(siteNumber(row, col), bottom);
            }

            if (isOpen(row, col+1)) {
                if (!weightedQuickUnionUF.connected(siteNumber(row, col), siteNumber(row, col+1))) {
                    weightedQuickUnionUF.union(siteNumber(row, col), siteNumber(row, col+1));
                }
            }

            if (isOpen(row+1, col)) {
                if (!weightedQuickUnionUF.connected(siteNumber(row, col), siteNumber(row+1, col))) {
                    weightedQuickUnionUF.union(siteNumber(row, col), siteNumber(row+1, col));
                }
            }

            if (isOpen(row, col-1)) {
                if (!weightedQuickUnionUF.connected(siteNumber(row, col), siteNumber(row, col-1))) {
                    weightedQuickUnionUF.union(siteNumber(row, col), siteNumber(row, col-1));
                }
            }

            if (isOpen(row-1, col)) {
                if (!weightedQuickUnionUF.connected(siteNumber(row, col), siteNumber(row-1, col))) {
                    weightedQuickUnionUF.union(siteNumber(row, col), siteNumber(row-1, col));
                }
            }
        }
    }

    // Is site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) return false;
        return grid[row-1][col-1];
    }

    // Is site (row, col) full?
    public boolean isFull(int row, int col) {
        return weightedQuickUnionUF.connected(siteNumber(row, col), 0);
    }

    // Number of open sites.
    public int numberOfOpenSites() {
        return count;
    }

    // Does the system percolate?
    public boolean percolates() {
        return weightedQuickUnionUF.connected(0, n * n + 1);
    }

    private void validate(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n) throw new IllegalArgumentException("Invalid number");
    }
}
