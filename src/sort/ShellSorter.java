package sort;

//import java.util.*;
import main.*;
import movement.*;

public class ShellSorter extends Sorter{

	public ShellSorter(World world) {
		super(world);
	}

	public String getName() {
		return "Shell Sort";
	}

	private int[] array;
	public synchronized void sort(int[] arrayToBeSorted) {
		array = arrayToBeSorted.clone();
		
		int temp; int interval = array.length;
		while (interval > 1) {
			interval = shrinkInterval(interval);
			
			for (int i = interval; i < array.length; i++) {
				for (int j = i - interval; j >= 0; j-=interval) {
					if (array[j] > array[j+interval]) {
						temp = array[j+interval];
						array[j+interval] = array[j];
						array[j] = temp;
						addMovement(new SwapMovement(j, j+interval));
					} else {
						addMovement(new HighlightMovement(j, j+interval));
						break;
					}
				}
			}
		}
		
		finish();
		
	}
	
	private int shrinkInterval(int interval) {
		interval = (interval*100)/225;
		if (interval < 1) {
			return 1;
		}
		return interval;
	}

}
