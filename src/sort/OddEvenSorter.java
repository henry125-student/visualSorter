package sort;

//import java.util.*;
import main.*;
import movement.*;

public class OddEvenSorter extends Sorter{

	public OddEvenSorter(World world) {
		super(world);
	}

	public String getName() {
		return "Odd-Even Sort";
	}

	private int[] array;
	public synchronized void sort(int[] arrayToBeSorted) {
		array = arrayToBeSorted.clone();
		
		int temp; boolean sorted; int sortedInRow = 0; int offset = 0;
		while (true) {
			sorted = true;
			offset = (offset + 1)%2;
			for (int j = offset; j < array.length - 1; j+=2) {
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
				sortedInRow++;
				if (sortedInRow >= 2) {
					break;
				}
			} else {
				sortedInRow = 0;
			}
		}
		
		finish();
		
	}

}
