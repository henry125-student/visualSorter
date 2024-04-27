package sort;

//import java.util.*;
import main.*;
import movement.*;

public class CountingSorter extends Sorter{

	public CountingSorter(World world) {
		super(world);
	}

	public String getName() {
		return "Counting Sort";
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
		int[] counts = new int[max+1];
		
		for (int j = 0; j < counts.length; j++) {
			counts[j] = 0;
		}
		for (int j = 0; j < array.length; j++) {
			counts[array[j]]++;
			addMovement(new HighlightMovement(j));
		}
		int sum = 0;
		for (int j = 0; j < counts.length; j++) {
			sum += counts[j];
			counts[j] = sum;
		}
		int[] og = array.clone();
		for (int j = og.length - 1; j >= 0; j--) {
			counts[og[j]]--;
			
			array[counts[og[j]]] = og[j];
			addMovement(new ReplaceMovement(counts[og[j]], og[j]));
			
		}
		
		finish();
		
	}

}
