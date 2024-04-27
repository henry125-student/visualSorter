package sort;

//import java.util.*;
import main.*;
import movement.*;

public class RadixLSDSorter extends Sorter{

	private int base;
	
	public RadixLSDSorter(World world, int base) {
		super(world);
		this.base = base;
	}

	public String getName() {
		return "Radix LSD Sort (Base "+base+")";
	}

	private int[] array;
	public synchronized void sort(int[] arrayToBeSorted) {
		array = arrayToBeSorted.clone();
		
		int max = 0;
		for (int i : array) {
			if (i > max) {
				max = i;
			}
		}
		int[] counts = new int[base];
		
		for (int i = 1; i <= greatestRadixPos(max); i++) {
			for (int j = 0; j < base; j++) {
				counts[j] = 0;
			}
			for (int j = 0; j < array.length; j++) {
				counts[radixAt(array[j], i)]++;
				addMovement(new HighlightMovement(j));
			}
			int sum = 0;
			for (int j = 0; j < base; j++) {
				sum += counts[j];
				counts[j] = sum;
			}
			int[] og = array.clone(); int radix;
			for (int j = og.length - 1; j >= 0; j--) {
				radix = radixAt(og[j], i);

				counts[radix]--;
				
				array[counts[radix]] = og[j];
				addMovement(new ReplaceMovement(counts[radix], og[j]));
				
			}
		}
		
		finish();
		
	}
	
	private int greatestRadixPos(int i) {
		int q = 1;
		int r = 0;
		while (i/q > 0) {
			q *= base;
			r++;
		}
		return r;
	}
	
	private int radixAt(int num, int radix) {
		num = num%((int)Math.pow(base, radix));

		if (radix > 0) {
			num /= (int)Math.pow(base, radix-1);
		}

		return num;
	}

}
