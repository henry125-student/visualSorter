package action;

import main.*;

public class InvertAction extends Action {

	public InvertAction(World world) {
		super(world);
	}
	
	public String getName() {
		return "Invert";
	}
	
	protected int[] edit(int[] data) {
		
		for (int i = 0; i < data.length / 2; i++) {
			int j = data.length - i - 1;
			int temp = data[i];
			data[i] = data[j];
			data[j] = temp;
		}
		
		return data;
	}

}
