import java.util.*;

public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {
        List<Integer> summands = new ArrayList<Integer>();
        //write your code here
        int sum = 0;
        int i = 0;
        if (n < 3) {
            summands.add(n);
        }
        else {
            while (sum < n) {
                if (sum + 2 * (i + 1) + 1 < n) {
                    i++;
                    summands.add(i);
                    sum += i;
                }
                else if (sum + 2 * (i + 1) + 1 > n) {
                    summands.add(n - sum);
                    sum = n;
                }
                else {
                    summands.add(i += 1);
                    summands.add(i += 1);
                    sum = n;
                }
            }
        }
        return summands;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

