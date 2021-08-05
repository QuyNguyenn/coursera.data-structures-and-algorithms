import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        float[] a = new float[n];
        for (int i = 0; i < n; i++){
            a[i] = scanner.nextFloat();
        }
        System.out.println(minGroupingChildren(a));
    }

    public static int minGroupingChildren(float[] a){
        int n = a.length;
        int lastChildren = 0;
        int currentChildren = 0;
        int numGroup = 1;
        while (currentChildren < n){
            lastChildren = currentChildren;
            while (currentChildren < n - 1 && a[currentChildren + 1] - a[lastChildren] <= 1){
                currentChildren++;
            }
            if (currentChildren == n-1){
                return numGroup;
            }
            numGroup++;
            currentChildren++;
        }
        return numGroup;
    }
}
