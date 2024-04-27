package display;

import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import main.Frame;

public class LinePlotDisplay extends Display{

	public LinePlotDisplay(Scene scene, Group display) {
		super(scene, display);
	}

	public String getName() {
		return "Line Plot";
	}

	private Line[] lines;
	
	public void setUp(Frame frame) {
		int[] array = frame.getData();
		display.getChildren().clear();
		lines = new Line[array.length-1];
		for (int i = 0; i < lines.length; i++) {
			lines[i] = new Line();
			lines[i].setStrokeLineCap(StrokeLineCap.ROUND);
			display.getChildren().add(lines[i]);
		}
		update(frame);
		
	}

	public void update(Frame frame) {
		int[] array = frame.getData();
		if (lines == null || array.length-1 != lines.length) {
			setUp(frame);
			return;
		}
		int max = frame.getMax();
		double barWidth = scene.getWidth() / Math.max(array.length, 0);
		double barUnitHeight = scene.getHeight() / max;
		for (int i = 0; i < array.length - 1; i++) {
			Line subject = lines[i];
			subject.setStartX(i * barWidth + barWidth/2);
			subject.setEndX((i+1) * barWidth + barWidth/2);
			subject.setStartY(scene.getHeight() - barUnitHeight * array[i]);
			subject.setEndY(scene.getHeight() - barUnitHeight * array[i+1]);
			
			if (frame.isHighlighted(i) || frame.isHighlighted(i+1)) {
				subject.setStroke(Color.LIME);
				lines[i].setStrokeWidth(4);
			} else {
				subject.setStroke(Color.WHITE);
				lines[i].setStrokeWidth(1);
			}
			
		}
		
	}

}
