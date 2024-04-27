package action;

import main.*;

public class RoughenAction extends Action {

	public RoughenAction(World world) {
		super(world);
	}
	
	public String getName() {
		return "Roughen";
	}
	
	protected int[] edit(int[] data) {
		
		boolean[] hasSwapped = new boolean[data.length];
		
		for (int i = 0; i < data.length; i++) {
			if (!hasSwapped[i]) {
				int j;
				int range = data.length/15 + 1;
				int min = Math.max(0, i-range);
				int max = Math.min(data.length-1, i+range);
				
				do {
					j = min + (int)(Math.random() * (max - min + 1));
				} while (hasSwapped[j]);
				
				int temp = data[i];
				data[i] = data[j];
				data[j] = temp;
				hasSwapped[i] = true; hasSwapped[j] = true; 
			}
		}
		
		return data;
	}

}
