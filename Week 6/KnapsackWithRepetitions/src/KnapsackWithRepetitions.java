import java.util.Scanner;

public class KnapsackWithRepetitions {

    private static int maxValueCalculator(int W, int[] w, int[] v) {
        int[] table = new int[W + 1];
        table[0] = 0;
        int nw = w.length;
        int max = 0;
        for (int i = 1; i < table.length; i++) {
            table[i] = 0;
            for (int j = 0; j < nw; j++) {
                if (i >= w[j]) {
                    max = table[i - w[j]] + v[j];
                }
                if (max > table[i]) {
                    table[i] = max;
                }
            }
        }
        for (int i = 0; i < table.length; i++) {
            System.out.print(i + "\t");
        }
        System.out.println("");
        for (int ints: table) {
            System.out.print(ints + "\t");
        }
        System.out.println("");
        return table[W];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W = scanner.nextInt();

        int itemNumber = scanner.nextInt();
        int[] w = new int[itemNumber];
        int[] v = new int[itemNumber];

        for (int i = 0; i < itemNumber; i++) {
            w[i] = scanner.nextInt();
            v[i] = scanner.nextInt();
        }

        System.out.println(maxValueCalculator(W, w, v));
    }
}
