import java.util.Scanner;

public class Fibonacci {
    private static long calc_fib(int n) {
        if (n <= 1)
            return n;

        return calc_fib(n - 1) + calc_fib(n - 2);
    }

    public static long calc_fibLoop(int n){
        if (n < 1){
            return n;
        }
        long F1 = 0;
        long F2 = 1;
        long tmp;
        for (int i = 2; i <= n; i++){
            tmp = F2;
            F2 = F1 + F2;
            F1 = tmp;
        }
        return F2;
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        System.out.println(calc_fibLoop(n));
    }
}
