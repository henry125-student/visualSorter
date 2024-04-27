package action;

import main.*;

public class Swap3Action extends Action {

	public Swap3Action(World world) {
		super(world);
	}
	
	public String getName() {
		return "Swap 3";
	}
	
	protected int[] edit(int[] data) {
		
		for (int i = 0; i < 3; i++) {
			int j = (int)(Math.random() * data.length);
			int k = (int)(Math.random() * data.length);
			int temp = data[k];
			data[k] = data[j];
			data[j] = temp;
		}
		
		return data;
	}

}
