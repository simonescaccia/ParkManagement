package logic.boundary.desktop.view;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.boundary.desktop.controlgrafico.AttractionsGuiControl;

public final class AttractionsView extends GenericView{
	
	public AttractionsView(){
		super.gGC = new AttractionsGuiControl(this);
	}
	
	@Override
	public void start(Stage stage) {
		
		stage.setTitle("SpeedyFila");
		
		//pageButton background
		Color darkGreen = Color.rgb(0, 170, 109);
		BackgroundFill fill = new BackgroundFill(darkGreen, null, null);
		Background backB2 = new Background(fill);
		super.bPages[0].setBackground(backB2);
		
		//info
		Button nomeAttrazioneButton = new Button("Nome attrazione");
		nomeAttrazioneButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
			AddReportView addReportView = new AddReportView(nomeAttrazioneButton.getText(), gGC.getLoginBean());
			addReportView.showScene(stage);
		});	
		

		super.sideInfo.getChildren().addAll(super.messageBox);
		super.info.getChildren().addAll(nomeAttrazioneButton);
		stage.setScene(super.scene);
		
		stage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
	
}
