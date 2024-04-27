package display;

import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import main.Frame;

public class BinaryRadixDisplay extends Display{

	public BinaryRadixDisplay(Scene scene, Group display) {
		super(scene, display);
	}

	public String getName() {
		return "Binary Radix";
	}

	private Rectangle[][] columns;
	
	public void setUp(Frame frame) {
		int[] array = frame.getData();
		display.getChildren().clear();
		columns = new Rectangle[array.length][greatestRadixPos(frame.getMax())];
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < greatestRadixPos(frame.getMax()); j++) {
				columns[i][j] = new Rectangle();
				display.getChildren().add(columns[i][j]);
			}
		}
		update(frame);
		
	}

	public synchronized void update(Frame frame) {
		int[] array = frame.getData();
		int maxRadix = greatestRadixPos(frame.getMax());
		if (columns == null || array.length != columns.length || maxRadix != columns[0].length) {
			setUp(frame);
			return;
		}

		double barWidth = scene.getWidth() / Math.max(array.length, 1);
		double barHeight = scene.getHeight() / maxRadix;
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < greatestRadixPos(frame.getMax()); j++) {
				Rectangle subject = columns[i][j];
				subject.setTranslateX(i * barWidth);
				subject.setWidth(barWidth);
				subject.setHeight(barHeight);
				subject.setTranslateY(barHeight * j);
				
				if (radixAt(array[i], maxRadix - j) == 1) {
					if (frame.isHighlighted(i)) {
						subject.setFill(Color.GREEN);
					} else {
						subject.setFill(Color.WHITE);
					}
				} else {
					subject.setFill(Color.BLACK);
				}
			}
		}
		
	}
	
	private int greatestRadixPos(int i) {
		int q = 1;
		int r = 0;
		while (i/q > 0) {
			q *= 2;
			r++;
		}
		return r;
	}
	
	private int radixAt(int num, int radix) {
		num = num%((int)Math.pow(2, radix));

		if (radix > 0) {
			num /= (int)Math.pow(2, radix-1);
		}

		return num;
	}

}
