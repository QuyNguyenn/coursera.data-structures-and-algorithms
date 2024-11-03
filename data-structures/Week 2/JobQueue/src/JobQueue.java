import java.io.*;
import java.util.List;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        // TODO: replace this code with a faster algorithm.
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        long[] nextFreeTime = new long[numWorkers];
        int[] workerIndex = new int[numWorkers];
        int usedWorker = 0;
        for (int i = 0; i < numWorkers; i++) {
            workerIndex[i] = i;
            nextFreeTime[i] = 0;
        }
        for (int i = 0; i < jobs.length; i++) {
            int duration = jobs[i];
            assignedWorker[i] = workerIndex[0];
            startTime[i] = nextFreeTime[0];
            nextFreeTime[0] += duration;
            shiftDown(0, nextFreeTime, workerIndex);
//            for (int j = 0; j < numWorkers; j++) {
//                System.out.print(nextFreeTime[j] + "\t");
//            }
//            System.out.println();
//            for (int j = 0; j < numWorkers; j++) {
//                System.out.print(workerIndex[j] + "\t");
//            }
//            System.out.println();
//            System.out.println("-----------------------------------");
        }
    }

    private int parent(int i) {
        return (i - 1)/2;
    }

    private int leftChild(int i) {
        return  2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private void shiftDown(int i, long[] nextFreeTime, int[] workerIndex) {
        int minIndex = i;
        int l = leftChild(i);
        int r = rightChild(i);
        if (l < nextFreeTime.length) {
            if (nextFreeTime[l] < nextFreeTime[minIndex]) {
                minIndex = l;
            }
            else if (nextFreeTime[l] == nextFreeTime[minIndex]) {
                minIndex = workerIndex[l] < workerIndex[minIndex] ? l : minIndex;
            }
        }
        if (r < nextFreeTime.length) {
            if (nextFreeTime[r] < nextFreeTime[minIndex]) {
                minIndex = r;
            }
            else if (nextFreeTime[r] == nextFreeTime[minIndex]) {
                minIndex = workerIndex[r] < workerIndex[minIndex] ? r : minIndex;
            }
        }

        if (minIndex != i) {
            long tmp = nextFreeTime[i];
            nextFreeTime[i] = nextFreeTime[(int) minIndex];
            nextFreeTime[minIndex] = tmp;

            int tmtIndex = workerIndex[i];
            workerIndex[i] = workerIndex[minIndex];
            workerIndex[minIndex] = tmtIndex;

            shiftDown(minIndex, nextFreeTime, workerIndex);
        }
    }

    private void shiftUp(int i, long[] nextFreeTime, int[] workerIndex) {
        int pi = parent(i);
        while (pi >= 0 && (nextFreeTime[pi] > nextFreeTime[i] || (nextFreeTime[pi] == nextFreeTime[i] && workerIndex[pi] > workerIndex[i]))) {
            long tmp = nextFreeTime[i];
            nextFreeTime[i] = nextFreeTime[pi];
            nextFreeTime[pi] = tmp;

            int tmtIndex = workerIndex[i];
            workerIndex[i] = workerIndex[pi];
            workerIndex[pi] = tmtIndex;
        }
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
