package sort;

//import java.util.*;
import main.*;
import movement.*;

public class BubbleSorter extends Sorter{

	public BubbleSorter(World world) {
		super(world);
	}

	public String getName() {
		return "Bubble Sort";
	}

	private int[] array;
	public synchronized void sort(int[] arrayToBeSorted) {
		array = arrayToBeSorted.clone();
		
		int temp; boolean sorted;
		for (int i = 0; i < array.length - 1; i++) {
			sorted = true;
			for (int j = 0; j < array.length - 1 - i; j++) {
				if (array[j] > array[j+1]) {
					temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
					addMovement(new SwapMovement(j, j+1));
					
					sorted = false;
					
				} else {
					addMovement(new HighlightMovement(j, j+1));
				}
			}
			if (sorted) {
				break;
			}
		}
		
		finish();
		
	}

}
