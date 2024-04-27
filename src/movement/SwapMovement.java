package movement;

import main.Frame;

public class SwapMovement extends Movement{
	
	private int i;
	private int j;
	
	public SwapMovement(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	public Frame applyTo(Frame frame) {
		Frame newFrame = new Frame(frame.getData(), i, j);
		
		newFrame.getData()[i] = frame.getData()[j];
		newFrame.getData()[j] = frame.getData()[i];
		
		return newFrame;
	}
}
