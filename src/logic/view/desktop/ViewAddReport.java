package logic.view.desktop;

import java.io.FileNotFoundException;

import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

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
		
		Color c = Color.rgb(0, 170, 109);
		BackgroundFill fill = new BackgroundFill(c, null, null);
		Background backB2 = new Background(fill);
		super.bPages[1].setBackground(backB2);
		
		
		super.info.getChildren().addAll(labelSuccess);
		stage.setScene(super.scene);
		
		stage.show();
	}
	
}