package action;

import main.*;

public class RotateAction extends Action {

	public RotateAction(World world) {
		super(world);
	}
	
	public String getName() {
		return "Rotate";
	}
	
	protected int[] edit(int[] data) {
		int temp;
		for (int i = 0; i < data.length/20; i++) {
			temp = data[0];
			for (int j = 0; j < data.length-1; j++) {
				data[j] = data[j+1];
			}
			data[data.length-1] = temp;
		}
		
		return data;
	}

}
