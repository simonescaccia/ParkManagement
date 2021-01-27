package logic.boundary.desktop.view;

import java.util.Iterator;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.boundary.desktop.controlgrafico.InsertFeedbackGuiControl;
import logic.boundary.desktop.controlgrafico.LoginGuiControl;
import logic.control.bean.ReportBean;

public class InsertFeedbackView extends GenericView{

	private String lNameOfAttraction;
	private String lDate;
	private TextField textField;
	
	public InsertFeedbackView(LoginGuiControl lGC) {
		super.gGC = new InsertFeedbackGuiControl(this);
		super.gGC.setLoginGuiControl(lGC);
	}
	
	@Override
	public void showScene(Stage stage) {
		
		super.stage = stage;
		
		if(super.gGC.getLoginGuiControl().getLoginControl().getLoginBean().getUserID() != null) {
			super.loginOn();
		}
		
		//pageButton background
		super.bPages[1].setBackground(backB2);
		
		//info-------------------------------------
		
		//show reports
		((InsertFeedbackGuiControl)super.gGC).showReports();
		
		super.sideInfo.getChildren().addAll(bVideoAds);
		
		stage.setScene(super.scene);
		
		stage.show();
	}
	
	protected void insertFeedback(){
		((InsertFeedbackGuiControl)super.gGC).insertFeedback(textField.getText(), lNameOfAttraction, lDate);
	}
	
	public void showReports(List<ReportBean> listOfReports) {
		
		VBox superReportContainer = new VBox();
		
		HBox reportContainer = new HBox();
		
		VBox listOfReport = new VBox();
		listOfReport.setSpacing(20);
		
		Label spaceTop = new Label();
		spaceTop.setMinSize(966, 20);
		
		Label spaceLeft = new Label();
		spaceLeft.setMinSize(20, 500);
		
		superReportContainer.getChildren().addAll(spaceTop);
		
	    ScrollPane scrollPane = new ScrollPane(superReportContainer);
	    scrollPane.setFitToWidth(true);
	    
		Iterator<ReportBean> i = listOfReports.iterator();
		while(i.hasNext()) {
			ReportBean rBean = i.next();
			
			HBox report = new HBox();
			report.setMinWidth(900);
			report.setMaxWidth(900);
			report.setBorder(greenBorder);
			report.setSpacing(30);
			//immagine
			Label labelImg = new Label();
			Image imgR = new Image(rBean.getParkAttraction().getImg());
	
			BackgroundSize bSize = new BackgroundSize(160, 120, false, false, true, true); 
			BackgroundImage bImg = new BackgroundImage(imgR, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, bSize);
			Background back = new Background(bImg);
			labelImg.setBackground(back);
			labelImg.setMinSize(160, 120);
			
			VBox reportInfo = new VBox();
			Label spaceTopA = new Label();
			spaceTopA.setMinSize(10, 10);
			Label spaceTopA2 = new Label();
			spaceTopA2.setMinSize(10, 10);
			
			Button nameOfAttraction = new Button(rBean.getParkAttraction().getName());
			nameOfAttraction.setFont(font);
			nameOfAttraction.setBackground(Background.EMPTY);
			nameOfAttraction.setPadding(new Insets(0));
			nameOfAttraction.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
				AddReportView addReportView = new AddReportView(nameOfAttraction.getText(), gGC.getLoginGuiControl());
				addReportView.showScene(stage);
			});	
			nameOfAttraction.addEventHandler(MouseEvent.MOUSE_ENTERED, e-> 
				nameOfAttraction.setStyle(styleHandCursor)
					);
			nameOfAttraction.addEventHandler(MouseEvent.MOUSE_EXITED, e-> 
				nameOfAttraction.setEffect(null)  
			);
			
			//feedback view
			HBox reportInfoBottom = new HBox();
			Button insertFeedback = new Button("Insert Feedback");
			
			insertFeedback.addEventHandler(MouseEvent.MOUSE_CLICKED, e-> 
					//inserisci il feedback
					insertFeedback()
					);
				insertFeedback.addEventHandler(MouseEvent.MOUSE_ENTERED, e-> 
			insertFeedback.setStyle(styleHandCursor)
					);
			insertFeedback.addEventHandler(MouseEvent.MOUSE_EXITED, e-> 
				insertFeedback.setEffect(null)  
					);
			
			insertFeedback.setBorder(blackBorder);
			insertFeedback.setBackground(backB2);
			insertFeedback.setPrefHeight(40);
			insertFeedback.setFont(fontSide);
			insertFeedback.setTextFill(Color.BLACK);
			
			Label label = new Label("    Date: ");
			
			Label date = new Label();
			date.setFont(font);
			date.setText("" + rBean.getDate());
			
			TextField tf = new TextField("insert the time in minutes");
			tf.setBorder(greenBorder);
			tf.setPrefWidth(200);
			tf.setPrefHeight(40);
			tf.setOnMouseClicked(e-> {
				tf.setText("");
				textField = tf;
				lNameOfAttraction = nameOfAttraction.getText();
				lDate = date.getText();
				super.iconMessage.setGraphic(null);
				super.labelMessage.setText("");
			});
			
			reportInfoBottom.getChildren().addAll(tf, insertFeedback, label, date);
			
			reportInfo.getChildren().addAll(spaceTopA, nameOfAttraction, spaceTopA2, reportInfoBottom);
			report.getChildren().addAll(labelImg, reportInfo);
			listOfReport.getChildren().addAll(report);
		}

		reportContainer.getChildren().addAll(spaceLeft, listOfReport);
		superReportContainer.getChildren().addAll(reportContainer);
		
		infoContainer.getChildren().addAll(superReportContainer, scrollPane);
		super.info.getChildren().addAll(infoContainer);
		
	}
	
}
