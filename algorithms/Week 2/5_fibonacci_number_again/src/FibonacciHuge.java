import java.util.*;

public class FibonacciHuge {
    private static List<Long> getPeriod(long m) {
        List<Long> period = new ArrayList<>();
        if (m == 1) {
            period.add(0L);
            return period;
        }
        long previous = 0;
        long current  = 1 % m;
        period.add(0L);
        period.add(current);
        while (true){
            long tmp_previous = previous;
            previous = current;
            current = (tmp_previous + current) % m;
            if (current == 0 && previous == 1) {
                break;
            }
            period.add(current);
        }
        return period;
    }

    private static long getFibonacciHuge(long n, long m) {
        List<Long> period = getPeriod(m);
        long index = n % period.size();
        return period.get((int) index);
    }

    private static long getFibonacciHugeNaive(long n, long m) {
        if (n <= 1)
            return n;

        long previous = 0;
        long current  = 1;

        for (long i = 0; i < n - 1; ++i) {
            long tmp_previous = previous;
            previous = current;
            current = tmp_previous + current;
        }

        return current % m;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacciHuge(n, m));
    }
}

