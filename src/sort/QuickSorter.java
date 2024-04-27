package sort;

import java.util.*;
import main.*;
import movement.*;

public class QuickSorter extends Sorter{

	public QuickSorter(World world) {
		super(world);
	}

	public String getName() {
		return "Quick Sort";
	}

	private int[] array;
	public synchronized void sort(int[] arrayToBeSorted) {
		array = arrayToBeSorted.clone();
		
		quickSort(array, 0, array.length);
		
		finish();
		
	}
	
	private void quickSort(int[] array, int f, int l) {
		if (f < l-1) {
			int pivot = partition(array, f, l);
			
			quickSort(array, f, pivot);
			quickSort(array, pivot+1, l);
		}
	}
	
	private static Random random = new Random();
	
	private int partition(int[] array, int f, int l) {
		swap(array, l-1, random.nextInt(l-f-1)+f);
		int pivotValue = array[l-1];
		
		int i = f-1;
		for (int j = f; j <= l-2; j++) {
			if (array[j] <= pivotValue){
	            i++;
	            swap(array, i, j);
	        } else {
	        	addMovement(new HighlightMovement(i, j));
	        }
		}
		
		swap(array, i+1, l-1);
	    return (i+1);
	}
	
	private void swap(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
		addMovement(new SwapMovement(i, j));
	}

}
