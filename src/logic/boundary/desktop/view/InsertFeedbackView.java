package logic.boundary.desktop.view;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.stage.Stage;
import logic.boundary.desktop.controlgrafico.InsertFeedbackGuiControl;
import logic.boundary.desktop.controlgrafico.LoginGuiControl;

public class InsertFeedbackView extends GenericView{

	public InsertFeedbackView(LoginGuiControl lGC) {
		super.gGC = new InsertFeedbackGuiControl(this);
		super.gGC.setLoginGuiControl(lGC);
	}
	
	@Override
	public void showScene(Stage stage) {
		
		super.stage = stage;
		
		//pageButton background
		BackgroundFill fill = new BackgroundFill(darkGreen, null, null);
		Background backB2 = new Background(fill);
		super.bPages[1].setBackground(backB2);
		
		//info
		//show reports
		((InsertFeedbackGuiControl)super.gGC).showReports();
			
		stage.setScene(super.scene);
		
		stage.show();
	}
	
}
