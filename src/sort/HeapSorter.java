package sort;

//import java.util.*;
import main.*;
import movement.*;

public class HeapSorter extends Sorter{

	public HeapSorter(World world) {
		super(world);
	}

	public String getName() {
		return "Heap Sort";
	}

	private int[] array;
	public synchronized void sort(int[] arrayToBeSorted) {
		array = arrayToBeSorted.clone();
		
		for (int i = array.length/2 - 1; i >= 0; i--) {
			heapify(array, i, array.length);
		}
		for (int i = array.length - 1; i >= 0; i--) {
			swap(array, i, 0);
			heapify(array, 0, i);
		}
		
		finish();
		
	}
	
	private void heapify(int[] array, int i, int end) {
		int left = i*2+1;
		int right = i*2+2;
		int max = i;
		if (left < end && array[left] > array[max]) {
			max = left;
		}
		if (right < end && array[right] > array[max]) {
			max = right;
		}
		if (i != max) {
			swap(array, i, max); 
			heapify(array, max, end);
		} else {
			addMovement(new HighlightMovement(i));
		}
	}
	
	private void swap(int[] array, int i, int j) {
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
		addMovement(new SwapMovement(i, j));
	}

}
