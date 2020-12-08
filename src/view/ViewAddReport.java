package view;

import java.io.FileNotFoundException;

import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ViewAddReport extends BackgroundPage {

	public ViewAddReport() throws FileNotFoundException{
		// Do nothing because exception not managed
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception{
		
		stage.setTitle("SpeedyFila");
		
		Label labelSuccess = new Label("Now background page has reusable code!!!!");
		
		super.info.getChildren().addAll(labelSuccess);
		
		stage.setScene(super.scene);
		
		stage.show();
	}
	
}