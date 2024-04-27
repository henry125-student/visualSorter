package movement;

import main.*;

public class ReplaceMovement extends Movement{

	private int[] indices;
	private int[] newVals;
	
	public ReplaceMovement(int[] indices, int[] newVals) {
		this.indices = indices;
		this.newVals = newVals;
	}
	
	public ReplaceMovement(int... ints) {
		if (ints.length % 2 == 1) {
			return;
		}
		indices = new int[ints.length/2];
		newVals = new int[ints.length/2];
		for (int i = 0; i < ints.length; i++) {
			if (i % 2 == 0) {
				indices[i/2] = ints[i];
			} else {
				newVals[i/2] = ints[i];
			}
		}
	}
	
	public Frame applyTo(Frame frame) {
		Frame newFrame = new Frame(frame.getData(), indices);
		newFrame.setMax(frame.getMax());
		
		for (int i = 0; i < indices.length; i++) {
			newFrame.getData()[indices[i]] = newVals[i];
		}
		
		return newFrame;
	}
	
}
