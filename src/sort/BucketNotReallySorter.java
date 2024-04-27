package sort;

//import java.util.*;
import main.*;
import movement.*;

public class BucketNotReallySorter extends Sorter{

	private int buckets;
	
	public BucketNotReallySorter(World world, int buckets) {
		super(world);
		this.buckets = buckets;
	}

	public String getName() {
		return "\"Bucket\" Sort ("+buckets+" buckets)";
	}

	private int[] array;
	private int min, max;
	public synchronized void sort(int[] arrayToBeSorted) {
		array = arrayToBeSorted.clone();
		
		max = 0; min = 0;
		for (int i : array) {
			if (i > max) {
				max = i;
			}
			if (i < min) {
				min = i;
			}
		}
		
		int[] counts = new int[buckets];
		
		for (int j = 0; j < buckets; j++) {
			counts[j] = 0;
		}
		for (int j = 0; j < array.length; j++) {
			counts[assignBucket(array[j])]++;
			addMovement(new HighlightMovement(j));
		}
		int sum = 0;
		for (int j = 0; j < buckets; j++) {
			sum += counts[j];
			counts[j] = sum;
		}
		int[] og = array.clone(); int bucket;
		int[] indices = counts.clone();
		for (int j = array.length - 1; j >= 0; j--) {
			bucket = assignBucket(og[j]);

			counts[bucket]--;
			
			array[counts[bucket]] = og[j];
			addMovement(new ReplaceMovement(counts[bucket], og[j]));
			
		}
		
		//sort buckets
		if (indices[0] != 0) {
			insertionSort(array, 0, indices[0]);
		}
		for (int i = 0; i < indices.length - 1; i++) {
			if (indices[i] != indices[i+1]) {
				insertionSort(array, indices[i], indices[i+1]);
			}
		}
		
		finish();
	}
	
	private int assignBucket(int num) {
		if (num == max) {
			return buckets-1;
		}
		return buckets*(num-min)/(max-min);
	}
	
	private void insertionSort(int[] array, int f, int l) {
		int temp;
		for (int i = f+1; i < l; i++) {
			for (int j = i - 1; j >= f; j--) {
				if (array[j] > array[j+1]) {
					temp = array[j+1];
					array[j+1] = array[j];
					array[j] = temp;
					addMovement(new SwapMovement(j, j+1));
				} else {
					addMovement(new HighlightMovement(j, j+1));
					break;
				}
			}
		}
		
	}

}
