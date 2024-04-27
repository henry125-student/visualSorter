package main;

public class Frame {

	private int[] data;
	private int[] highlightedData;
	private int max;
	
	
	public Frame(int[] data) {
		this.data = data.clone();
		this.highlightedData = new int[0];
		
		setMax();
	}
	
	public Frame(int[] data, int... highlights) {
		this.data = data.clone();
		this.highlightedData = highlights;
		
		setMax();
	}
	
	public int[] getData() {
		return data;
	}
	
	public void setMax() {
		this.max = 1;
		for (int i : data) {
			if (max < i) {
				max = i;
			}
		}
	}
	
	public void setMax(int newVal) {
		max = newVal;
	}
	
	public int getMax() {
		return max;
	}
	
	public boolean isHighlighted(int index) {
		for (int subject : highlightedData) {
			if (subject == index) {
				return true;
			}
		}
		return false;
	}
	
}
