import java.io.*;
import java.util.*;

public class Sorting {

    private static Random random = new Random();

    private static int[] partition3(int[] a, int l, int r) {
        int x = a[l];
        int tmp = 0;
        int i = l;
        int j = r + 1;
        for (int k = i + 1; k <= r; k++) {
            if (a[k] < x) {
                i++;
                tmp = a[k];
                a[k] = a[i];
                a[i] = tmp;
            }
        }
        for (int k = j - 1; k > l; k--) {
            if (a[k] > x) {
                j--;
                tmp = a[k];
                a[k] = a[j];
                a[j] = tmp;
            }
        }
        tmp = a[i];
        a[i] = a[l];
        a[l] = tmp;
        int m1 = i - 1;
        int m2 = j;
        int[] m = {m1, m2};
        return m;
    }

    private static int partition2(int[] a, int l, int r) {
        int x = a[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= x) {
                j++;
                int t = a[i];
                a[i] = a[j];
                a[j] = t;
            }
        }
        int t = a[l];
        a[l] = a[j];
        a[j] = t;
        return j;
    }

    private static void randomizedQuickSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;
        //use partition3
        int[] m = partition3(a, l, r);
        randomizedQuickSort(a, l, m[0]);
        randomizedQuickSort(a, m[1], r);
    }

    private static void quickSortByMe(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = a[(r + l) / 2];
        int i = 0;
        int j = r;
        while (i < j) {
            while (a[i] < p && i < j) {
                i++;
            }
            while (a[j] > p && j > i) {
                j--;
            }
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
            if (a[i] == a[j]) {
                i++;
                j--;
            }
        }
        quickSortByMe(a, l, i - 1);
        quickSortByMe(a, i + 1, r);
    }

    private static void quickSortByMeV2(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int p = random.nextInt(r - l) + l;
        int tmp  = a[p];
        a[p] = a[l];
        a[l] = tmp;
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= a[l]) {
                j++;
                tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            }
        }
        tmp = a[j];
        a[j] = a[l];
        a[l] = tmp;
        quickSortByMeV2(a, l, j - 1);
        quickSortByMeV2(a, j + 1, r);
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        //quickSortByMeV2(a, 0, n - 1);
        randomizedQuickSort(a, 0, n - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

