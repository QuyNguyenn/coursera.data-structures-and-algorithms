import java.util.Scanner;

public class Change {

    private static int getChange(int m) {
        int[] coin = new int[]{10, 5, 1};
        int cnt = 0;
        int i = 0;
        while (m > 0){
            while (m / coin[i] > 0){
                m -= coin[i];
                cnt++;
            }
            i++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));
    }
}

