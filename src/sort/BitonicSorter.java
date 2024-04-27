package sort;

//import java.util.*;
import main.*;
import movement.*;

public class BitonicSorter extends Sorter{

	public BitonicSorter(World world) {
		super(world);
	}

	public String getName() {
		return "Bitonic Sort";
	}

	private int[] array;
	public synchronized void sort(int[] arrayToBeSorted) {
		array = arrayToBeSorted.clone();
		
		bitonicSort(array, 0, array.length, true);
		
		finish();
		
	}
	
	private void bitonicSort(int[] data, int f, int l, boolean dir) {
		if (Math.abs(l - f) <= 1) {
			return;
		}
		int m = (f + l) /2;
		merge(data, f, m, l, dir);
	}
	
	private void merge(int[] data, int f, int m, int l, boolean dir) {
		
		bitonicSort(data, f, m, !dir);
		bitonicSort(data, m, l, dir);
		
		fix(data, f, l, dir);
	}
	
	private void fix(int[] data, int f, int l, boolean dir) {
		int q = greatestPow2LessThan(l-f);
		for (int i = f; i < l-q; i++) {
			if (dir == (data[i] > data[i+q])) {
				swap(data, i, i+q);
			} else {
				addMovement(new HighlightMovement(i, i+q));
			}
		}

		if (Math.abs(f-l) >= 2) {
			fix(data, f, f+q, dir);
			fix(data, f+q, l, dir);
		}
	}
	
	private void swap(int[] array, int i, int j) {
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
		addMovement(new SwapMovement(i, j));
	}
	
	private int greatestPow2LessThan(int i) {
		int q = 1;
		while (q < i) {
			q *= 2;
		}
		return q/2;
	}

}
