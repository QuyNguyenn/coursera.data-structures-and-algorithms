public class Main {

    public static void main(String[] args) {
        int n = 60;
        int[] roots = new int[n];
        int[] ranks = new  int[n];
        for (int i = 0; i < n; i++) {
            makeSet(i, roots, ranks);
        }
        for (int i = 0; i < 30; i++) {
            union(i, 2 * i, roots, ranks);
        }
        for (int i = 0; i < n; i++) {
            System.out.print(roots[i] + " ");
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print(ranks[i] + " ");
        }
    }

    public static void makeSet(int i, int[] roots, int[] ranks) {
        roots[i] = i;
        ranks[i] = 0;
    }

    public static int find(int i, int[] roots, int[] ranks) {
        if (roots[i] != i) {
            roots[i] = find(roots[i], roots, ranks);
        }
        return roots[i];
    }

    public static void union(int i, int j, int[] roots, int[] ranks) {
        int ri = find(i, roots, ranks);
        int rj = find(j, roots, ranks);
        if (ri == rj) {
            return;
        }
        if (ranks[ri] > ranks[rj]) {
            roots[rj] = ri;
        }
        else {
            roots[ri] = rj;
            if (ranks[ri] == ranks[rj]) {
                ranks[rj]++;
            }
        }
    }
}
