package main;

import java.util.*;

import action.*;
import arrayType.*;
import display.*;
import javafx.application.Platform;
//import javafx.event.*;
import javafx.scene.*;
import javafx.scene.input.*;
import movement.*;
import sort.*;

public class World {
	private Scene scene;
	private Group root, text = new Group(), graph = new Group();
	
	private Frame data;
	private Queue<Movement> movementQueue = new Queue<Movement>();
	private Timer timer = new Timer();
	
	private Display[] displays;
	private int displayIndex = 0;
	
	private static final int[] arraySizes = {10, 25, 50, 100, 250, 500, 1000, 2500, 5000};
	private int arraySizeIndex = 4;
	
	private final ArrayType[] arrayTypes = {
			new EvenArrayType(),
			new StepsArrayType(),
			new RandomArrayType(),
			new ExponentialArrayType(),
			new SigmoidArrayType()
	};
	private int arrayTypeIndex = 0;
	
	private final Action[] actions = {
			new ShuffleAction(this),
			new InvertAction(this),
			new RoughenAction(this),
			new InstantSortAction(this),
			new UnMergeAction(this),
			new RotateAction(this),
			new Swap3Action(this)
	};
	private int actionIndex = 0;
	
	private static final int[] delayTimes = {250, 100, 50, 25, 10, 5, 2, 1, 0};
	private int delayTimeIndex = 4;
	
	private final Sorter[] sorters = {
			new BubbleSorter(this),
			new CocktailShakerSorter(this),
			new OddEvenSorter(this),
			new SelectionSorter(this),
			new InsertionSorter(this),
			new CombSorter(this),
			new ShellSorter(this),
			new QuickSorter(this),
			new HeapSorter(this),
			new MergeSorter(this),
			new BitonicSorter(this),
			new CycleSorter(this),
			new CountingSorter(this),
			new RadixLSDSorter(this, 2),
			new RadixLSDSorter(this, 4),
			new RadixLSDSorter(this, 10),
			new RadixMSDSorter(this, 2),
			new RadixMSDSorter(this, 4),
			new RadixMSDSorter(this, 10),
			new BucketNotReallySorter(this, 10)
	};
	private int sorterIndex = 0;
	
	private ArrayList<SettingBox> menu = new ArrayList<SettingBox>();
	private int menuSelectIndex = 0;
	
	public World(Scene scene) {
		this.scene = scene;
		this.root = (Group)scene.getRoot();
		displays = new Display[]{
				new HeightBarDisplay(scene, graph),
				new LinePlotDisplay(scene, graph),
				new DotPlotDisplay(scene, graph),
				new BinaryRadixDisplay(scene, graph),
				new GreyscaleGradientDisplay(scene, graph),
				new ChromaticGradientDisplay(scene, graph),
				new ChromaticDotPlotDisplay(scene, graph),
				new ChromaticHeightBarDisplay(scene, graph)
				};
		
		setUpMenu();
		
		root.getChildren().add(graph);
		graph.toBack();
		data = new Frame(arrayTypes[arrayTypeIndex].get(arraySizes[arraySizeIndex]));
		getDisplay().setUp(data);
		scene.widthProperty().addListener((property, oldVal, newVal) -> {
			getDisplay().update(data);
		});
		scene.heightProperty().addListener((property, oldVal, newVal) -> {
			getDisplay().update(data);
		});
	}
	
	private void setUpMenu() {
		root.getChildren().add(text);
		
		World self = this;
		menu.add(new SettingBox(text, "Display: Height Bars") {
			public void leftPress() {
				displayIndex = (displayIndex - 1 + displays.length) % displays.length;
				updateText("Display: "+displays[displayIndex].getName());
				self.getDisplay().setUp(data);
			}
			public void rightPress() {
				displayIndex = (displayIndex + 1) % displays.length;
				updateText("Display: "+displays[displayIndex].getName());
				self.getDisplay().setUp(data);
			}
		});
		menu.add(new SettingBox(text, "Array Size: 250") {
			public void leftPress() {
				arraySizeIndex = Math.max(arraySizeIndex - 1, 0);
				updateText("Array Size: "+arraySizes[arraySizeIndex]);
			}
			public void rightPress() {
				arraySizeIndex = Math.min(arraySizeIndex + 1, arraySizes.length - 1);
				updateText("Array Size: "+arraySizes[arraySizeIndex]);
			}
		});
		menu.add(new SettingBox(text, "Array Type: Even") {
			public void leftPress() {
				arrayTypeIndex = (arrayTypeIndex - 1 + arrayTypes.length) % arrayTypes.length;
				updateText("Array Type: "+arrayTypes[arrayTypeIndex].getName());
			}
			public void rightPress() {
				arrayTypeIndex = (arrayTypeIndex + 1) % arrayTypes.length;
				updateText("Array Type: "+arrayTypes[arrayTypeIndex].getName());
			}
		});
		menu.add(new SettingBox(text, "[Set New Array]") {
			public void rightPress() {
				setData(arrayTypes[arrayTypeIndex].get(arraySizes[arraySizeIndex]));
				movementQueue.clear();
			}
		});
		menu.add(new SettingBox(text, "Action: Shuffle") {
			public void leftPress() {
				actionIndex = (actionIndex - 1 + actions.length) % actions.length;
				updateText("Action: "+actions[actionIndex].getName());
			}
			public void rightPress() {
				actionIndex = (actionIndex + 1) % actions.length;
				updateText("Action: "+actions[actionIndex].getName());
			}
		});
		menu.add(new SettingBox(text, "[Act]") {
			public void rightPress() {
				movementQueue.clear();
				actions[actionIndex].act();
			}
		});
		menu.add(new SettingBox(text, "Sort: Bubble Sort") {
			public void leftPress() {
				sorterIndex = (sorterIndex - 1 + sorters.length) % sorters.length;
				updateText("Sort: "+sorters[sorterIndex].getName());
			}
			public void rightPress() {
				sorterIndex = (sorterIndex + 1) % sorters.length;
				updateText("Sort: "+sorters[sorterIndex].getName());
			}
		});
		menu.add(new SettingBox(text, "Delay Time: 10") {
			public void leftPress() {
				delayTimeIndex = Math.max(delayTimeIndex - 1, 0);
				updateText("Delay Time: "+delayTimes[delayTimeIndex]);
			}
			public void rightPress() {
				delayTimeIndex = Math.min(delayTimeIndex + 1, delayTimes.length - 1);
				updateText("Delay Time: "+delayTimes[delayTimeIndex]);
			}
		});
		menu.add(new SettingBox(text, "[Sort]") {
			public void rightPress() {
				movementQueue.clear();
				sorters[sorterIndex].sort(data.getData());
				text.setVisible(false);
				play();
			}
		});
		
		menu.get(0).setIsSelected(true);
		
		scene.setOnKeyPressed((KeyEvent e) -> {
			if (text.isVisible()) {
				switch(e.getCode()) {
				case ESCAPE:
					text.setVisible(false);
					break;
				case UP:
					menu.get(menuSelectIndex).setIsSelected(false);
					menuSelectIndex = (menuSelectIndex - 1 + menu.size())%menu.size();
					menu.get(menuSelectIndex).setIsSelected(true);
					break;
				case DOWN:
					menu.get(menuSelectIndex).setIsSelected(false);
					menuSelectIndex = (menuSelectIndex + 1)%menu.size();
					menu.get(menuSelectIndex).setIsSelected(true);
					break;
				case ENTER:
				case RIGHT:
					menu.get(menuSelectIndex).rightPress();
					break;
				case BACK_SPACE:
				case LEFT:
					menu.get(menuSelectIndex).leftPress();
					break;
				default:
					break;
				};
			} else {
				text.setVisible(true);
			}
		}); 
	}
	
	private boolean isDone = true;
	
	public void start() {
		isDone = false;
		play();
	}
	
	private void play() {
		if (movementQueue.isEmpty() && isDone) {
			
			return;
		}
		
		Movement subject = movementQueue.getFirst();
		
		if (subject.isReady()) {
			setData(movementQueue.take().applyTo(data));
		} else {
			System.out.println("lagg");
		}
		

		if (!timerCancelled) {
			timer.schedule(new TimerTask() {
				public void run() {
					Platform.runLater(() -> {
						play();
					});
				}
			}, delayTimes[delayTimeIndex]);
		}
	}
	
	public void addToQueue(Movement movement) {
		
		movementQueue.add(movement);
	}
	
	public void endQueue() {
		movementQueue.add(new NothingMovement());
		isDone = true;
	}
	
	public Display getDisplay() {
		return displays[displayIndex];
	}
	
	public int[] getData() {
		return data.getData();
	}
	
	public void setData(Frame data){
		this.data = data;
		getDisplay().update(this.data);
	}
	
	public void setData(int[] data){
		this.data = new Frame(data);
		getDisplay().update(this.data);
	}
	
	public void close() {
		timer.cancel();
		timerCancelled = true;
	}
	
	private boolean timerCancelled = false;
	public Timer getTimer() {
		return timer;
	}
	public boolean isTimerCancelled() {
		return timerCancelled;
	}
	
}
