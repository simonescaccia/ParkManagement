package logic.main;

import javafx.application.Application;
import javafx.stage.Stage;
import logic.boundary.desktop.controlgrafico.LoginGuiControl;
import logic.boundary.desktop.view.AttractionsView;

public class Main extends Application{

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setTitle("SpeedyFila");
		LoginGuiControl lGC = new LoginGuiControl();
		
		AttractionsView aV = new AttractionsView(lGC);
		
		aV.showScene(stage);
		
	}
	
	

}
