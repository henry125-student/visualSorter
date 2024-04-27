package sort;

//import java.util.*;
import main.*;
import movement.*;

public class CombSorter extends Sorter{

	public CombSorter(World world) {
		super(world);
	}

	public String getName() {
		return "Comb Sort";
	}

	private int[] array;
	public synchronized void sort(int[] arrayToBeSorted) {
		array = arrayToBeSorted.clone();
		
		int temp; boolean sorted; int gap = array.length - 1;
		while (true) {
			sorted = true;
			gap = shrinkGap(gap);
			for (int j = 0; j < array.length - gap; j++) {
				if (array[j] > array[j+gap]) {
					temp = array[j];
					array[j] = array[j+gap];
					array[j+gap] = temp;
					addMovement(new SwapMovement(j, j+gap));
					
					sorted = false;
					
				} else {
					addMovement(new HighlightMovement(j, j+gap));
				}
			}
			if (sorted && gap == 1) {
				break;
			}
		}
		
		finish();
		
	}
	
	private int shrinkGap(int gap) {
		gap = (gap*10)/13;
		if (gap < 1) {
			return 1;
		}
		return gap;
	}

}
