import java.util.*;

public class Knapsack {
    static int optimalWeight(int W, int[] w) {
        int iMax = w.length + 1;
        int jMax = W + 1;
        int[][] result = new int[iMax][jMax];

        for (int i = 0; i < iMax; i++) {
            result[i][0] = 0;
        }
        for (int j = 0; j < jMax; j++) {
            result[0][j] = 0;
        }

        for (int i = 1; i < iMax; i++) {
            for (int j = 1; j < jMax; j++) {
                int max = result[i - 1][j];
                if (j >= w[i - 1]) {
                    max = Math.max(max, w[i - 1]);
                }
                if (j - w[i - 1] >= 0) {
                    max = Math.max(max, result[i - 1][j - w[i - 1]] + w[i - 1]);
                }
                result[i][j] = max;
            }
        }
        return result[iMax - 1][jMax - 1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

