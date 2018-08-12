import java.util.Scanner;

public class P53 {
    private static final int MASK = 0xFFFF;
    private static final int WORD_SIZE = 16;

    private long[] precomputed;

    public P53() {
        precomputed = new long[(int) Math.pow(2, WORD_SIZE)];
        for (int i = 0; i < precomputed.length; i++) {
            precomputed[i] = reverseDirectly(i);
        }
    }

    private static int reverseDirectly(int var) {
        for (int i = 0; i < WORD_SIZE / 2; i++) {
            var = swapBits(var, i, WORD_SIZE - i - 1);
        }
        return var;
    }

    private static int swapBits(int var, int i, int j) {
        if (((var >>> i) & 1) != ((var >>> j) & 1)) {
            var ^= (1 << i) | (1 << j);
        }
        return var;
    }

    public long reverse(long var) {
        return
            precomputed[(int)(var & MASK)] << (WORD_SIZE * 3) |
            precomputed[(int)((var >> WORD_SIZE) & MASK)] << (WORD_SIZE * 2) |
            precomputed[(int)((var >> (WORD_SIZE * 2)) & MASK)] << WORD_SIZE |
            precomputed[(int)((var >> (WORD_SIZE * 3)) & MASK)];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        P53 calc = new P53();

        do {
            long input = Long.parseLong(scanner.next(), 2);
            System.out.println(Long.toBinaryString(calc.reverse(input)));
        } while(scanner.hasNextLine());
    }
}