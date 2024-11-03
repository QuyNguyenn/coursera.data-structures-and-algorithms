import java.util.Scanner;

public class CarFueling {

    public static void main(String[] args) {
        // Enter distance, tank capacity, number gas station
        Scanner scanner = new Scanner(System.in);
        int dist = scanner.nextInt();
        int tank = scanner.nextInt();
        int n = scanner.nextInt();

        // Enter gas station position
        int[] stops = new int[n];
        for (int i = 0; i < n; i++) {
            stops[i] = scanner.nextInt();
        }

        // Print out minimum refill
        System.out.println(computeMinRefills(dist, tank, stops));
    }

    static int computeMinRefills(int dist, int tank, int[] stops) {
        int lastRefueling = 0;
        int currentRefueling = 0;
        int nextRefueling = 0;
        int numberRefueling = 0;
        int i = -1;
        int n = stops.length;

        // Greedy algorithm
        while (currentRefueling < dist){
            lastRefueling = currentRefueling;
            nextRefueling = (i + 1 < n) ? stops[i + 1] : dist;

            // Check the farthest gas station can refill
            while (i < n && nextRefueling - lastRefueling <= tank){
                i++;
                nextRefueling = (i + 1 < n) ? stops[i + 1] : dist;
            }

            // Check the current refill and counting
            currentRefueling = (i< n) ? stops[i] : dist;
            if (currentRefueling == lastRefueling){
                return -1;
            }
            if (currentRefueling < dist){
                numberRefueling++;
            }
        }
        return numberRefueling;
    }
}
