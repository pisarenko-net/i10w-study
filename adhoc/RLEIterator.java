import java.util.Iterator;

/**
 * https://leetcode.com/problems/rle-iterator
 */
public class RLEIterator {
	private int[] source;
	private int index;
	private int count;

    public RLEIterator(int[] input) {
    	this.source = input;
    }
    
    public int next(int n) {
    	while (index < source.length) {
    		if ((count + n) > source[index]) {
    			n -= source[index] - count;
    			index += 2;
    			count = 0;
    		} else {
    			count += n;
    			return source[index + 1];
    		}
    	}

    	return -1;
    }

    public static void main(String[] args) {
    	RLEIterator rle = new RLEIterator(new int[]{3,8,0,9,2,5});
    	for (int i : new int[]{2,1,1,2}) {
    		System.out.println(rle.next(i));
    	}

    	rle = new RLEIterator(new int[]{923381016,843,898173122,924,540599925,391,705283400,275,811628709,850,895038968,590,949764874,580,450563107,660,996257840,917,793325084,82});
    	for (int i : new int[]{612783106,486444202,630147341,845077576,243035623,731489221,117134294,220460537,794582972,332536150,815913097,100607521,146358489,697670641,45234068,573866037,519323635,27431940,16279485,203970}) {
    		System.out.println(rle.next(i));
    	}
    }
}