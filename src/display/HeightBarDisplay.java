package display;

import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import main.Frame;

public class HeightBarDisplay extends Display{

	public HeightBarDisplay(Scene scene, Group display) {
		super(scene, display);
	}

	public String getName() {
		return "Height Bars";
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

	public void update(Frame frame) {
		int[] array = frame.getData();
		if (columns == null || array.length != columns.length) {
			setUp(frame);
			return;
		}
		int max = frame.getMax();
		double barWidth = scene.getWidth() / Math.max(array.length, 1);
		double barUnitHeight = scene.getHeight() / max;
		for (int i = 0; i < array.length; i++) {
			Rectangle subject = columns[i];
			subject.setTranslateX(i * barWidth);
			subject.setWidth(barWidth);
			subject.setHeight(barUnitHeight * array[i]);
			subject.setTranslateY(scene.getHeight() - subject.getHeight());
			
			if (frame.isHighlighted(i)) {
				subject.setFill(Color.LIME);
			} else {
				subject.setFill(Color.WHITE);
			}
			
		}
		
	}

}
