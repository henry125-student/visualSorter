package main;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.paint.*;
import javafx.stage.*;

public class VisualSorter extends Application{

	public void start(Stage stage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root, 500, 500);
		scene.setFill(Color.BLACK);
		
		World world = new World(scene);
		
		stage.setTitle("Sorter"); 
		stage.setResizable(true);
	    stage.setScene(scene); 
	    stage.show(); 
	    
	    stage.setOnCloseRequest((WindowEvent e) -> {
	    	world.close();
	    });
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
