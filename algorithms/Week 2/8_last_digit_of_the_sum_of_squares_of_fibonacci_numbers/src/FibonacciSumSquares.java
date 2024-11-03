import java.util.*;

public class FibonacciSumSquares {
    private static List<Long> getPeriod(long m) {
        List<Long> period = new ArrayList<>();
        if (m <= 1) {
            period.add(0L);
            return period;
        }
        long previous = 0;
        long current = 1;
        period.add(0L);
        do {
            period.add(current);
            long tmpPrevious = previous;
            previous = current;
            current = (current + tmpPrevious) % m;
        } while (current != 0 || previous != 1);
        return period;
    }

    private static long getFibonacciSumSquares(long n) {
        List<Long> period = getPeriod(10L);
        int periodSize = period.size();
        Long sum = 0L;
        if (n / periodSize > 0) {
            long tmp = 0;
            for (int i = 0; i < periodSize; i++) {
                tmp += (period.get(i) * period.get(i)) % 10;
            }
            sum += n / periodSize * tmp % 10;
        }
        for (int i = 0; i <= n % periodSize; i++) {
            sum += period.get(i) * period.get(i) % 10;
        }
        return sum % 10;
    }

    private static long getFibonacciSumSquaresNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;
        long sum      = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
            sum += current * current;
        }

        return sum % 10;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long s = getFibonacciSumSquares(n);
        System.out.println(s);
    }
}
