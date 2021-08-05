import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        //System.out.println(sumLastDigitFibonacciRecursion(n));
        System.out.println(sumLastDigitFibonacci(n));
    }

    public static BigInteger sumFibonacci(int n){
        if (n < 1){
            return fibonacciLoop(n);
        }
        return fibonacciLoop(n).add(sumFibonacci(n - 1));
    }

    public static BigInteger sumLastDigitFibonacci(int n){
        BigInteger F1 = new BigInteger("0");
        BigInteger F2 = new BigInteger("1");
        BigInteger tmp = new BigInteger("0");
        BigInteger sum = new BigInteger("1");
        for (int i = 2; i <= n; i++){
            tmp = F2;
            F2 = F2.add(F1).remainder(BigInteger.TEN);
            F1 = tmp;
            sum = sum.add(F2).remainder(BigInteger.TEN);
        }
        if (n == 0){
            return F1;
        }
        return sum;
    }

    public static BigInteger sumLastDigitFibonacciRecursion(int n){
        if (n < 1){
            return fibonacciLoop(n);
        }
        return lastDigitFibonacciLoop(n).add(sumLastDigitFibonacciRecursion(n - 1)).remainder(BigInteger.TEN);
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
