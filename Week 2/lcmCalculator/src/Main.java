import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        long b = scanner.nextLong();
        System.out.println(lcmCalculatorRecursion(a, b));
    }

    public static long lcmCalculatorRecursion(long a, long b){
        long tmp = a / euclidAlgorithmRecursion(a, b);
        return tmp * b;
    }

    public static long euclidAlgorithmRecursion(long a, long b){
        long m = Math.max(a, b);
        long n = Math.min(a, b);
        if (n == 0){
            return m;
        }
        return euclidAlgorithmRecursion(n, m % n);
    }
}
