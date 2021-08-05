import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(fibonacciLoop(n));
        //System.out.println(fibonacciRecursion(n));
    }

    public static long fibonacciRecursion(int n){
        if (n <= 1)
            return 1;
        else
            return fibonacciRecursion(n - 1) + fibonacciRecursion(n - 2);
    }

    public static long fibonacciLoop(int n){
        long F1 = 0;
        long F2 = 1;
        long tmp;
        for (int i = 2; i <= n; i++){
            tmp = F2;
            F2 = F1 + F2;
            F1 = tmp;
        }
        if (n == 0){
            return 0;
        }
        return F2;
    }
}
