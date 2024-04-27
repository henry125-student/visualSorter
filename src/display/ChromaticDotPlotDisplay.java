package display;

import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import main.Frame;

public class ChromaticDotPlotDisplay extends Display{

	public ChromaticDotPlotDisplay(Scene scene, Group display) {
		super(scene, display);
	}

	public String getName() {
		return "Dot Plot";
	}

	private Circle[] dots;
	
	public void setUp(Frame frame) {
		int[] array = frame.getData();
		display.getChildren().clear();
		dots = new Circle[array.length];
		for (int i = 0; i < array.length; i++) {
			dots[i] = new Circle();
			display.getChildren().add(dots[i]);
		}
		update(frame);
		
	}

	public void update(Frame frame) {
		int[] array = frame.getData();
		if (dots == null || array.length != dots.length) {
			setUp(frame);
			return;
		}
		int max = frame.getMax();
		double barWidth = scene.getWidth() / Math.max(array.length, 0);
		double barUnitHeight = scene.getHeight() / max;
		for (int i = 0; i < array.length; i++) {
			Circle subject = dots[i];
			subject.setTranslateX(i * barWidth + barWidth/2);
			subject.setTranslateY(scene.getHeight() - barUnitHeight * array[i]);
			
			int value = (array[i] * 360 / max);
			
			if (frame.isHighlighted(i)) {
				subject.setRadius(Math.max(Math.min(barWidth / 2, 10), 3));
				subject.setFill(Color.hsb(value, 0.1, 1));
			} else {
				subject.setRadius(Math.max(Math.min(barWidth / 2, 10), 0.75));
				subject.setFill(Color.hsb(value, 1, 1));
			}
			
		}
		
	}

}
