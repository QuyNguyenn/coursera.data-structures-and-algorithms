import java.util.Scanner;

public class DotProduct {

    private static void binaryInsertionSort(int[] a) {
        int l, r, m;
        int tmp, max;
        for (int i = 1; i < a.length; i++) {
            max = a[i];
            l = 0;
            r = i - 1;
            while (l <= r) {
                m = (l + r) / 2;
                if (a[m] > max) {
                    l = m + 1;
                }
                else {
                    r = m - 1;
                }
            }
            tmp = max;
            if (i - l >= 0) System.arraycopy(a, l, a, l + 1, i - l);
            a[l] = tmp;
        }
    }

    private static long maxDotProduct(int[] a, int[] b) {
        //write your code here
        binaryInsertionSort(a);
        binaryInsertionSort(b);
        long result = 0;
        for (int i = 0; i < a.length; i++) result += (long) a[i] * b[i];
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = scanner.nextInt();
        }
        System.out.println(maxDotProduct(a, b));
    }
}
