import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /*while (true){
            Random random = new Random();
            long a = random.nextLong();
            long b = random.nextLong();
            a = (a < 0) ? a * -1 : a;
            b = (b < 0) ? b * -1 : b;
            System.out.println(a + " " + b);
            long result1 = euclidAlgorithmFast(a, b);
            long result2 = euclidAlgorithmRecursion(a, b);
            System.out.println(result1 + " " + result2);
            if (result1 != result2){
                System.out.println("WRONG");
                break;
            }
            System.out.println("OK");
        }*/

        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        long b = scanner.nextLong();
        System.out.println(euclidAlgorithmRecursion(a, b));
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
}
