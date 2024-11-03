import java.util.*;

public class PrimitiveCalculator {
    private static List<Integer> optimal_sequence(int n) {
        List<Integer> sequence = new ArrayList<Integer>();
        int[] a = new int[n + 1];
        a[0] = 0;
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.MAX_VALUE;
            if (i % 3 == 0) {
                a[i] = Math.min(a[i], a[i / 3] + 1);
            }
            if (i % 2 == 0) {
                a[i] = Math.min(a[i], a[i / 2] + 1);
            }
            a[i] = Math.min(a[i], a[i - 1] + 1);
        }
        while (n >= 1) {
            sequence.add(n);
            if (n % 3 == 0 && a[n] == a[n / 3] + 1) {
                n /= 3;
            } else if (n % 2 == 0 && a[n] == a[n / 2] + 1) {
                n /= 2;
            } else {
                n -= 1;
            }
        }
        Collections.reverse(sequence);
        return sequence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (Integer x : sequence) {
            System.out.print(x + " ");
        }
    }
}

