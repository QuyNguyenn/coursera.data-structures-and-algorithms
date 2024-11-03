import java.io.*;
import java.util.*;

public class Closest {

    public static int binarySearchLow(int[] a, int[] index, int l, int r, double n) {
        int mid;
        while (l <= r) {
            mid = (l + r) / 2;
            if (a[index[mid]] < n) {
                l = mid + 1;
            }
            if (a[index[mid]] >= n) {
                r = mid - 1;
            }
        }
        return r + 1;
    }

    public static int binarySearchHigh(int[] a, int[] index, int l, int r, double n) {
        int mid;
        while (l <= r) {
            mid = (l + r) / 2;
            if (a[index[mid]] <= n) {
                l = mid + 1;
            }
            if (a[index[mid]] > n) {
                r = mid - 1;
            }
        }
        return r;
    }

    public static void mergeSortIndex(int[] a, int[] index, int l, int r) {
        int mid = (l + r) / 2;
        if (l >= r - 1)
            return;
        mergeSortIndex(a, index, l, mid);
        mergeSortIndex(a, index, mid, r);
        int i = l;
        int j = mid;
        int k = 0;
        int[] tmp = new int[r - l];
        while (i < mid && j < r) {
            while (i < mid && a[index[i]] <= a[index[j]]) {
                tmp[k] = index[i];
                k++;
                i++;
                // System.out.println(i + " " + j + "b");
            }
            while (j < r && i < mid && a[index[j]] <= a[index[i]]) {
                tmp[k] = index[j];
                k++;
                j++;
                // System.out.println(i + " " + j + "c");
            }
        }
        for (; i < mid; i++) {
            tmp[k] = index[i];
            k++;
        }
        for (; j < r; j++) {
            tmp[k] = index[j];
            k++;
        }
        for (k = 0; k < r - l; k++) {
            index[l + k] = tmp[k];
        }
    }

    static class Point implements Comparable<Point> {
        long x, y;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return o.y == y ? Long.signum(x - o.x) : Long.signum(y - o.y);
        }
    }

    static double minimalDistance(int[] x, int[] y, int[] xIndex, int[] yIndex, int l, int r) {
        double ans = Double.POSITIVE_INFINITY;
        //write your code here
        int mid1 = (l + r) / 2;
        int mid2 = (l == r - 2) ? mid1 : mid1 + 1;
        System.out.println(l + " " + mid1 + " " + mid2 + " " + r);
        if (l == r - 1) {
            ans = Math.pow((double) x[xIndex[l]] - x[xIndex[r]], 2.0) + Math.pow((double) y[xIndex[l]] - y[xIndex[r]], 2.0);
            ans = Math.sqrt(ans);
            System.out.println(ans);
            return ans;
        }
        double d1 = minimalDistance(x, y, xIndex, yIndex, l, mid1);
        double d2 = minimalDistance(x, y, xIndex, yIndex, mid2, r);
        double d3 = Double.POSITIVE_INFINITY;
        double d = Math.min(d1, d2);
        ans = d;
        int i = mid1;
        int j = mid1;
        while (i > l && x[xIndex[i - 1]] >= x[xIndex[mid1]] - d) {
            i--;
        }
        while (j < r && x[xIndex[j + 1]] <= x[xIndex[mid1]] + d) {
            j++;
        }
        System.out.println(i + " " + j);
        for (int k = i; k <= j - 1; k++) {
            for (int h = k + 1; h <= j; h++) {
                if (Math.abs(y[xIndex[k]] - y[xIndex[h]]) <= d) {
                    d3 = Math.pow((double) x[xIndex[k]] - x[xIndex[h]], 2.0) + Math.pow((double) y[xIndex[k]] - y[xIndex[h]], 2.0);
                    d3 = Math.sqrt(d3);
                    ans = Math.min(d3, ans);
                }
            }
        }
        System.out.println(ans);
        return ans;
    }

    static double minimalDistance2(int[] x, int[] y, int[] xIndex, int[] yIndex, int l, int r) {
        double ans = Double.POSITIVE_INFINITY;

        //write your code here
        int mid1 = (l + r) / 2;
        int mid2 = (l == r - 2) ? mid1 : mid1 + 1;
        if (l == r - 1) {
            ans = Math.pow((double) x[xIndex[l]] - x[xIndex[r]], 2.0) + Math.pow((double) y[xIndex[l]] - y[xIndex[r]], 2.0);
            ans = Math.sqrt(ans);
            return ans;
        }

        // Recursion
        double d1 = minimalDistance2(x, y, xIndex, yIndex, l, mid1);
        double d2 = minimalDistance2(x, y, xIndex, yIndex, mid2, r);

        // Get minimum distance
        double d3 = Double.POSITIVE_INFINITY;
        double d = Math.min(d1, d2);
        ans = d;

        // Copy all point in the middle area to new array anh sort by Y coordination
        int i = binarySearchLow(x, xIndex, l, mid1, x[xIndex[mid1]] - d);
        int j = binarySearchHigh(x, xIndex, mid1, r, x[xIndex[mid1]] + d);
        int[] tmpX = new int[j - i + 1];
        int[] tmpY = new int[j - i + 1];
        int[] tmpYIndex = new int[j - i + 1];
        for (int k = 0; k < j - i + 1; k++) {
            tmpX[k] = x[xIndex[i + k]];
            tmpY[k] = y[xIndex[i + k]];
            tmpYIndex[k] = k;
        }
        mergeSortIndex(tmpY, tmpYIndex, 0, j - i + 1);

        // Find the minimum distance in the middle area
        for (int k = 0; k < j - i; k++) {
            int h = k;
            while (h < j - i && Math.abs(tmpY[tmpYIndex[h + 1]] - tmpY[tmpYIndex[k ]]) <= d) {
                h++;
                d3 = Math.pow((double) tmpX[tmpYIndex[h]] - tmpX[tmpYIndex[k]], 2.0) + Math.pow((double) tmpY[tmpYIndex[h]] - tmpY[tmpYIndex[k]], 2.0);
                d3 = Math.sqrt(d3);
                ans = Math.min(d3, ans);
            }
        }
        return ans;
    }

    public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = nextInt();
            y[i] = nextInt();
        }

        // My code
        int[] xIndex = new int[n];
        int[] yIndex = new int[n];
        for (int i = 0; i < n; i++) {
            xIndex[i] = i;
            yIndex[i] = i;
        }
        mergeSortIndex(x, xIndex, 0, n);
        mergeSortIndex(y, yIndex, 0, n);
        //System.out.println(minimalDistance(x, y, xIndex, yIndex, 0, n - 1));
        System.out.println(minimalDistance2(x, y, xIndex, yIndex, 0, n - 1));
        writer.close();
    }

    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");


    static String next() {
        while (!tok.hasMoreTokens()) {
            String w = null;
            try {
                w = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (w == null)
                return null;
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}
