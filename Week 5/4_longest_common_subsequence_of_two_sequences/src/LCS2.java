import java.util.*;

public class LCS2 {

    private static int[][] editDistance(int[] a, int[] b) {
        int na = a.length + 1;
        int nb = b.length + 1;
        int insertion, deletion, substitution, match;
        int[][] array = new int[na][nb];
        array[0][0] = 0;
        for (int i = 1; i < na; i++) {
            array[i][0] = array[i - 1][0] + 1;
        }
        for (int i = 1; i < nb; i++) {
            array[0][i] = array[0][i - 1] + 1;
        }
        for (int i = 1; i < na; i++) {
            for (int j = 1; j < nb; j++) {
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

    private static int lcs2(int[] a, int[] b, int[][] array, int i, int j) {
        if (i == 0 && j == 0) {
            return 0;
        }
        int lsc1 = 0;
        int lsc2 = 0;
        int lsc3 = 0;
        int lsc4 = 0;
        if (i > 0 && j > 0 && a[i - 1] == b[j - 1] && array[i][j] == array[i - 1][j - 1]) {
            lsc1 = 1 + lcs2(a, b, array, i - 1, j - 1);
        }
        if (i > 0 && j > 0 && a[i - 1] != b[j - 1] && array[i][j] == array[i - 1][j - 1] + 1) {
            lsc2 = lcs2(a, b, array, i - 1, j - 1);
        }
        if (i > 0 && array[i][j] == array[i - 1][j] + 1) {
            lsc3 = lcs2(a, b, array, i - 1, j);
        }
        if (j > 0 && array[i][j] == array[i][j - 1] + 1) {
            lsc4 = lcs2(a, b, array, i, j - 1);
        }
        return Math.max(lsc1, Math.max(lsc2, Math.max(lsc3, lsc4)));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
        }

        /*int[] a = {2, 7 ,8, 3};
        int[] b = {5, 2, 8, 7};

        for (int ints: a) {
            System.out.print(ints + "\t");
        }
        System.out.println("");
        for (int ints: b) {
            System.out.print(ints + "\t");
        }
        System.out.println("");
        System.out.println("");*/

        int[][] editDistance = editDistance(a, b);
        System.out.println(lcs2(a, b, editDistance, a.length, b.length));
    }
}

