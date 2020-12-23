package logic.view.desktop;

import logic.bean.AddReportBean;
import logic.bean.LoginBean;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

public final class AddReportView extends BackgroundPage{

	private Label labelMessage;
	private AddReportBean beanAddR;
	private LoginBean loginBean;
	private Label iconMessage;
	
	public AddReportView() throws FileNotFoundException{
		iconMessage = new Label("");
		iconMessage.setPrefSize(40, 30);
		iconMessage.setPadding(new Insets(0,0,0,10));
		iconMessage.setTextFill(Color.BLACK);
		iconMessage.setFont(super.font);
		
		labelMessage = new Label();
		labelMessage.setPrefSize(330, 30);
		labelMessage.setPadding(new Insets(0,0,0,10));
		labelMessage.setTextFill(Color.BLACK);
		labelMessage.setFont(super.font);
		
		beanAddR = new AddReportBean();
	}
	
	public void insertReport(String queueLen, boolean isTheLast, String attraction) {
		beanAddR.setAttraction(attraction);
		beanAddR.setQueueLen(queueLen);
		beanAddR.setIsLast(isTheLast);
		beanAddR.setGui(this);
		beanAddR.addReport();
	}

	public void showMessage(String message,boolean type) {
		String env = "user.dir";
		try {
			FileInputStream iconM;
			if(type) {
				iconM = new FileInputStream(System.getProperty(env)+"\\img\\success-icon2.png");
			} else {
				iconM = new FileInputStream(System.getProperty(env)+"\\img\\error-flat2.png");
			}
			Image imgI = new Image(iconM);
			ImageView imgIV = new ImageView(imgI);
			this.iconMessage.setGraphic(imgIV);
		} catch (FileNotFoundException e) {
			if(type) {
				this.iconMessage.setText("OK!");
			} else {
				this.iconMessage.setText("Err");
			}
		}
		labelMessage.setText(message);
		
	}
	
	@Override
	public void start(Stage stage) throws Exception{
		
		stage.setTitle("SpeedyFila");
		
		VBox addReport = new VBox();
		HBox insertReport = new HBox();
		HBox messageBox = new HBox();
		
		Font fontSide = new Font("Comic Sans MS", 16);
		Font fontSide2 = new Font("Comic Sans MS", 17);
		
		Color darkGreen = Color.rgb(0, 170, 109);
		Border blackBorder = new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,null,null));
		Border greenBorder = new Border(new BorderStroke((darkGreen),BorderStrokeStyle.SOLID,null,null));
		
		 Label labelAttraction = new Label("Nome dell'attrazione");
		
		//pageButton background
		BackgroundFill fill = new BackgroundFill(darkGreen, null, null);
		Background backB2 = new Background(fill);
		super.bPages[1].setBackground(backB2);
		
		//sideInfo

		
		//use case Add Report
		Label textAddReport = new Label("How many people are there in the queue?");
		textAddReport.setMinSize(380, 30);
		textAddReport.setFont(fontSide2);
		textAddReport.setTextFill(Color.BLACK);
		textAddReport.setPadding(new Insets(0,10,0,10));
		
		CheckBox cb = new CheckBox("I'm the last in the queue");
		cb.setIndeterminate(false);
		cb.setFont(fontSide);
		cb.setTextFill(Color.BLACK);
		cb.setPadding(new Insets(0,10,0,10));
		
		insertReport.setPadding(new Insets(5,10,0,10));
		
		TextField tf = new TextField();
		Button bAddReport = new Button("Add Report");
		
		bAddReport.addEventHandler(MouseEvent.MOUSE_CLICKED, e-> 
				insertReport(tf.getText(), cb.isSelected(), labelAttraction.getText())  );
		
		tf.setBorder(greenBorder);
		tf.setPrefWidth(50);
		tf.setPrefHeight(40);
		bAddReport.setBorder(blackBorder);
		bAddReport.setBackground(backB2);
		bAddReport.setPrefWidth(120);
		bAddReport.setPrefHeight(40);
		bAddReport.setFont(fontSide);
		bAddReport.setTextFill(Color.BLACK);
		
		
		messageBox.getChildren().addAll(iconMessage, labelMessage);
		insertReport.getChildren().addAll(tf,bAddReport);
		addReport.getChildren().addAll(textAddReport,cb,insertReport);
		super.sideInfo.getChildren().addAll(messageBox,addReport);
		super.info.getChildren().addAll(labelAttraction);
		stage.setScene(super.scene);
		
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void login() {
		
	}
}