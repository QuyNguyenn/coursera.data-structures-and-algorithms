import java.util.*;

public class LCM {
    private static long lcm_naive(int a, int b) {
        for (long l = 1; l <= (long) a * b; ++l)
            if (l % a == 0 && l % b == 0)
                return l;

        return (long) a * b;
    }

    public static long lcmCalculator(long a, long b){
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

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        System.out.println(lcmCalculator(a, b));
    }
}
