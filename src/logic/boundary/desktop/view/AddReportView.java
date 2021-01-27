package logic.boundary.desktop.view;

import logic.boundary.desktop.controlgrafico.AddReportGuiControl;
import logic.boundary.desktop.controlgrafico.LoginGuiControl;
import logic.boundary.desktop.controlgrafico.ViewAttractionsGuiControl;
import logic.control.bean.ParkAttractionBean;
import logic.control.bean.ReportBean;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.Background;
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

import java.util.Iterator;

import javafx.geometry.Insets;

public final class AddReportView extends GenericView{
	
	private String nomeAttrazione;
	
	private CheckBox cb;
	private TextField tf;
	
	private Label labelAttraction;
	
	
	public AddReportView(String nA, LoginGuiControl lGControl){
		super.gGC = new AddReportGuiControl(this);
		super.gGC.setLoginGuiControl(lGControl);
		nomeAttrazione = nA;
	}
	
	public void insertReport() {		
		((AddReportGuiControl)gGC).insertQueueLenght(labelAttraction.getText(),tf.getText(),cb.isSelected());
	}
	
	public void showScene(Stage stage){
				
		super.stage = stage;
		
		if(super.gGC.getLoginGuiControl().getLoginControl().getLoginBean().getUserID() != null) {
			super.loginOn();
		}
		
		VBox addReport = new VBox();
		HBox insertReport = new HBox();
		
		Font fontSide2 = new Font("Comic Sans MS", 17);
		
		Border greenBorder = new Border(new BorderStroke((darkGreen),BorderStrokeStyle.SOLID,null,null));
		
		
		//pageButton background
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
				
		insertReport.getChildren().addAll(tf,bAddReport,super.bVideoAds);
		addReport.getChildren().addAll(textAddReport,cb,insertReport);
		super.sideInfo.getChildren().addAll(addReport);
		
		//info
		labelAttraction = new Label();
		ViewAttractionsGuiControl vAGC = new ViewAttractionsGuiControl(this);
		vAGC.showAttractionInformation(nomeAttrazione);
		
		stage.setScene(super.scene);

	}
	
	public void showattractionInfo(ParkAttractionBean pAB) {
		
		//info --------------------------
		VBox container = new VBox();
		HBox title = new HBox();
		HBox superContainer = new HBox();
		
		Label space = new Label();
		space.setMinWidth(20);
		Label space2 = new Label();
		space2.setMinHeight(20);
		
		Label labelAttraction0 = new Label();
		labelAttraction0.setText("Attractions >");
		labelAttraction0.setFont(font);
		labelAttraction0.setMinWidth(130);
		
		labelAttraction.setFont(font);
		labelAttraction.setMinWidth(520);
		labelAttraction.setText(pAB.getName());
		
		//icona categoria
		Label icona = new Label();
		Image imgC = new Image(pAB.getImgC());
		
		BackgroundSize bSizeC = new BackgroundSize(50, 50, false, false, true, true); 
		BackgroundImage bImgC = new BackgroundImage(imgC, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, bSizeC);
		Background backC = new Background(bImgC);
		icona.setBackground(backC);
		icona.setMinSize(50, 50);
		
		//immagine attrazione
		Label img = new Label();
		Image imgA = new Image(pAB.getImg());
		
		BackgroundImage bImgA = new BackgroundImage(imgA, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, BackgroundSize.DEFAULT);
		Background backA = new Background(bImgA);
		img.setBackground(backA);
		img.setMinSize(700,430);
		
		title.getChildren().addAll(labelAttraction0, labelAttraction, icona);
		container.getChildren().addAll(space2, title, img);
		superContainer.getChildren().addAll(space, container);
		infoContainer.getChildren().addAll(superContainer);
		super.info.getChildren().addAll(infoContainer);
		
		//side info ---------------------------
		// last Report
		Label space3 = new Label();
		space3.setMinHeight(50);
		Label lastReport = new Label("Last Report");
		lastReport.setFont(fontSide);
		lastReport.setTextFill(Color.BLACK);
		
		HBox lastReportsContainer = new HBox();
		VBox lastReports = new VBox();
	    ScrollPane scrollPane = new ScrollPane(lastReports);
	    scrollPane.setFitToWidth(true);
	    
	    Label spaceLeft = new Label();
	    spaceLeft.setMinWidth(10);
	 
	    lastReports.getChildren().addAll(lastReport);
	    lastReportsContainer.getChildren().addAll(spaceLeft, lastReports);
	    
	    Iterator<ReportBean> i = pAB.getListOfReports().iterator();
		while(i.hasNext()) {
			ReportBean rB = i.next();
			Label report = new Label();
			report.setMinWidth(300);
			report.setFont(fontSide);
			report.setText(rB.getDate()+", len: "+rB.getLenQueue());
			
			lastReports.getChildren().addAll(report);
		}
		
		super.sideInfo.getChildren().addAll(space3, lastReportsContainer);
	}
	
	
	
}