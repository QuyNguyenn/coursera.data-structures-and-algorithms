import java.util.Scanner;

public class ChangeDP {
    private static int getChange(int m, int[] coins) {
        int[] a = new int[m + 1];
        a[0] = 0;
        for (int i = 1; i <= m; i++) {
            a[i] = Integer.MAX_VALUE;
            for (int j = 0; j < coins.length; j++) {
                if (a[i] >= coins[j]) {
                    a[i] = Math.min(a[i], a[i - coins[j]] + 1);
                }
            }
        }
        return a[m];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int[] coins = {1, 3, 4};
        System.out.println(getChange(m, coins));
    }
}

