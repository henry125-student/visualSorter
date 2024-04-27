package movement;

import main.Frame;

public class HighlightMovement extends Movement{
	
	private int[] indices;
	
	public HighlightMovement(int... indices) {
		this.indices = indices;
	}
	
	public Frame applyTo(Frame frame) {
		Frame newFrame = new Frame(frame.getData(), indices);
		
		return newFrame;
	}
}
