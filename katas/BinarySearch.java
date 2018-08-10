import java.util.Arrays;
import java.util.Scanner;
import edu.princeton.cs.algs4.StdRandom;

public class BinarySearch {
    public static int rank(int[] a, int key) {
        int lo = 0, hi = a.length - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key > a[mid]) lo = mid + 1;
            else if (key < a[mid]) hi = mid - 1;
            else return mid;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] a = new int[15];
        for (int i = 0; i < a.length; i++) a[i] = StdRandom.uniform(100);
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));

        Scanner scanner = new Scanner(System.in);
        do {
            int key = scanner.nextInt();
            System.out.println(rank(a, key));
        } while (scanner.hasNextLine());
    }
}