package action;

import main.*;

public class ShuffleAction extends Action {

	public ShuffleAction(World world) {
		super(world);
	}
	
	public String getName() {
		return "Shuffle";
	}
	
	protected int[] edit(int[] data) {
		
		for (int i = 0; i < data.length; i++) {
			int j = (int)(Math.random() * data.length);
			int temp = data[i];
			data[i] = data[j];
			data[j] = temp;
		}
		
		return data;
	}

}
