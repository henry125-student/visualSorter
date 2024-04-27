package sort;

//import java.util.*;
import main.*;
import movement.*;

public class CycleSorter extends Sorter{

	public CycleSorter(World world) {
		super(world);
	}

	public String getName() {
		return "Cycle Sort";
	}

	private int[] array;
	public synchronized void sort(int[] arrayToBeSorted) {//TODO
		array = arrayToBeSorted.clone();
		boolean[] sorted = new boolean[array.length];
		
		int pos; int n; int temp;
		for (int i = 0; i < array.length-1; i++) {
			addMovement(new HighlightMovement(i));
			if (!sorted[i]) {
				n = array[i];
				pos = getPos(i, n);
				if (pos == i) {
					sorted[i] = true;
					continue;
				}
				while (n == array[pos]) {
					pos++;
				}
				if (pos != i) {
					temp = n;
					n = array[pos];
					replace(array, pos, temp);
					sorted[pos] = true;
				}
				
				while (pos != i) {
					pos = getPos(i, n);
					while (n == array[pos]) {
						pos++;
					}

					if (n != array[pos]) {
						temp = n;
						n = array[pos];
						replace(array, pos, temp);
						sorted[pos] = true;
					}
					
				}
				
				sorted[i] = true;
			}
		}
		
		finish();
		
	}
	
	private int getPos(int leftBound, int num) {
		int pos = leftBound;
		for (int i = leftBound+1; i < array.length; i++) {
			if (array[i] < num) {
				pos++;
			}
			addMovement(new HighlightMovement(i));
		}
		return pos;
	}
	
	private void replace(int[] array, int i, int newVal) {
		array[i] = newVal;
		addMovement(new ReplaceMovement(i, newVal));
	}

}
