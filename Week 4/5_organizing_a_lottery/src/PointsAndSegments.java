import java.util.Random;
import java.util.Scanner;

public class PointsAndSegments {

    private static void mergeSortPoints(int[] points, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        mergeSortPoints(points, l, mid);
        mergeSortPoints(points, mid + 1, r);
        int i = l;
        int j = mid + 1;
        int k = 0;
        int[] tmp = new int[r - l + 1];
        while (i <= mid && j <= r) {
            while (points[i] <= points[j] && i <= mid) {
                tmp[k] = points[i];
                k++;
                i++;
            }
            while (points[j] < points[i] && j <= r) {
                tmp[k] = points[j];
                k++;
                j++;
            }
        }
        for (; i <= mid; i++) {
            tmp[k] = points[i];
            k++;
        }
        for (; j <= r; j++) {
            tmp[k] = points[j];
            k++;
        }
        for (k = 0; k < r - l + 1; k++) {
            points[k + l] = tmp[k];
        }
    }

    private static void mergeSort(int[] starts, int[] ends, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) / 2;
        mergeSort(starts, ends, l, mid);
        mergeSort(starts, ends, mid + 1, r);
        int i = l;
        int j = mid + 1;
        int k = 0;
        int[] tmp1 = new int[r - l + 1];
        int[] tmp2 = new int[r - l + 1];
        while (i <= mid && j <= r) {
            while (i <= mid && starts[i] <= starts[j]) {
                tmp1[k] = starts[i];
                tmp2[k] = ends[i];
                k++;
                i++;
            }
            while (j <= r && starts[j] < starts[i]) {
                tmp1[k] = starts[j];
                tmp2[k] = ends[j];
                k++;
                j++;
            }
        }
        for (; i <= mid; i++) {
            tmp1[k] = starts[i];
            tmp2[k] = ends[i];
            k++;
        }
        for (; j <= r; j++) {
            tmp1[k] = starts[j];
            tmp2[k] = ends[j];
            k++;
        }
        for (k = 0; k < r - l + 1; k++) {
            starts[k + l] = tmp1[k];
            ends[k + l] = tmp2[k];
        }
    }

    private static int binarySearch(int[] array, int n) {
        int mid = 0;
        int l = 0;
        int r = array.length - 1;
        while (l <= r) {
            mid = (r + l) / 2;
            if (array[mid] <= n) {
                l = mid + 1;
            }
            else if (array[mid] > n) {
                r = mid - 1;
            }
        }
        return r;
    }

    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int n = points.length;
        int p = 0;
        int b = 0;
        int[] cnt = new int[n];
        for (int i = 0; i < n; i++) {
            p = binarySearch(starts, points[i]);
            //System.out.println(points[i] + " " + b + " " + p);
            cnt[i] = 0;
            for (int j = b; j <= p; j++) {
                if (ends[j] >= points[i]) {
                    //System.out.println(starts[j] + " " + ends[j]);
                    cnt[i]++;
                    /*if (cnt[i] == 1) {
                        b = i;
                    }*/
                }
            }
        }
        return cnt;
    }

    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        /*while (true) {
            Random random = new Random();
            int n, m;
            n = random.nextInt(15) + 5;
            m = random.nextInt(5) + 2;
            int[] starts = new int[n];
            int[] ends = new int[n];
            int[] points = new int[m];
            for (int i = 0; i < n; i++) {
                starts[i] = random.nextInt(100);
                ends[i] = random.nextInt(100 - starts[i]) + starts[i];
            }
            for (int i = 0; i < m; i++) {
                points[i] = random.nextInt(200) - 50;
            }
            mergeSort(starts, ends, 0, starts.length - 1);
            System.out.println("----------------");
            int[] cnt = fastCountSegments(starts, ends, points);
            int[] cnt2 = naiveCountSegments(starts, ends, points);
            for (int i = 0; i < n; i++) {
                System.out.print(starts[i] + " ");
            }
            System.out.println("");
            for (int i = 0; i < n; i++) {
                System.out.print(ends[i] + " ");
            }
            System.out.println("");
            for (int i = 0; i < m; i++) {
                System.out.print(points[i] + " ");
            }
            System.out.println("");
            for (int x : cnt) {
                System.out.print(x + " ");
            }
            System.out.println("");
            for (int x : cnt2) {
                System.out.print(x + " ");
            }
            System.out.println("");
            for (int i = 0; i < m; i++) {
                if (cnt[i] != cnt2[i]) {
                    System.out.println("FAIL");
                    return;
                }
            }
            System.out.println("OK");
        }*/

        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        mergeSort(starts, ends, 0, starts.length - 1);
        //mergeSortPoints(points, 0, points.length - 1);
        //use fastCountSegments
        //int[] cnt = naiveCountSegments(starts, ends, points);
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }
}

