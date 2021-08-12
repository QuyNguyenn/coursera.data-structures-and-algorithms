import java.util.Scanner;

public class PlacingParentheses {
    private static void MinAndMax(long[][] m, long[][] M, char[] ops, int i, int j) {
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        long[] tmp = new long[4];
        for (int k = 0; k < j - i; k++) {
            tmp[0] = eval(m[i][i + k], m[i + k + 1][j], ops[i + k]);
            tmp[1] = eval(M[i][i + k], M[i + k + 1][j], ops[i + k]);
            tmp[2] = eval(m[i][i + k], M[i + k + 1][j], ops[i + k]);
            tmp[3] = eval(M[i][i + k], m[i + k + 1][j], ops[i + k]);
            for (long along: tmp) {
                min = Math.min(min, along);
                max = Math.max(max, along);
            }
        }
        m[i][j] = min;
        M[i][j] = max;
    }

    private static long getMaximValue(String exp) {
        int n = (exp.length() - 1) / 2 + 1;
        long[][] m = new long[n][n];
        long[][] M = new long[n][n];
        char[] ops = new char[n - 1];
        for (int i = 0; i < n; i++) {
            m[i][i] = exp.charAt(2 * i) - 48;
            M[i][i] = m[i][i];
        }
        for (int i = 0; i < n - 1; i++) {
            ops[i] = exp.charAt(2 * i + 1);
        }
        for (int s = 1; s < n; s++) {
            for (int i = 0; i < n - s; i++) {
                int j = i + s;
                MinAndMax(m, M, ops, i, j);
            }
        }
        /*for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(M[i][j] + "\t");
            }
            System.out.println("");
        }*/
        return M[0][n - 1];
    }

    private static long eval(long a, long b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else if (op == '*') {
            return a * b;
        } else {
            assert false;
            return 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String exp = scanner.next();
        System.out.println(getMaximValue(exp));
    }
}

