package sort;

//import java.util.*;
import main.*;
import movement.*;

public class SelectionSorter extends Sorter{

	public SelectionSorter(World world) {
		super(world);
	}

	public String getName() {
		return "Selection Sort";
	}

	private int[] array;
	public synchronized void sort(int[] arrayToBeSorted) {
		array = arrayToBeSorted.clone();
		
		int temp;
		for (int i = 0; i < array.length; i++) {
			int k = i;//minimum index
			for (int j = i ; j < array.length; j++) {
				if (array[k] > array[j]) {
					k = j;
				}
				addMovement(new HighlightMovement(k, j));
			}
			temp = array[k];
			array[k] = array[i];
			array[i] = temp;
			addMovement(new SwapMovement(i, k));
		}
		
		finish();
		
	}

}
