package logic.boundary.desktop.view;

import logic.boundary.desktop.controlgrafico.AddReportGuiControl;
import logic.boundary.desktop.controlgrafico.LoginGuiControl;
import logic.boundary.desktop.controlgrafico.ViewAttractionsGuiControl;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;

public final class AddReportView extends GenericView{
	
	private String nomeAttrazione;
	
	private CheckBox cb;
	private TextField tf;
	private Label labelAttraction;
	
	private Button showBVideoAds;
	private Label lVideoAds;
	private Button bVideoAds;
	
	private VBox infoContainer;
	
	
	public AddReportView(String nA, LoginGuiControl lGControl){
		super.gGC = new AddReportGuiControl(this);
		super.gGC.setLoginGuiControl(lGControl);
		nomeAttrazione = nA;
		infoContainer = new VBox();
	}
	
	public void insertReport() {		
		((AddReportGuiControl)gGC).insertQueueLenght(labelAttraction.getText(),tf.getText(),cb.isSelected());
	}

	public void showButtonVideoAds() {
		showBVideoAds.setVisible(true);
	}
	
	public void showVideoAds() {
		infoContainer.setVisible(false);
		lVideoAds.setVisible(true);
		bVideoAds.setVisible(true);
	}
	
	public void closeVideoAds() {
		lVideoAds.setVisible(false);
		bVideoAds.setVisible(false);
		super.info.setVisible(true);
	}
	
	public void showScene(Stage stage){
				
		VBox addReport = new VBox();
		HBox insertReport = new HBox();
		
		Font fontSide2 = new Font("Comic Sans MS", 17);
		
		Border greenBorder = new Border(new BorderStroke((darkGreen),BorderStrokeStyle.SOLID,null,null));
		
		
		//pageButton background
		BackgroundFill fill = new BackgroundFill(darkGreen, null, null);
		Background backB2 = new Background(fill);
		super.bPages[0].setBackground(backB2);
		
		
		//info
		ViewAttractionsGuiControl vAGC = new ViewAttractionsGuiControl(this);
		vAGC.showAttractionInformation(nomeAttrazione);
		
		//immagine videoAds
		lVideoAds = new Label();
		try {
			FileInputStream vAds = new FileInputStream(System.getProperty(ENV)+"\\img\\videoAds.png");
			Image imgVAds = new Image(vAds);
	
			BackgroundSize bSize = new BackgroundSize(500, 400, false, false, true, true); 
			BackgroundImage bImg = new BackgroundImage(imgVAds, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, bSize);
			Background back = new Background(bImg);
			lVideoAds.setBackground(back);
			lVideoAds.setMinSize(700, 400);
		} catch (FileNotFoundException e){
			lVideoAds.setFont(font);
			lVideoAds.setText("VideoAds");
		}
		lVideoAds.setVisible(false);
		
		//button close videoAds
		bVideoAds = new Button("Close Video Ads");
		bVideoAds.setBorder(blackBorder);
		bVideoAds.setBackground(backB2);
		bVideoAds.setPrefWidth(200);
		bVideoAds.setPrefHeight(40);
		bVideoAds.setFont(fontSide);
		bVideoAds.setTextFill(Color.BLACK);
		
		bVideoAds.setVisible(false);
		
		bVideoAds.addEventHandler(MouseEvent.MOUSE_CLICKED, e-> 
			closeVideoAds()
		);
		bVideoAds.addEventHandler(MouseEvent.MOUSE_ENTERED, e-> 
			bVideoAds.setStyle(styleHandCursor)
		);
		bVideoAds.addEventHandler(MouseEvent.MOUSE_EXITED, e-> 
			bVideoAds.setEffect(null)  
		);
		
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
		bAddReport.addEventHandler(MouseEvent.MOUSE_ENTERED, e-> 
			bAddReport.setStyle(styleHandCursor)
				);
		bAddReport.addEventHandler(MouseEvent.MOUSE_EXITED, e-> 
			bAddReport.setEffect(null)  
				);
		
		tf.setBorder(greenBorder);
		tf.setPrefWidth(50);
		tf.setPrefHeight(40);
		bAddReport.setBorder(blackBorder);
		bAddReport.setBackground(backB2);
		bAddReport.setPrefWidth(120);
		bAddReport.setPrefHeight(40);
		bAddReport.setFont(fontSide);
		bAddReport.setTextFill(Color.BLACK);
		
		showBVideoAds = new Button("Gain 1 coin");
		showBVideoAds.setBorder(blackBorder);
		showBVideoAds.setBackground(backB2);
		showBVideoAds.setPrefWidth(120);
		showBVideoAds.setPrefHeight(40);
		showBVideoAds.setFont(fontSide);
		showBVideoAds.setTextFill(Color.BLACK);
		
		showBVideoAds.setVisible(false);
		
		showBVideoAds.addEventHandler(MouseEvent.MOUSE_CLICKED, e-> {
			showVideoAds();
			showBVideoAds.setVisible(false);
			((AddReportGuiControl)super.gGC).showVideoAds();
		});
		showBVideoAds.addEventHandler(MouseEvent.MOUSE_ENTERED, e-> 
			showBVideoAds.setStyle(styleHandCursor)
		);
		showBVideoAds.addEventHandler(MouseEvent.MOUSE_EXITED, e-> 
			showBVideoAds.setEffect(null)  
		);
		
		
		if(super.gGC.getLoginGuiControl().getLoginControl().getLoginBean().getUserID() != null) {
			super.loginOn();
		}
		
		insertReport.getChildren().addAll(tf,bAddReport,showBVideoAds);
		addReport.getChildren().addAll(textAddReport,cb,insertReport);
		super.sideInfo.getChildren().addAll(super.messageBox,addReport);
		infoContainer.getChildren().addAll(labelAttraction);
		super.info.getChildren().addAll(infoContainer,lVideoAds,bVideoAds);
		stage.setScene(super.scene);

	}
	
}