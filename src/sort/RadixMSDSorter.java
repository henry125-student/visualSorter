package sort;

//import java.util.*;
import main.*;
import movement.*;

public class RadixMSDSorter extends Sorter{

	private int base;
	
	public RadixMSDSorter(World world, int base) {
		super(world);
		this.base = base;
	}

	public String getName() {
		return "Radix MSD Sort (Base "+base+")";
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
		radixSort(array, 0, array.length, greatestRadixPos(max));
		
		finish();
	}
	public void radixSort(int[] arrayToBeSorted, int f, int l, int p) {
		int[] counts = new int[base];
		
		for (int j = 0; j < base; j++) {
			counts[j] = 0;
		}
		for (int j = f; j < l; j++) {
			counts[radixAt(array[j], p)]++;
			addMovement(new HighlightMovement(j));
		}
		int sum = f;
		for (int j = 0; j < base; j++) {
			sum += counts[j];
			counts[j] = sum;
		}
		int[] og = array.clone(); int radix;
		int[] indices = counts.clone();
		for (int j = l - 1; j >= f; j--) {
			radix = radixAt(og[j], p);

			counts[radix]--;
			
			array[counts[radix]] = og[j];
			addMovement(new ReplaceMovement(counts[radix], og[j]));
			
		}
		if (p > 1) {
			if (indices[0] != 0) {
				radixSort(array, f, indices[0], p-1);
			}
			for (int i = 0; i < indices.length - 1; i++) {
				if (indices[i] != indices[i+1]) {
					radixSort(array, indices[i], indices[i+1], p-1);
				}
			}
		}
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
