import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        //String result = sumSquareFibonacciRecursion(n).toString();
        //System.out.println(result.charAt(result.length()-1));
        System.out.println(sumLastSquareDigitFibonacci(n));
        //System.out.println(lastDigitFibonacciLoop(n + 2).subtract(BigInteger.ONE));
    }

    public static BigInteger sumLastSquareDigitFibonacci(long n){
        BigInteger F1 = new BigInteger("0");
        BigInteger F2 = new BigInteger("1");
        BigInteger tmp = new BigInteger("0");
        BigInteger sum = new BigInteger("1");
        for (long i = 2; i <= n; i++){
            tmp = F2;
            F2 = F2.add(F1).remainder(BigInteger.TEN);
            F1 = tmp;
            sum = sum.add(F2.multiply(F2)).remainder(BigInteger.TEN);
        }
        if (n == 0){
            return F1;
        }
        return sum;
    }
    
    public static BigInteger sumSquareFibonacciRecursion(int n){
        BigInteger result = new BigInteger("0");
        if (n < 1){
            result = fibonacciLoop(n);
            return result.multiply(result);
        }
        result = fibonacciLoop(n);
        result = result.multiply(result);
        return result.add(sumSquareFibonacciRecursion(n - 1));
    }

    public static BigInteger fibonacciLoop(int n){
        BigInteger F1 = new BigInteger("0");
        BigInteger F2 = new BigInteger("1");
        BigInteger tmp = new BigInteger("0");
        for (int i = 2; i <= n; i++){
            tmp = F2;
            F2 = F1.add(F2);
            F1 = tmp;
        }
        if (n == 0){
            return F1;
        }
        return F2;
    }

    public static BigInteger lastDigitFibonacciLoop(long n){
        BigInteger F1 = new BigInteger("0");
        BigInteger F2 = new BigInteger("1");
        BigInteger tmp = new BigInteger("0");
        for (long i = 2; i <= n; i++){
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
