package action;

import main.*;

public class UnMergeAction extends Action {

	public UnMergeAction(World world) {
		super(world);
	}
	
	public String getName() {
		return "UnMerge";
	}
	
	protected int[] edit(int[] data) {
		
		int[] og = data.clone();
		
		int k = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = i; j < og.length; j += 2) {
				data[k] = og[j];
				k++;
			}
		}
		
		return data;
	}

}
