package display;

import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import main.Frame;

public class GreyscaleGradientDisplay extends Display{

	public GreyscaleGradientDisplay(Scene scene, Group display) {
		super(scene, display);
	}

	public String getName() {
		return "GreyScale Gradient";
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
			
			int value = (array[i] * 255 / max);
			
			if (frame.isHighlighted(i)) {
				subject.setFill(Color.rgb(0, value, 0));
			} else {
				subject.setFill(Color.rgb(value, value, value));
			}
			
		}
		
	}

}
