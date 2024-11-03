import java.util.Scanner;

public class StringComparison {

    public static int[][] editDistance(String s1, String s2){
        int n1 = s1.length() + 1;
        int n2 = s2.length() + 1;
        int insertion = 0;
        int deletion = 0;
        int match = 0;
        int mismatch = 0;
        int[][] a = new int[n1][n2];
        a[0][0] = 0;
        for (int i = 1; i < n1; i++) {
            a[i][0] = a[i - 1][0] + 1;
        }
        for (int i = 1; i < n2; i++) {
            a[0][i] = a[0][i - 1] + 1;
        }
        for (int i = 1; i < n1; i++) {
            for (int j = 1; j < n2; j++) {
                insertion = a[i][j - 1] + 1;
                deletion = a[i - 1][j] + 1;
                match = a[i - 1][j - 1];
                mismatch = a[i - 1][j - 1] + 1;
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    a[i][j] = Math.min(Math.min(insertion, deletion), match);
                }
                else {
                    a[i][j] = Math.min(Math.min(insertion, deletion), mismatch);
                }
            }
        }
        return a;
    }

    public static String[] optimalAlignment(String s1, String s2, int[][] a, int i, int j) {
        String[] s = new String[2];
        String[] tmp = new String[2];
        s[0] = "";
        s[1] = "";
        if (i == 0 && j == 0) {
            return s;
        }
        if (i > 0 && j > 0 && a[i][j] == a[i - 1][j - 1] && s1.charAt(i - 1) == s2.charAt(j - 1)) {
            tmp = optimalAlignment(s1, s2, a, i - 1, j - 1);
            s[0] = tmp[0] + s1.charAt(i - 1);
            s[1] = tmp[1] + s2.charAt(j - 1);
            return s;
        }
        else if (i > 0 && j > 0 && a[i][j] == a[i - 1][j - 1] + 1 && s1.charAt(i - 1) != s2.charAt(j - 1)) {
            tmp = optimalAlignment(s1, s2, a, i - 1, j - 1);
            s[0] = tmp[0] + s1.charAt(i - 1);
            s[1] = tmp[1] + s2.charAt(j - 1);
            return s;
        }
        else if (i > 0 && a[i][j] == a[i - 1][j] + 1) {
            tmp = optimalAlignment(s1, s2, a, i - 1, j);
            s[0] = tmp[0] + s1.charAt(i - 1);
            s[1] = tmp[1] + "-";
            return s;
        }
        else if (j > 0 && a[i][j] == a[i][j - 1] + 1) {
            tmp = optimalAlignment(s1, s2, a, i, j - 1);
            s[0] = tmp[0] + "-";
            s[1] = tmp[1] + s2.charAt(j - 1);
            return s;
        }
        return s;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = "EDITING";
        String s2 = "DISTANCE";
        int[][] a = editDistance(s1, s2);
        for (int[] ints : a) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(ints[j] + "\t");
            }
            System.out.println("");
        }
        String[] s = optimalAlignment(s1, s2, a, s1.length(), s2.length());
        System.out.println(s[0]);
        System.out.println(s[1]);
    }
}
