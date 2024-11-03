import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        n = scanner.nextInt();

        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int m;
        m = scanner.nextInt();

        new queue(n, m, arr).solve();
    }
}

class queue {

    queue(int n, int m, int[] arr) {
        this.n = n;
        this.m = m;
        this.arr = arr;
        this.max = new int[n];
    }

    public void solve() {
        max[0] = arr[0];
        int max2 = -1;
        int index1 = 0;
        int index2 = 1;

        for (int i = 1; i < n; i++) {
            int value = arr[i];
            if (value >= max[i - 1]) {
                max[i] = value;
                index1 = i;
            }
            else {
                if (i - index1 >= m) {
                    int j = index2 > index1 ? index2 : index1 + 1;
                    max2 = arr[j];
                    while (j <= i) {
                        if (max2 <= arr[j]) {
                            max2 = arr[j];
                            index2 = j;
                        }
                        j++;
                    }

                    max[i] = max2;
                    index1 = index2;

                    index2++;
                }
                else {
                    max[i] = (max[i - 1]);
                }
            }
        }

        for (int i = m - 1; i < n; i++) {
            System.out.print(max[i] + " ");
        }
    }

    int n;
    int m;
    int max[];
    int arr[];
}
