package main;

import javafx.geometry.VPos;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.*;

public class SettingBox {
	private Text display;
	private String text;
	private boolean isSelected = false;
	
	private static Rectangle block;
	private static double yOffset = 10;
	
	public SettingBox(Group group, String text) {
		this.text = text;
		display = new Text(text);
		display.setX(10); display.setY(yOffset); yOffset += 17;
		display.setFont(Font.font(15)); display.setFill(Color.WHITE);
		display.setTextOrigin(VPos.TOP); 
		group.getChildren().add(display);
		
		if (block == null) {
			block = new Rectangle(0, 0, 300, 37);
			block.setFill(Color.color(0, 0, 0, 0.5));
			group.getChildren().add(block);
			block.toBack();
		} else {
			block.setHeight(yOffset + 20);
		}
	}
	
	private void updateDisplay() {
		if (isSelected) {
			display.setText("<"+text+">");
			display.setFill(Color.LIME);
		} else {
			display.setText(text);
			display.setFill(Color.WHITE);
		}
	}
	
	public void updateText(String text) {
		this.text = text;
		updateDisplay();
	}
	
	public Text getDisplay() {
		return display;
	}
	
	public void setIsSelected(boolean newVal) {
		isSelected = newVal;
		updateDisplay();
	}
	
	public void leftPress() {}
	public void rightPress() {}
	
	public static void skipLine() {
		yOffset += 17;
	}
}
