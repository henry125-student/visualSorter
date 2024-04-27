package action;

import main.*;

public abstract class Action {
	World world;
	
	public Action(World world) {
		this.world = world;
	}
	
	public void act() {
		world.setData(edit(world.getData().clone()));
	}
	
	public abstract String getName();
	
	protected abstract int[] edit(int[] data);
}
