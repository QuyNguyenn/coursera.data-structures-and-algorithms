import java.util.*;
import java.io.*;

public class tree_height {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class TreeHeight {
        int n;
        int parent[];

        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = in.nextInt();
            }
        }

        int computeHeight() {
            // Replace this code with a faster implementation
            int maxHeight = 0;

            int arr[] = new int[n + 1];
            for (int i = 0; i < n + 1; i++) {
                arr[i] = 0;
            }
            for (int i = 0; i < n; i++) {
                arr[parent[i] + 1]++;
            }
            for (int i = 0; i < n + 1; i++) {
                if (arr[i] != 0) {
                    maxHeight++;
                };
            }
            for (int i = 0; i < n + 1; i++) {
                System.out.print(arr[i] + " ");;
            }
            return maxHeight;
        }
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new tree_height().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        TreeHeight tree = new TreeHeight();
        tree.read();
        System.out.println(tree.computeHeight());
    }
}

