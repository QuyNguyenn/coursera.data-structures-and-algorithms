import java.util.*;

public class FibonacciSumLastDigit {
    private static List<Long> getPeriod(long m) {
        List<Long> period = new ArrayList<>();
        if (m == 1) {
            period.add(0L);
            return period;
        }
        int previous = 0;
        int current = 1;
        period.add(0L);
        do {
            period.add((long) current);
            int tmpPrevious = previous;
            previous = current;
            current = (current + tmpPrevious) % (int) m;
        } while (current != 0 || previous != 1);
        return period;
    }

    private static long getFibonacciSum(long n) {
        if (n <= 1)
            return n;
        List<Long> period = getPeriod(10);
        int periodSize = period.size();
        long sumLastDigit = 0;
        if (n >= periodSize) {
            long sum = 0;
            for (int i = 0; i < periodSize; i++) {
                sum += period.get(i);
            }
            sumLastDigit = sum * (n / periodSize);
        }
        for (int i = 0; i < (n + 1) % periodSize; i++) {
            sumLastDigit += period.get(i);
        }

        return sumLastDigit % 10;
    }

    private static long getFibonacciSumNaive(long n) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;
        long sum      = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
            sum += current;
        }

        return sum % 10;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long s = getFibonacciSum(n);
        System.out.println(s);
    }
}
