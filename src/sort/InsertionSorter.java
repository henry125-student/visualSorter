package sort;

//import java.util.*;
import main.*;
import movement.*;

public class InsertionSorter extends Sorter{

	public InsertionSorter(World world) {
		super(world);
	}

	public String getName() {
		return "Insertion Sort";
	}

	private int[] array;
	public synchronized void sort(int[] arrayToBeSorted) {
		array = arrayToBeSorted.clone();
		
		int temp;
		for (int i = 1; i < array.length; i++) {
			for (int j = i - 1; j >= 0; j--) {
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
		
		finish();
		
	}

}
