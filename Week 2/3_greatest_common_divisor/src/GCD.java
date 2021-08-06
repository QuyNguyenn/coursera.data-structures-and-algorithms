import java.util.*;

public class GCD {
    private static int gcd_naive(int a, int b) {
        int current_gcd = 1;
        for(int d = 2; d <= a && d <= b; ++d) {
            if (a % d == 0 && b % d == 0) {
                if (d > current_gcd) {
                    current_gcd = d;
                }
            }
        }

        return current_gcd;
    }

    public static long euclidAlgorithmRecursion(long a, long b){
        long m = Math.max(a, b);
        long n = Math.min(a, b);
        if (n == 0){
            return m;
        }
        return euclidAlgorithmRecursion(n, m % n);
    }

    public static long euclidAlgorithmFast(long a, long b){
        int i = 1;
        long m = Math.max(a, b);
        long n = Math.min(a, b);
        while (n > 0){
            m = m % n;
            if (m == 0){
                return n;
            }
            n = n % m;
        }
        return m;
    }

    public static long euclidAlgorithmSlow(long a, long b){
        long m;
        long n;
        long tmp;
        m = Math.max(a, b);
        n = Math.min(a, b);
        while (m != n){
            tmp = m;
            m = Math.max(m - n, n);
            n = Math.min(tmp - n, n);
        }
        return m;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        System.out.println(euclidAlgorithmRecursion((long) a, (long) b));
    }
}
