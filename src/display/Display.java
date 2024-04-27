package display;

import javafx.scene.*;
import main.*;

public abstract class Display {
	protected Scene scene;
	protected Group display;
	
	public Display(Scene scene, Group display) {
		this.scene = scene;
		this.display = display;
		display.toBack();
	}
	public abstract String getName();
	public abstract void setUp(Frame frame);
	public abstract void update(Frame frame);
}
