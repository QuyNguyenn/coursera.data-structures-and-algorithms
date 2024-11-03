import java.util.*;

class EditDistance {
    public static int EditDistance(String s, String t) {
        int ns = s.length() + 1;
        int nt = t.length() + 1;
        int deletion = 0;
        int insertion = 0;
        int substitution = 0;
        int match = 0;
        int[][] a = new int[ns][nt];
        a[0][0] = 0;
        for (int i = 1; i < ns; i++) {
            a[i][0] = a[i - 1][0] + 1;
        }
        for (int i = 1; i < nt; i++) {
            a[0][i] = a[0][i - 1] + 1;
        }
        for (int i = 1; i < ns; i++) {
            for (int j =1; j < nt; j++) {
                deletion = a[i][j - 1]  + 1;
                insertion = a[i - 1][j] + 1;
                substitution = a[i - 1][j - 1] + 1;
                match = a[i - 1][j - 1];
                if (s.charAt(i -1) == t.charAt(j - 1)) {
                    a[i][j] = Math.min(deletion, Math.min(insertion, match));
                }
                else {
                    a[i][j] = Math.min(deletion, Math.min(insertion, substitution));
                }
            }
        }
        /*for (int i = 0; i < ns; i++) {
            for (int j = 0; j < nt; j++) {
                System.out.print(a[i][j] + "\t");
            }
            System.out.println("");
        }*/
        return a[ns - 1][nt - 1];
    }
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);

        String s = scan.next();
        String t = scan.next();

        System.out.println(EditDistance(s, t));
    }

}
