import java.util.Scanner;

public class KnapsackWithoutRepetitions {
    private static int maxValueCalculator(int W, int[] w, int[] v) {
        int nw = w.length;
        int[][] tabulation = new int[nw + 1][W + 1];
        int max;
        for (int i = 0; i <= nw; i++) {
            tabulation[i][0] = 0;
        }
        for (int i = 0; i <= W; i++) {
            tabulation[0][i] = 0;
        }
        for (int i = 1; i <= nw; i++) {
            for (int j = 1; j <= W; j++) {
                max = tabulation[i - 1][j];
                if (j >= w[i - 1]) {
                    max = Math.max(max, v[i - 1]);
                }
                if (j - w[i - 1] >= 0) {
                    max = Math.max(max, tabulation[i - 1][j - w[i - 1]] + v[i - 1]);
                }
                tabulation[i][j] = max;
            }
        }
        /*System.out.print("\t");
        for (int i = 0; i <= W; i++) {
            System.out.print(i + "\t");
        }
        System.out.println("");
        for (int i = 0; i <= nw; i++) {
            if (i > 0) {
                System.out.print(w[i - 1] + "\t");
            }
            else {
                System.out.print("0\t");
            }
            for (int j = 0; j <= W; j++) {
                System.out.print(tabulation[i][j] + "\t");
            }
            System.out.println("");
        }*/
        return tabulation[nw][W];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W = scanner.nextInt();

        int nw = scanner.nextInt();
        int[] w = new int[nw];
        int[] v = new int[nw];
        for (int i = 0; i < nw; i++) {
            w[i] = scanner.nextInt();
            v[i] = scanner.nextInt();
        }

        System.out.println(maxValueCalculator(W, w, v));
    }
}
