import java.util.*;
import java.io.*;

public class Partition3 {
    private static int search(int[] A, int[][] tabulation, int iStart, int jStart, int number) {
        if (iStart == 0 || jStart == 0) {
            return 0;
        }
        if (tabulation[iStart][jStart] == number) {
            return 1;
        }
        if (tabulation[iStart][jStart] == tabulation[iStart - 1][jStart - A[iStart - 1]] + A[iStart - 1]) {
            if (search(A, tabulation, iStart - 1, jStart - A[iStart - 1], tabulation[iStart - 1][jStart - A[iStart - 1]]) == 1) {
                return 1;
            }
        }
        if (tabulation[iStart][jStart] == tabulation[iStart - 1][jStart]) {
            if (search(A, tabulation, iStart - 1, jStart, tabulation[iStart - 1][jStart]) == 1) {
                return 1;
            }
        }
        return 0;
    }

    private static int[][] knapsack(int[] v, int number) {
        int nv = v.length + 1;
        int[][] tabulation = new int[nv][number + 1];
        for (int i = 0; i < nv; i++) {
            tabulation[i][0] = 0;
        }
        for (int i = 0; i <= number; i++) {
            tabulation[0][i] = 0;
        }
        for (int i = 1; i < nv; i++) {
            for (int j = 1; j <= number; j++) {
                int max = tabulation[i - 1][j];
                if (j >= v[i - 1]) {
                    max = Math.max(max, v[i - 1]);
                }
                if (j - v[i - 1] >= 0) {
                    max = Math.max(max, tabulation[i - 1][j - v[i - 1]] + v[i - 1]);
                }
                tabulation[i][j] = max;
            }
        }
        /*System.out.print("\t");
        for (int i = 0; i <= number; i++) {
            System.out.print(i + "\t");
        }
        System.out.println("");
        for (int i = 0; i < nv; i++) {
            if (i > 0) {
                System.out.print(v[i - 1] + "\t");
            }
            for (int j = 0; j <= number; j++) {
                System.out.print(tabulation[i][j] + "\t");
            }
            System.out.println("");
        }*/
        return tabulation;
    }

    private static int partition3(int[] A) {
        int sum = 0;
        int average;
        for (int ints: A) {
            sum += ints;
        }
        average = sum/3;
        int[][] tabulation = knapsack(A, 2 * average);
        if (tabulation[A.length][2 * average] == 2 * average) {
            if (search(A, tabulation, A.length, 2 * average, average) == 1) {
                return 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }
        System.out.println(partition3(A));
    }
}

