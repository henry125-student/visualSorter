package sort;

//import java.util.*;

import main.*;
import movement.*;

public abstract class Sorter {
	private World world;
	
	public Sorter(World world) {
		this.world = world;
	}
	
	public abstract String getName();
	
	public abstract void sort(int[] array);
	
	
	protected void addMovement(Movement movement) {
		world.addToQueue(movement);
	}
	
	protected void finish() {
		world.endQueue();
	}
	
	protected World getWorld() {
		return world;
	}
}
