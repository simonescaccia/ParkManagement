package logic.boundary.desktop.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.boundary.desktop.controlgrafico.ViewAttractionsGuiControl;
import logic.control.bean.ParkAttractionBean;

public final class AttractionsView extends GenericView{
	
	private String[] checkBoxName = {"Waiting time", "Distance", "Fun", "Restaurant", "Toilet", "Shop", "Photo Store"}; 
	private CheckBox[] cb = new CheckBox[checkBoxName.length];
	
	private Stage stage;
	
	public AttractionsView(){
		super.gGC = new ViewAttractionsGuiControl(this);
	}
	
	@Override
	public void start(Stage stage) {
		
		this.stage = stage;
		stage.setTitle("SpeedyFila");
		
		//pageButton background
		Color darkGreen = Color.rgb(0, 170, 109);
		BackgroundFill fill = new BackgroundFill(darkGreen, null, null);
		Background backB2 = new Background(fill);
		super.bPages[0].setBackground(backB2);
		
		//info
		
		//show attraction ordered by distance
		((ViewAttractionsGuiControl)gGC).showAttractions(null, null);

		//side info
		HBox superContainer = new HBox();
		VBox container = new VBox();
		HBox miniContainer = new HBox();
		VBox labels = new VBox();
		VBox checkBox = new VBox();
		HBox submit = new HBox();
		
		int i;
		for(i=0; i<checkBoxName.length; i++) {
			cb[i] = new CheckBox(checkBoxName[i]);
			cb[i].setIndeterminate(false);
			cb[i].setFont(fontSide);
			cb[i].setTextFill(Color.BLACK);
			cb[i].setPadding(new Insets(0,10,0,10));
			
			//event handler for checkbox group
			final CheckBox cb1 = cb[i];
			
			cb[i].addEventHandler(MouseEvent.MOUSE_CLICKED, e-> 
				verifyGroupCheckBox(cb1.getText())
			);
			
			checkBox.getChildren().addAll(cb[i]);
			
		}
		
		Label sort = new Label("Sort:");
		sort.setMinWidth(100);
		sort.setFont(fontSide);
		sort.setTextFill(Color.BLACK);
		Label space = new Label();
		space.setMinHeight(23);
		Label filter = new Label("Filter: ");
		filter.setMinWidth(100);
		filter.setFont(fontSide);
		filter.setTextFill(Color.BLACK);
		Label space1 = new Label();
		space1.setMinWidth(23);
		Label space2 = new Label();
		space2.setMinHeight(10);
		
		labels.getChildren().addAll(sort, space, filter);
		
		Button search = new Button("Search");
		search.setBorder(blackBorder);
		search.setBackground(backB2);
		search.setPrefWidth(110);
		search.setPrefHeight(40);
		search.setFont(fontSide);
		search.setTextFill(Color.BLACK);
		search.addEventHandler(MouseEvent.MOUSE_CLICKED, e->
			((ViewAttractionsGuiControl)gGC).showAttractions(getGroup1(), getGroup2())
		);	
		
		search.addEventHandler(MouseEvent.MOUSE_ENTERED, e-> 
			search.setStyle(styleHandCursor)
		);
	
		search.addEventHandler(MouseEvent.MOUSE_EXITED, e-> 
			search.setEffect(null)  
		);
		
		submit.getChildren().addAll(search);
		
		miniContainer.getChildren().addAll(labels, checkBox);
		container.getChildren().addAll(miniContainer, space2, submit);
		superContainer.getChildren().addAll(space1, container);
		
		super.sideInfo.getChildren().addAll(super.messageBox, superContainer);
		super.info.getChildren().addAll();
		stage.setScene(super.scene);
		
		stage.show();
	}
	
	protected void verifyGroupCheckBox(String text) {
		int i;
		for(i=0; i<checkBoxName.length;i++) {
			if(text.equals(checkBoxName[i])) {
				break;
			}
		}
		if(i<2) {
			//group 1
			cb[1-i].setSelected(false);
		} else {
			//group 2
			int j;
			for(j=2;j<checkBoxName.length; j++) {
				if(i!=j) {
					cb[j].setSelected(false);
				}
			}
		}		
	}
	
	protected String getGroup1() {
		if(cb[0].isSelected()) {
			return "waitingtime";
		} else {
			return "distance";
		}
	}
	
	protected String getGroup2() {
		int i;
		for(i=2; i<checkBoxName.length;i++) {
			if(cb[i].isSelected()) {
				return checkBoxName[i];
			}
		}
		return null;
	}
	
	public void showAttractions(List<ParkAttractionBean> list) {
		
		VBox spaceAttractionFull = new VBox();
		
		HBox spaceAttraction = new HBox();
		
		VBox listOfAttraction = new VBox();
		listOfAttraction.setSpacing(20);
		
		Label spaceTop = new Label();
		spaceTop.setMinSize(966, 20);
		
		Label spaceLeft = new Label();
		spaceLeft.setMinSize(20, 500);
		
		listOfAttraction.getChildren().addAll(spaceTop);
		
	    ScrollPane scrollPane = new ScrollPane(spaceAttractionFull);
	    scrollPane.setFitToWidth(true);
	    
		Iterator<ParkAttractionBean> i = list.iterator();
		while(i.hasNext()) {
			ParkAttractionBean pABean = i.next();
			
			HBox attraction = new HBox();
			attraction.setMinWidth(900);
			attraction.setMaxWidth(900);
			attraction.setBorder(greenBorder);
			attraction.setSpacing(30);
			//immagine
			Label labelImg = new Label();
			Image imgA = new Image(pABean.getImg());
	
			BackgroundSize bSize = new BackgroundSize(160, 120, false, false, true, true); 
			BackgroundImage bImg = new BackgroundImage(imgA, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, bSize);
			Background back = new Background(bImg);
			labelImg.setBackground(back);
			labelImg.setMinSize(160, 120);
			
			VBox attractionInfo = new VBox();
			Label spaceTopA = new Label();
			spaceTopA.setMinSize(10, 10);
			Label spaceTopA2 = new Label();
			spaceTopA2.setMinSize(10, 10);
			
			Button nameOfAttraction = new Button(pABean.getName());
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
			
			HBox attractionInfoBottom = new HBox();
			int j;
			int n = 6;
			Label[] listOfInformations = new Label[n];
			for(j = 0; j<n ;j++) {
				listOfInformations[j] = new Label();
				if(j%2 == 1) {
					listOfInformations[j].setMinWidth(170);
					listOfInformations[j].setAlignment(Pos.BOTTOM_LEFT);
				}
			}
			
			//icon clessidra
			try {
				FileInputStream fISC = new FileInputStream(System.getProperty(ENV)+"\\img\\clessidra.png");
				Image imgC = new Image(fISC);
		
				BackgroundSize bSizeC = new BackgroundSize(50, 50, false, false, true, true); 
				BackgroundImage bImgC = new BackgroundImage(imgC, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, bSizeC);
				Background backC = new Background(bImgC);
				listOfInformations[0].setBackground(backC);
				listOfInformations[0].setMinSize(50, 50);
			} catch (FileNotFoundException e){
				listOfInformations[0].setFont(font);
				listOfInformations[0].setText("Icon not found");
			}
			
			//waiting time
			listOfInformations[1].setFont(font);
			if(pABean.getWaitingTime() != -1) {
				listOfInformations[1].setText("" + pABean.getWaitingTime()+ " min");
			} else {
				listOfInformations[1].setText("no recent info");
			}
			
			
			//icona categoria
			Image imgC = new Image(pABean.getImgC());
			
			BackgroundSize bSizeC = new BackgroundSize(50, 50, false, false, true, true); 
			BackgroundImage bImgC = new BackgroundImage(imgC, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, bSizeC);
			Background backC = new Background(bImgC);
			listOfInformations[2].setBackground(backC);
			listOfInformations[2].setMinSize(50, 50);

			//labelCategoria
			listOfInformations[3].setFont(font);
			listOfInformations[3].setText(pABean.getCategoryName());
			
			//icon distance
			try {
				FileInputStream fISD = new FileInputStream(System.getProperty(ENV)+"\\img\\maps2.png");
				Image imgD = new Image(fISD);
		
				BackgroundSize bSizeD = new BackgroundSize(50, 50, false, false, true, true); 
				BackgroundImage bImgD = new BackgroundImage(imgD, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, bSizeD);
				Background backD = new Background(bImgD);
				listOfInformations[4].setBackground(backD);
				listOfInformations[4].setMinSize(50, 50);
			} catch (FileNotFoundException e){
				listOfInformations[4].setFont(font);
				listOfInformations[4].setText("Icon not found");
			}
			
			//distance in meters
			listOfInformations[5].setFont(font);
			listOfInformations[5].setText("" + pABean.getDistanceFromUser()+ " m");
			
			for(j=0; j<n ;j++) {
				attractionInfoBottom.getChildren().addAll(listOfInformations[j]);
			}
			
			attractionInfo.getChildren().addAll(spaceTopA, nameOfAttraction, spaceTopA2, attractionInfoBottom);
			attraction.getChildren().addAll(labelImg, attractionInfo);
			listOfAttraction.getChildren().addAll(attraction);
		}

		spaceAttraction.getChildren().addAll(spaceLeft, listOfAttraction);
		spaceAttractionFull.getChildren().addAll(spaceTop,spaceAttraction);
		
		super.info.getChildren().clear();
		super.info.getChildren().addAll(spaceAttractionFull, scrollPane);
	}
	
	public static void main(String[] args) {
		launch();
	}
	
}
