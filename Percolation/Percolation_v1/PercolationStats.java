/* Amitoj Singh created this file on August 4, 2017.

Percolation: Given a composite systems comprised of randomly distributed
insulating and metallic materials: what fraction of the materials need to be
metallic so that the composite system is an electrical conductor? Given a porous
landscape with water on the surface (or oil below), under what conditions will
the water be able to drain through to the bottom (or the oil to gush through to
the surface)? Scientists have defined an abstract process known as percolation
to model such situations.

The Problem: In a famous scientific problem, researchers are interested in
the following question: if sites are independently set to be open with
probability p (and therefore blocked with probability 1 − p), what is the
probability that the system percolates? When p equals 0, the system does not
percolate; when p equals 1, the system percolates.

By repeating this computation experiment T times and averaging the results, we
obtain a more accurate estimate of the percolation threshold. Let xt be the
fraction of open sites in computational experiment t. The sample mean x provides
an estimate of the percolation threshold; the sample standard deviation s;
measures the sharpness of the threshold.
*/
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    // Variable to store number of traila.
    private final int trials;

    // Array to store result of each trail.
    private final double[] result;

    // Variable to store mean of all the percolation thresholds.
    private double mean;

    // Variable to store sttdev to all the percolation thresholds.
    private double stddev;

    // Perform trials independent experiments on an n-by-n grid.
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException("Invalid Size/Trials");

        this.trials = trials;
        result = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                percolation.open(StdRandom.uniform(1, n+1), StdRandom.uniform(1, n+1));
            }

            // Store percolation threshold into the result array.
            result[i] = (double) percolation.numberOfOpenSites() / (double) (n * n);
        }
    }

    // Sample mean of all percolation thresholds.
    public double mean() {
        mean = StdStats.mean(result);
        return mean;
    }

    // Sample standard deviation of all percolation thresholds.
    public double stddev() {
        stddev = StdStats.stddev(result);
        return stddev;
    }

    // Low  endpoint of 95% confidence interval.
    public double confidenceLo() {
        return mean - 1.96 * stddev / Math.sqrt((double) trials);
    }

    // High endpoint of 95% confidence interval.
    public double confidenceHi() {
        return mean + 1.96 * stddev / Math.sqrt((double) trials);
    }

    // Test client
    public static void main(String[] args) {

        // Parse first input argument, "n" for n-by-n grid.
        int n = Integer.parseInt(args[0]);

        // Parse second input argument, number of trials.
        int trials = Integer.parseInt(args[1]);

        PercolationStats percolationStats = new PercolationStats(n, trials);

        // Print mean.
        System.out.println("mean                    = " + percolationStats.mean());

        // Print sttdev.
        System.out.println("stddev                  = " + percolationStats.stddev());

        // Print 95% confidence interval.
        System.out.println("95% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");

    }
}
