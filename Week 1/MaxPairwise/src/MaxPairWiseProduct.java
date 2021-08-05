import java.util.Random;
import java.util.Scanner;

public class MaxPairWiseProduct {

    public static void main(String[] args) {
        /*while (true){
            Random random = new Random();
            int n = random.nextInt(100) % 1000 + 2;
            System.out.println(n);
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = random.nextInt(1000000);
                System.out.print(a[i] + " ");
            }
            System.out.print("\n");
            long result1 = MaxPairWiseProductFast(a);
            long result2 = MaxPairWiseProductBest(a);
            if (result1 != result2){
                System.out.println(result1 + " " + result2);
                System.out.println("Wrong Result");
                break;
            }
            else {
                System.out.println("OK");
            }
        }*/
        Random random = new Random();
        // Create scanner
        Scanner s = new Scanner(System.in);
        // Enter elements number
	    int n = s.nextInt();
        System.out.println(System.nanoTime());
	    // assert
	    assert n >= 2;
        // Enter array elements
	    int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            //a[i] = s.nextInt();
            a[i] = random.nextInt(100000);
        }
        // Print out max pairwise
        long result = MaxPairWiseProductBest(a);
        System.out.println(result);
        System.out.println(System.nanoTime());
    }

    public static long MaxPairWiseProductBest(int[] a){
        // Assume a[0] and a[1] is max pairwise
        int max1;
        int max2;
        int n = a.length;
        if (a[0] > a[1]){
            max1 = a[0];
            max2 = a[1];
        }
        else {
            max1 = a[1];
            max2 = a[0];
        }
        // Find max pairwise
        for (int i = 2; i < n; i++){
            if (a[i] > max1){
                max2 = max1;
                max1 = a[i];
            }
            else if(a[i] > max2)
            {
                max2 = a[i];
            }
        }
        return (long)max1 * max2;
    }

    public static long MaxPairWiseProductFast(int[] a){
        // Assume a[0] and a[1] is max pairwise
        int max1 = 0;
        int max2 = 1;
        int n = a.length;
        // Find max pairwise
        for (int i = 0; i < n; i++){
            if (a[i] > a[max1] && i != max2){
                max1 = i;
            }
        }
        for (int j = 0; j < n; j++){
            if (a[j] > a[max2] && j != max1){
                max2 = j;
            }
        }
        System.out.println(max1 + " " + max2);
        return (long)a[max1] * a[max2];
    }
}
