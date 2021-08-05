import java.util.*;

public class CoveringSegments {

    private static void binaryInsertionSort(Segment[] segments) {
        int l, r, m;
        Segment max = segments[0];
        Segment tmp = segments[0];
        for (int i = 1; i < segments.length; i++) {
            max = segments[i];
            l = 0;
            r = i - 1;
            while (l <= r) {
                m = (l + r) / 2;
                if (segments[m].end < max.end) {
                    l = m + 1;
                }
                else {
                    r = m - 1;
                }
            }
            tmp = max;
            if (i - l >= 0) System.arraycopy(segments, l, segments, l + 1, i - l);
            segments[l] = tmp;
        }
    }

    private static int[] optimalPoints(Segment[] segments) {
        //write your code here
        binaryInsertionSort(segments);
        int[] points = new int[segments.length];
        int n = segments.length;
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < n) {
            j = i;
            while (j < n - 1 && segments[i].end >= segments[j + 1].start) {
                j++;
            }
            points[k] = segments[i].end;
            k++;
            i = j + 1;
        }
        return Arrays.copyOf(points, k);
    }

    private static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
