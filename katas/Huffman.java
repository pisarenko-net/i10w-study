import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;

import lombok.AllArgsConstructor;

public class Huffman {
	private final static int R = 256;

	@AllArgsConstructor
	private static class Node implements Comparable<Node> {
		char ch;
		int freq;
		Node left, right;

		boolean isLeaf() {
			return (left == null) && (right == null);
		}

		public int compareTo(Node that) {
			return Integer.compare(this.freq, that.freq);
		}
	}

	public static void compress(String s) {
		int[] freq = new int[R];
		for (int i = 0; i < s.length(); i++) freq[s.charAt(i)]++;

		Node root = buildTrie(freq);
		writeTrie(root);

		List<Boolean>[] st = (List<Boolean>[]) new List[R];
		buildCode(st, root, new ArrayList<>());

		BinaryStdOut.write(s.length());
		for (int i = 0; i < s.length(); i++)
			for (boolean bit : st[s.charAt(i)]) BinaryStdOut.write(bit);
		BinaryStdOut.close();
	}

	private static Node buildTrie(int[] freq) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for (char c = 0; c < R; c++)
			if (freq[c] > 0)
				pq.add(new Node(c, freq[c], null, null));

		while (pq.size() > 1) {
			Node left = pq.remove();
			Node right = pq.remove();
			Node parent = new Node('\0', left.freq+right.freq, left, right);
			pq.add(parent);
		}
		return pq.remove();
	}

	private static void writeTrie(Node n) {
		if (n.isLeaf()) {
			BinaryStdOut.write(true);
			BinaryStdOut.write(n.ch, 8);
			return;
		}
		BinaryStdOut.write(false);
		writeTrie(n.left);
		writeTrie(n.right);
	}

	private static void buildCode(List<Boolean>[] st, Node n, List<Boolean> code) {
		if (!n.isLeaf()) {
			code.add(false);
			buildCode(st, n.left, code);
			code.remove(code.size()-1);
			code.add(true);
			buildCode(st, n.right, code);
			code.remove(code.size()-1);
		} else st[n.ch] = new ArrayList<>(code);
	}

	public static void expand() {
		Node root = readTrie();
		int length = BinaryStdIn.readInt();
		for (int i = 0; i < length; i++) {
			Node n = root;
			while (!n.isLeaf()) {
				if (BinaryStdIn.readBoolean()) n = n.right;
				else n = n.left;
			}
			BinaryStdOut.write(n.ch, 8);
		}
		BinaryStdOut.close();
	}

	private static Node readTrie() {
		boolean isLeaf = BinaryStdIn.readBoolean();
		if (isLeaf) return new Node(BinaryStdIn.readChar(), -1, null, null);
		else return new Node('\0', -1, readTrie(), readTrie());
	}

    public static void main(String[] args) {
        if (args[0].equals("-")) compress(BinaryStdIn.readString());
        else if (args[0].equals("+")) expand();
    }
}