package display;

import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import main.Frame;

public class ChromaticGradientDisplay extends Display{

	public ChromaticGradientDisplay(Scene scene, Group display) {
		super(scene, display);
	}

	public String getName() {
		return "Chromatic Gradient";
	}

	private Rectangle[] columns;
	
	public void setUp(Frame frame) {
		int[] array = frame.getData();
		display.getChildren().clear();
		columns = new Rectangle[array.length];
		for (int i = 0; i < array.length; i++) {
			columns[i] = new Rectangle();
			display.getChildren().add(columns[i]);
		}
		update(frame);
		
	}

	public synchronized void update(Frame frame) {
		int[] array = frame.getData();
		if (columns == null || array.length != columns.length) {
			setUp(frame);
			return;
		}

		int max = frame.getMax();
		double barWidth = scene.getWidth() / Math.max(array.length, 1);
		for (int i = 0; i < array.length; i++) {
			Rectangle subject = columns[i];
			subject.setTranslateX(i * barWidth);
			subject.setWidth(barWidth);
			subject.setHeight(scene.getHeight());
			subject.setTranslateY(0);
			
			int value = (array[i] * 360 / max);
			
			if (frame.isHighlighted(i)) {
				subject.setFill(Color.hsb(value, 0.1, 1));
			} else {
				subject.setFill(Color.hsb(value, 1, 1));
			}
			
		}
		
	}

}
