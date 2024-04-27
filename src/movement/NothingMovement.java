package movement;

import main.Frame;

public class NothingMovement extends Movement {

	public NothingMovement() {}
	
	public Frame applyTo(Frame frame) {
		return new Frame(frame.getData());
	}

}
