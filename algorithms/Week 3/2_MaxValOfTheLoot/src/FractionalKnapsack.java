import java.util.Random;
import java.util.Scanner;

public class FractionalKnapsack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        inputValueSort(weights, values);
        System.out.println(getOptimalValue(capacity, values, weights));
    }

    // By me
    public static void inputValueSort(int[] w, int[] v){
        int n = w.length;
        double[] ratio = new double[n];
        int l = 0;
        int r = 0;
        int middle = 0;
        double tmp1 = 0;
        int tmp2 = 0;
        int tmp3 = 0;
        for (int i = 0; i < n; i++){
            ratio[i] = (double) v[i] / w[i];
        }
        for (int i = 1; i < n; i++){
            l = 0;
            r = i - 1;
            while (l <= r){
                middle = (r + l) / 2;
                if (ratio[middle] > ratio[i]){
                    l = middle + 1;
                }
                else {
                    r = middle - 1;
                }
            }
            tmp1 = ratio[i];
            tmp2 = v[i];
            tmp3 = w[i];
            for (int j = i; j > l; j--){
                ratio[j] = ratio[j - 1];
                v[j] = v[j - 1];
                w[j] = w[j - 1];
            }
            ratio[l] = tmp1;
            v[l] = tmp2;
            w[l] = tmp3;
        }
    }

    // By me
    public static void binaryInsertion(float[] a, int index, int l, int r){
        int middle = 0;
        float tmp = 0;
        while (l <= r){
            middle = (r + l) / 2;
            if (a[middle] < a[index]){
                l = middle + 1;
            }
            else {
                r = middle - 1;
            }
        }
        tmp = a[index];
        for (int j = index; j > l; j--){
            a[j] = a[j - 1];
        }
        a[l] = tmp;
    }

    // By me
    public static void binaryInsertionSort(float[] a){
        int n = a.length;;
        int l = 0;
        int r = 0;

        for (int i = 1; i < n; i++){
            l = 0;
            r = i - 1;
            binaryInsertion(a, i, l, r);
        }
    }

    private static double getOptimalValue(int capacity, int[] values, int[] weights) {
        double value = 0;
        int n = weights.length;
        for (int i = 0; i < n; i++){
            double a = Math.min(weights[i], capacity);
            capacity -= a;
            value += a / weights[i] * values[i];
            if (capacity == 0){
                return value;
            }
        }
        return value;
    }
}
