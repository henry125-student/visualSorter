package sort;

//import java.util.*;
import main.*;
import movement.*;

public class CocktailShakerSorter extends Sorter{

	public CocktailShakerSorter(World world) {
		super(world);
	}

	public String getName() {
		return "Cocktail Shaker Sort";
	}

	private int[] array;
	public synchronized void sort(int[] arrayToBeSorted) {
		array = arrayToBeSorted.clone();
		
		int temp; boolean sorted;
		int high = array.length - 1; int low = 0; int sortedInRow = 0;
		while (true) {
			//up
			sorted = true;
			for (int j = low; j < high; j++) {
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
			high--;
			
			//down
			
			sorted = true;
			for (int j = high; j > low; j--) {
				if (array[j-1] > array[j]) {
					temp = array[j-1];
					array[j-1] = array[j];
					array[j] = temp;
					addMovement(new SwapMovement(j-1, j));
					
					sorted = false;
					
				} else {
					addMovement(new HighlightMovement(j-1, j));
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
			low++;
		}
		
		finish();
		
	}

}
