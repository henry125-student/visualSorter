package action;

import main.*;

public class InstantSortAction extends Action {

	public InstantSortAction(World world) {
		super(world);
	}
	
	public String getName() {
		return "Sort (Instant)";
	}
	
	protected int[] edit(int[] data) {
		
		mergeSort(data, 0, data.length);
		
		return data;
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
		}
		int[] arr2 = new int[l - m];
		for (int i = 0; i < l - m; i++) {
			arr2[i] = data[m + i];
		}
		
		int arr1Index = 0, arr2Index = 0, dataIndex = f;
		while (arr1Index < m-f && arr2Index < l-m) {
			if (arr1[arr1Index] < arr2[arr2Index]) {
				data[dataIndex] = arr1[arr1Index];
				arr1Index++;
			} else {
				data[dataIndex] = arr2[arr2Index];
				arr2Index++;
			}
			dataIndex++;
		}
		while (arr1Index < m-f) {
			data[dataIndex] = arr1[arr1Index];
			arr1Index++;
			dataIndex++;
		}
		while (arr2Index < l-m) {
			data[dataIndex] = arr2[arr2Index];
			arr2Index++;
			dataIndex++;
		}
	}

}
