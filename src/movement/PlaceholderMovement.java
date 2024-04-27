package movement;

import main.Frame;

public class PlaceholderMovement extends Movement{
	
	public PlaceholderMovement() {
		
	}
	
	public boolean isReady() {
		return false;
	}

	public Frame applyTo(Frame frame) {
		return null;
	}
}
