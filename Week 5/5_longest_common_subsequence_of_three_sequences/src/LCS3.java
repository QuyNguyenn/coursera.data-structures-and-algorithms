import java.util.*;

public class LCS3 {

    private static int[][][] editDistance(int[] a, int[] b, int[] c) {
        int xMax = a.length + 1;
        int yMax = b.length + 1;
        int zMax = c.length + 1;
        int[] edit = new int[8];
        int[][][] arr = new int[xMax][yMax][zMax];
        arr[0][0][0] = 0;
        for (int i = 1; i < xMax; i++) {
            arr[i][0][0] = arr[i - 1][0][0] + 1;
        }
        for (int i = 1; i < yMax; i++) {
            arr[0][i][0] = arr[0][i - 1][0] + 1;
        }
        for (int i = 1; i < zMax; i++) {
            arr[0][0][i] = arr[0][0][i - 1] + 1;
        }
        for (int i = 1; i < xMax; i++) {
            for (int j = 1; j < yMax; j++) {
                for (int k = 1; k < zMax; k++) {
                    edit[0] = arr[i][j - 1][k] + 1;
                    edit[1] = arr[i][j - 1][k - 1] + 1;
                    edit[2] = arr[i][j][k - 1] + 1;
                    edit[3] = arr[i - 1][j][k] + 1;
                    edit[4] = arr[i - 1][j][k - 1] + 1;
                    edit[5] = arr[i - 1][j - 1][k] + 1;
                    edit[6] = arr[i - 1][j - 1][k - 1] + 1;
                    edit[7] = arr[i - 1][j - 1][k - 1];
                    int min = edit[0];
                    if (a[i - 1] == b[j - 1] && a[i - 1] == c[k - 1]) {
                        for (int t = 1; t < 6; t++) {
                            min = Math.min(min, edit[t]);
                        }
                        arr[i][j][k] = Math.min(min, edit[7]);
                    }
                    else {
                        for (int t = 1; t < 7; t++) {
                            min = Math.min(min, edit[t]);
                        }
                        arr[i][j][k] = min;
                    }
                }
            }
        }
        return arr;
    }

    private static int lcs3(int[] a, int[] b, int[] c) {
        return editDistance(a, b, c)[a.length][b.length][c.length];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int an = scanner.nextInt();
        int[] a = new int[an];
        for (int i = 0; i < an; i++) {
            a[i] = scanner.nextInt();
        }
        int bn = scanner.nextInt();
        int[] b = new int[bn];
        for (int i = 0; i < bn; i++) {
            b[i] = scanner.nextInt();
        }
        int cn = scanner.nextInt();
        int[] c = new int[cn];
        for (int i = 0; i < cn; i++) {
            c[i] = scanner.nextInt();
        }
        System.out.println(lcs3(a, b, c));
    }
}

