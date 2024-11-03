import java.util.*;

public class LargestNumber {
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

    private static boolean compare(String a, String b, int index) {
        int n = a.length() + b.length();
        char ai;
        char bi;
        if (index >= n) {
            return false;
        }
        ai = (a + b).charAt(index);
        bi = (b + a).charAt(index);
        if (ai > bi) {
            return true;
        }
        else if (ai < bi) {
            return false;
        }
        else {
            return compare(a, b, index + 1);
        }
    }

    private static boolean compare(String a, String b) {
        int ai = Integer.parseInt(a + b);
        int bi = Integer.parseInt(b + a);
        return ai > bi;
    }

    private static String largestNumber(String[] a) {
        //write your code here
        int n = a.length;
        String max;
        String tmp;
        for (int i = 1; i < n; i++) {
            max = a[i];
            int j = i;
            while (j > 0) {
                if (compare(a[i], a[j - 1])) {
                    j--;
                }
                else {
                    break;
                }
            }
            tmp = max;
            if (i - j >= 0) System.arraycopy(a, j, a, j + 1, i - j);
            a[j] = tmp;
        }
        StringBuilder result = new StringBuilder();
        for (String s : a) {
            result.append(s);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] a = new String[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.next();
        }
        System.out.println(largestNumber(a));
    }
}

