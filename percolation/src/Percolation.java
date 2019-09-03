import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF quickUnionUF;
    private byte[] siteStatus; //0 - blocked, 1 - opened, 2 - filled
    private int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        quickUnionUF = new WeightedQuickUnionUF(n * n + 2);
        siteStatus = new byte[n * n];
        this.n = n;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        siteStatus[convert2dToPlane(row -1, col - 1)] = 1;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return siteStatus[convert2dToPlane(row -1, col - 1)] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return siteStatus[convert2dToPlane(row -1, col - 1)] == 2;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 0; i < siteStatus.length; i++) {
            if (siteStatus[i] == 1) {
                count++;
            }
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return quickUnionUF.connected(0, n - 1);
    }

    // test client (optional)
    public static void main(String[] args) {

    }

    private int convert2dToPlane(int i, int j) {
        return j + (i - 1) * n - 1;
    }

    private void validate(int row, int col) {
        if (row > n || col > n) {
            throw new IllegalArgumentException();
        }
    }
}