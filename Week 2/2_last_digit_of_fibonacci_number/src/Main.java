import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        /*for (int i = 0; i < 100; i++){
            System.out.println(fibonacciLoop(i) + "  " + lastDigitFibonacciLoop(i));
        }*/
        System.out.println(lastDigitFibonacciLoop(n));
        //System.out.println(fibonacciRecursion(n));
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
