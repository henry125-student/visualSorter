package movement;

import main.*;

public abstract class Movement {

	public abstract Frame applyTo(Frame frame);
	
	public boolean isReady() {
		return true;
	}
}
