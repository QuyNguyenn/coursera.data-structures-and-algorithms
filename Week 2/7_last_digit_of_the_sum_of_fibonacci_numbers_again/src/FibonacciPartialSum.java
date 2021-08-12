import java.util.*;

public class FibonacciPartialSum {
    private static List<Integer> getPeriod(int m) {
        List<Integer> period = new ArrayList<>();
        if (m == 1) {
            period.add(0);
            return period;
        }
        int previous = 0;
        int current = 1;
        period.add(0);
        do {
            period.add(current);
            int tmpPrevious = previous;
            previous = current;
            current = (current + tmpPrevious) % m;
        } while (current != 0 || previous != 1);
        return period;
    }

    private static long getFibonacciPartialSum(long from, long to) {
        List<Integer> period = getPeriod(10);
        int periodSize = period.size();
        long sum = 0;
        to = to - from / periodSize * periodSize;
        from = from - from / periodSize * periodSize;
        if (to >= periodSize) {
            long tmp = 0;
            for (int i = 0; i < periodSize; i++) {
                tmp += period.get(i);
            }
            sum += tmp * (to / periodSize - 1) % 10;
            sum += tmp;
        }
        for (int i = 0; i <= to % periodSize; i++) {
            sum += period.get(i);
        }
        for (int j = 0; j < from % periodSize; j++) {
            sum -= period.get(j);
        }
        return sum % 10;
    }

    private static long getFibonacciPartialSumNaive(long from, long to) {
        long sum = 0;

        long current = 0;
        long next  = 1;

        for (long i = 0; i <= to; ++i) {
            if (i >= from) {
                sum += current;
            }

            long new_current = next;
            next = next + current;
            current = new_current;
        }

        return sum % 10;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long from = scanner.nextLong();
        long to = scanner.nextLong();
        System.out.println(getFibonacciPartialSum(from, to));
    }
}

