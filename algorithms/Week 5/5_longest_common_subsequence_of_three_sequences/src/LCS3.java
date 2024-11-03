import java.util.*;

public class LCS3 {

    private static int[][] editDistance2(long[] a, long[] b) {
        int xMax = a.length + 1;
        int yMax = b.length + 1;
        int insertion, deletion, substitution, match;
        int[][] array = new int[xMax][yMax];
        array[0][0] = 0;
        for (int i = 1; i < xMax; i++) {
            array[i][0] = array[i - 1][0] + 1;
        }
        for (int i = 1; i < yMax; i++) {
            array[0][i] = array[0][i - 1] + 1;
        }
        for (int i = 1; i < xMax; i++) {
            for (int j = 1; j < yMax; j++) {
                insertion = array[i - 1][j] + 1;
                deletion = array[i][j - 1] + 1;
                substitution = array[i - 1][j - 1] + 1;
                match = array[i - 1][j - 1];
                if (a[i - 1] == b[j - 1])
                {
                    array[i][j] = Math.min(insertion, Math.min(deletion, match));
                }
                else {
                    array[i][j] = Math.min(insertion, Math.min(deletion, substitution));
                }
            }
        }
        /*for (int i = 0; i < na; i++) {
            for (int j = 0; j< nb; j++) {
                System.out.print(array[i][j] + "\t");
            }
            System.out.println("");
        }*/
        return array;
    }

    private static int[][][] editDistance3(long[] a, long[] b, long[] c) {
        int xMax = a.length + 1;
        int yMax = b.length + 1;
        int zMax = c.length + 1;
        int[] edit = new int[8];
        int[][] tmp;
        int[][][] arr = new int[xMax][yMax][zMax];
        tmp = editDistance2(a, b);
        for (int i = 0; i < xMax; i++) {
            for (int j = 0; j < yMax; j++) {
                arr[i][j][0] = tmp[i][j];
            }
        }
        tmp = editDistance2(a, c);
        for (int i = 1; i < xMax; i++) {
            for (int j = 0; j < zMax; j++) {
                arr[i][0][j] = tmp[i][j];
            }
        }
        tmp = editDistance2(b, c);
        for (int i = 1; i < yMax; i++) {
            for (int j = 0; j < zMax; j++) {
                arr[0][i][j] = tmp[i][j];
            }
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

    private static int lcs3(long[] a, long[] b, long[] c, int[][][] edit, int i, int j, int k) {
        if (i == 0 && j == 0 && k == 0) {
            return 0;
        }
        int[] ls = {0, 0, 0, 0, 0, 0, 0, 0};

        // Match
        if (i > 0 && j > 0 && k > 0 && a[i - 1] == b[j - 1] && a[i - 1] == c[k - 1] && edit[i][j][k] == edit[i - 1][j - 1][k - 1]) {
            ls[0] = 1 + lcs3(a, b, c, edit, i - 1, j - 1, k - 1);
        }

        // Substitution
        if (i > 0 && j > 0 && k > 0 && (a[i - 1] != b[j - 1] || a[i - 1] != c[k - 1]) && edit[i][j][k] == edit[i - 1][j - 1][k - 1] + 1) {
            ls[1] = lcs3(a, b, c, edit, i - 1, j - 1, k - 1);
        }

        //
        if (j > 0 && edit[i][j][k] == edit[i][j - 1][k] + 1) {
            ls[2] = lcs3(a, b, c, edit, i, j - 1, k);
        }
        if (j > 0 && k > 0 && edit[i][j][k] == edit[i][j - 1][k - 1] + 1) {
            ls[3] = lcs3(a, b, c, edit, i, j - 1, k - 1);
        }
        if (k > 0 && edit[i][j][k] == edit[i][j][k - 1] + 1) {
            ls[4] = lcs3(a, b, c, edit, i, j, k - 1);
        }
        if (i > 0 && edit[i][j][k] == edit[i - 1][j][k] + 1) {
            ls[5] = lcs3(a, b, c, edit, i - 1, j, k);
        }
        if (i > 0 && k > 0 && edit[i][j][k] == edit[i - 1][j][k - 1] + 1) {
            ls[6] = lcs3(a, b, c, edit, i - 1, j, k - 1);
        }
        if (i > 0 && j > 0 && edit[i][j][k] == edit[i - 1][j - 1][k] + 1) {
            ls[7] = lcs3(a, b, c, edit, i - 1, j - 1, k);
        }

        int max = ls[0];
        for (int ints: ls) {
            max = Math.max(max, ints);
        }
        return max;
    }

    private static int lcs3(long[] a, long[] b, long[] c) {
        int na = a.length + 1;
        int nb = b.length + 1;
        int nc = c.length + 1;
        int[][][] arr = new int[na][nb][nc];
        int[] ar = {0, 0, 0, 0, 0, 0};
        for (int i = 0; i < na; i++) {
            for (int j = 0; j < nb; j++) {
                for (int k = 0; k < nc; k++) {
                    if (i == 0 || j == 0 || k == 0) {
                        arr[i][j][k] = 0;
                    }
                    else {
                        if (a[i - 1] == b[j - 1] && a[i - 1] == c[k - 1]) {
                            arr[i][j][k] = arr[i - 1][j - 1][k - 1] + 1;
                        }
                        else {
                            ar[0] = arr[i][j - 1][k];
                            ar[1] = arr[i][j - 1][k - 1];
                            ar[2] = arr[i][j][k - 1];
                            ar[3] = arr[i - 1][j][k];
                            ar[4] = arr[i - 1][j][k - 1];
                            ar[5] = arr[i - 1][j - 1][k];
                            int max = ar[0];
                            for (int ints: ar) {
                                max = Math.max(max, ints);
                            }
                            arr[i][j][k] = max;
                        }
                    }

                }
            }
        }
        return arr[na - 1][nb - 1][nc - 1];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int an = scanner.nextInt();
        long[] a = new long[an];
        for (int i = 0; i < an; i++) {
            a[i] = scanner.nextInt();
        }
        int bn = scanner.nextInt();
        long[] b = new long[bn];
        for (int i = 0; i < bn; i++) {
            b[i] = scanner.nextInt();
        }
        int cn = scanner.nextInt();
        long[] c = new long[cn];
        for (int i = 0; i < cn; i++) {
            c[i] = scanner.nextInt();
        }

        //int[][][] edit = editDistance3(a, b, c);
        //System.out.println(lcs3(a, b, c, edit, an, bn, cn));
        System.out.println(lcs3(a, b, c));
    }
}

