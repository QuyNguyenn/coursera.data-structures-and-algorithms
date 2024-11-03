import java.util.Scanner;

public class ChangeProblem {

    static final int[] coins = new int[]{1, 5, 6};

    public static int minCoinNumber(int n, int[] coins) {
        int number = 0;
        int[] money = new int[n + 1];
        money[0] = 0;
        int m = coins.length;
        for (int i = 1; i <= n; i++) {
            money[i] = Integer.MAX_VALUE;
            for (int j = 0; j < m; j++) {
                if (i >= coins[j]) {
                    number = money[i - coins[j]] + 1;
                    if (number < money[i]) {
                        money[i] = number;
                    }
                }
            }
        }
        return money[n];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(minCoinNumber(n/1000, coins));
    }
}
