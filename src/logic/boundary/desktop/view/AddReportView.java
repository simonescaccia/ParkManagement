package logic.boundary.desktop.view;

import logic.boundary.desktop.controlgrafico.AddReportGuiControl;
import logic.control.bean.LoginBean;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;

public final class AddReportView extends GenericView{
	
	private String nomeAttrazione;
	
	private CheckBox cb;
	private TextField tf;
	private Label labelAttraction;
	
	public AddReportView(String nA, LoginBean lB){
		super.gGC = new AddReportGuiControl(this);
		super.gGC.setLoginBean(lB);
		nomeAttrazione = nA;
	}
	
	public void insertReport() {		
		((AddReportGuiControl)gGC).insertQueueLenght(labelAttraction.getText(),tf.getText(),cb.isSelected());
	}

	public void showScene(Stage stage){
				
		VBox addReport = new VBox();
		HBox insertReport = new HBox();
		
		Font fontSide = new Font("Comic Sans MS", 16);
		Font fontSide2 = new Font("Comic Sans MS", 17);
		
		Color darkGreen = Color.rgb(0, 170, 109);
		Border blackBorder = new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,null,null));
		Border greenBorder = new Border(new BorderStroke((darkGreen),BorderStrokeStyle.SOLID,null,null));
		
		labelAttraction = new Label(nomeAttrazione);
		
		//pageButton background
		BackgroundFill fill = new BackgroundFill(darkGreen, null, null);
		Background backB2 = new Background(fill);
		super.bPages[0].setBackground(backB2);
		
		//sideInfo

		//use case Add Report
		Label textAddReport = new Label("How many people are there in the queue?");
		textAddReport.setMinSize(380, 30);
		textAddReport.setFont(fontSide2);
		textAddReport.setTextFill(Color.BLACK);
		textAddReport.setPadding(new Insets(0,10,0,10));
		
		cb = new CheckBox("I'm the last of the queue");
		cb.setIndeterminate(false);
		cb.setFont(fontSide);
		cb.setTextFill(Color.BLACK);
		cb.setPadding(new Insets(0,10,0,10));
		
		insertReport.setPadding(new Insets(5,10,0,10));
		
		tf = new TextField();
		Button bAddReport = new Button("Add Report");
		
		bAddReport.addEventHandler(MouseEvent.MOUSE_CLICKED, e-> 
				insertReport());
		
		tf.setBorder(greenBorder);
		tf.setPrefWidth(50);
		tf.setPrefHeight(40);
		bAddReport.setBorder(blackBorder);
		bAddReport.setBackground(backB2);
		bAddReport.setPrefWidth(120);
		bAddReport.setPrefHeight(40);
		bAddReport.setFont(fontSide);
		bAddReport.setTextFill(Color.BLACK);
		
		if(gGC.getLoginBean().getUserID() != null) {
			super.loginOn();
		}
		
		insertReport.getChildren().addAll(tf,bAddReport);
		addReport.getChildren().addAll(textAddReport,cb,insertReport);
		super.sideInfo.getChildren().addAll(super.messageBox,addReport);
		super.info.getChildren().addAll(labelAttraction);
		stage.setScene(super.scene);

	}
	
}