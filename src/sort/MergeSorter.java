package sort;

//import java.util.*;
import main.*;
import movement.*;

public class MergeSorter extends Sorter{

	public MergeSorter(World world) {
		super(world);
	}

	public String getName() {
		return "Merge Sort";
	}

	private int[] array;
	public synchronized void sort(int[] arrayToBeSorted) {
		array = arrayToBeSorted.clone();
		
		mergeSort(array, 0, array.length);
		
		finish();
		
	}
	
	private void mergeSort(int[] data, int f, int l) {
		if (l - f <= 1) {
			return;
		}
		int m = (f + l) /2;
		merge(data, f, m, l);
	}
	
	private void merge(int[] data, int f, int m, int l) {
		
		mergeSort(data, f, m);
		mergeSort(data, m, l);
		
		int[] arr1 = new int[m - f];
		for (int i = 0; i < m - f; i++) {
			arr1[i] = data[f + i];
			addMovement(new HighlightMovement(f + i));
		}
		int[] arr2 = new int[l - m];
		for (int i = 0; i < l - m; i++) {
			arr2[i] = data[m + i];
			addMovement(new HighlightMovement(m + i));
		}
		
		int arr1Index = 0, arr2Index = 0, dataIndex = f;
		while (arr1Index < m-f && arr2Index < l-m) {
			if (arr1[arr1Index] < arr2[arr2Index]) {
				data[dataIndex] = arr1[arr1Index];
				addMovement(new ReplaceMovement(dataIndex, arr1[arr1Index]));
				arr1Index++;
			} else {
				data[dataIndex] = arr2[arr2Index];
				addMovement(new ReplaceMovement(dataIndex, arr2[arr2Index]));
				arr2Index++;
			}
			dataIndex++;
		}
		while (arr1Index < m-f) {
			data[dataIndex] = arr1[arr1Index];
			addMovement(new ReplaceMovement(dataIndex, arr1[arr1Index]));
			arr1Index++;
			dataIndex++;
		}
		while (arr2Index < l-m) {
			data[dataIndex] = arr2[arr2Index];
			addMovement(new ReplaceMovement(dataIndex, arr2[arr2Index]));
			arr2Index++;
			dataIndex++;
		}
	}

}
