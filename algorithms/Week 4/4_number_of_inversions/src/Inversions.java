import java.util.*;

public class Inversions {

    private static long getNumberOfInversions(int[] a, int[] b, int left, int right) {
        long numberOfInversions = 0;
        if (right <= left + 1) {
            return numberOfInversions;
        }
        int ave = (left + right) / 2;
        numberOfInversions += getNumberOfInversions(a, b, left, ave);
        numberOfInversions += getNumberOfInversions(a, b, ave, right);
        //write your code here
        int i = left;
        int j = ave;
        int k = 0;
        int[] tmp = new int[right - left];
        while (i < ave && j < right) {
            while (i < ave && a[i] <= a[j]) {
                tmp[k] = a[i];
                i++;
                k++;
            }
            while (j < right && a[j] < a[i]) {
                tmp[k] = a[j];
                j++;
                k++;
                numberOfInversions += ave - i;
            }
        }
        //numberOfInversions += ave - i - 1;
        for (; i < ave; i++)
        {
            tmp[k] = a[i];
            k++;
        }
        for (; j < right; j++)
        {
            tmp[k] = a[j];
            k++;
        }
        for (k = 0; k < right - left; k++) {
            a[left + k] = tmp[k];
        }
        //System.out.println(left + " " + right + " " + numberOfInversions);
        return numberOfInversions;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        System.out.println(getNumberOfInversions(a, b, 0, a.length));
    }
}

