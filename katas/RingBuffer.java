import java.util.Arrays;

public class RingBuffer<Item> {
	private Item[] array;
	private int N;
	private int offset;

	public RingBuffer(int size) {
		array = (Item[]) new Object[size];
	}

	public void push(Item item) {
		if (N == array.length) throw new IndexOutOfBoundsException("The buffer is full");
		array[offset] = item;
		offset = (offset + 1) % array.length;
		N++;
	}

	public Item pop() {
		if (N == 0) throw new IndexOutOfBoundsException("The buffer is empty");
		int index = (offset + (array.length - N)) % array.length;
		Item item = array[index];
		array[index] = null;
		N--;
		return item;
 	}

	public Item peek() {
		if (N == 0) throw new IndexOutOfBoundsException("The buffer is empty");
		int index = (offset + (array.length - N)) % array.length;
		return array[index];
	}

	public int size() {
		return array.length;
	}

	public int count() {
		return N;
	}

	public String toString() {
		return String.format("N=%d, [%s]", N, Arrays.toString(array));
	}

	public static void main(String[] args) {
	    RingBuffer<Integer> rb = new RingBuffer<>(5);
	    rb.push(5);
	    rb.push(13);
	    rb.push(9);
	    rb.push(45);
	    System.out.println(rb);
	    System.out.println("after one delete " + rb.pop());
	    System.out.println(rb);
	    System.out.println("peek: " + rb.peek());
	    System.out.println("after one delete " + rb.pop());
	    System.out.println(rb);
	    rb.push(81);
	    rb.push(22);
	    System.out.println(rb);
	    rb.push(1);
	    System.out.println(rb);
	    try {
	      rb.push(6);
	    } catch (IndexOutOfBoundsException e) {
	      e.printStackTrace();
	    }
	    System.out.println("after one delete " + rb.pop());
	    System.out.println(rb);
	}
}