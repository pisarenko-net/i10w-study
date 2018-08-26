import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BinarySearch {
    public static int rank(int[] a, int key) {
        int lo = 0, hi = a.length - 1;

        while (hi >= lo) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] > key) hi = mid - 1;
            else if (a[mid] < key) lo = mid + 1;
            else return mid;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] a = new Random().ints(15, 0, 100).toArray();

        Arrays.sort(a);
        System.out.println(Arrays.toString(a));

        Scanner scanner = new Scanner(System.in);
        do {
            int key = scanner.nextInt();
            System.out.println(rank(a, key));
        } while (scanner.hasNextLine());
    }
}