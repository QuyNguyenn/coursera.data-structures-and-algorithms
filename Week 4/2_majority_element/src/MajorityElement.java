import java.util.*;
import java.io.*;

public class MajorityElement {

    private static void merge(int[] a, int l, int mid, int r) {
        int[] tmp = new int[r - l + 1];
        int i = l;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= r) {
            while (i <= mid && j <= r && a[i] <= a[j]) {
                tmp[k] = a[i];
                k++;
                i++;
            }
            while (j <= r && i <= mid && a[j] <= a[i]) {
                tmp[k] = a[j];
                k++;
                j++;
            }
        }
        for (; i <= mid; i++) {
            tmp[k] = a[i];
            k++;
        }
        for (; j <= r; j++) {
            tmp[k] = a[j];
            k++;
        }
        for (k = 0; k < r - l + 1; k++) {
            a[l + k] = tmp[k];
        }
    }

    private static void mergeSort(int[] a,int l,int r) {
        if (l == r) {
            return;
        }
        int mid = (l + r) / 2;
        mergeSort(a, l, mid);
        mergeSort(a, mid + 1, r);
        merge(a, l, mid, r);
    }

    private static int getMajorityElement(int[] a, int left, int right) {
        int count = 1;
        mergeSort(a, 0, right-1);
        for (int i = 0; i < right - 1; i++) {
            if (a[i + 1] == a[i]) {
                count++;
            }
            else {
                count = 1;
            }
            if (count > right / 2) {
                return 0;
            }
            else if (count == 1 && i > right / 2) {
                return -1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        if (getMajorityElement(a, 0, a.length) != -1) {
            System.out.println(1);
        } else {
            System.out.println(0);
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

