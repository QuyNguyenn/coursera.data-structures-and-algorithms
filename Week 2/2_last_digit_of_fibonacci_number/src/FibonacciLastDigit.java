import java.math.BigInteger;
import java.util.*;

public class FibonacciLastDigit {
    private static int getFibonacciLastDigitNaive(int n) {
        if (n <= 1)
            return n;

        int previous = 0;
        int current  = 1;

        for (int i = 0; i < n - 1; ++i) {
            int tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        return current % 10;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int c = getFibonacciLastDigitNaive(n);
        System.out.println(c);
    }


    public static long fibonacciRecursion(int n){
        if (n <= 1)
            return 1;
        else
            return fibonacciRecursion(n - 1) + fibonacciRecursion(n - 2);
    }

    public static BigInteger fibonacciLoop(int n){
        BigInteger F1 = new BigInteger("0");
        BigInteger F2 = new BigInteger("1");
        BigInteger tmp = new BigInteger("0");
        BigInteger zero = new BigInteger("0");
        for (int i = 2; i <= n; i++){
            tmp = F2;
            F2 = F2.add(F1);
            F1 = tmp;
        }
        if (n == 0){
            return F1;
        }
        return F2;
    }

    public static BigInteger lastDigitFibonacciLoop(int n){
        BigInteger F1 = new BigInteger("0");
        BigInteger F2 = new BigInteger("1");
        BigInteger tmp = new BigInteger("0");
        for (int i = 2; i <= n; i++){
            tmp = F2;
            F2 = F2.add(F1).remainder(BigInteger.TEN);
            F1 = tmp;
        }
        if (n == 0){
            return F1;
        }
        return F2;
    }
}
