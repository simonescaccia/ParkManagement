package logic.boundary.desktop.view;

import java.io.*;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.boundary.desktop.controlgrafico.GenericGuiControl;

public abstract class GenericView{

	protected VBox root;
	protected HBox lineButtons;
	protected HBox loginBox;
	protected HBox body;
	protected VBox info;
	protected VBox sideInfo;
	protected HBox messageBox;
	
	//video ads -----
	protected VBox infoContainer;
	protected Button bVideoAds;
	protected Label lVideoAds;
	protected Button bCloseVideoAds;
	//-------
	
	protected ImageView imgVL;
	
	protected Label labelImg;
	
	protected Button buttonUndo;
	protected Button buttonRedo;
	protected Button buttonLogin;
	
	protected Label iconMessage;
	protected Label labelMessage;
	
	protected Font font;
	protected Font fontSide;
	protected Border blackBorder;
	protected Border greenBorder;
	protected Border greenBorder2;
	protected Color darkGreen;
	
	protected String styleHandCursor = "-fx-cursor: hand;";
	
	protected Background backB1;
	protected Background backB2;
	protected BackgroundFill fill;
	
	protected FileInputStream inBImg;
	protected FileInputStream inUndoImg;
	protected FileInputStream inRedoImg;
	protected FileInputStream inLoginImg;
	
	protected Label space;
	protected Label space2;
	
	protected int n = 3; 
	protected Button[] bPages = new Button[n]; 
	protected String[] bNames = {"Attractions", "Your Reports", "Coupons"};
	
	protected Scene scene;
	protected Stage stage;
	
	protected EventHandler<MouseEvent> loginEvent;
	protected EventHandler<MouseEvent> logoutEvent;
	
	protected static final String ENV= "user.dir";
	
	protected GenericGuiControl gGC;
	
	public Stage getStage() {
		return stage;
	}
	
	public GenericGuiControl getGenericGuiControl() {
		return gGC;
	}
	
	public Label getLabelMessage() {
		return labelMessage;
	}
	
	public Label getIconMessage() {
		return iconMessage;
	}
	
	protected GenericView() {
		
		root = new VBox();
		lineButtons = new HBox();
		
		infoContainer = new VBox();
		
		font = new Font("Comic Sans MS", 20);
		fontSide = new Font("Comic Sans MS", 16);
		blackBorder = new Border(new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,null,null));
		
		darkGreen = Color.rgb(0, 170, 109);
		greenBorder = new Border(new BorderStroke(darkGreen,BorderStrokeStyle.SOLID,null,null));
		greenBorder2 = new Border(new BorderStroke(darkGreen,BorderStrokeStyle.SOLID,null,BorderWidths.FULL));
		
		labelImg = new Label();
		
		//top page-------------------------
		//immagine in alto
		try {
			inBImg = new FileInputStream(System.getProperty(ENV)+"\\img\\backgroundImage.jpg");
			Image imgB = new Image(inBImg);
	
			BackgroundSize bSize = new BackgroundSize(1366, 150, false, false, true, true); 
			BackgroundImage bImg = new BackgroundImage(imgB, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, bSize);
			Background back = new Background(bImg);
			labelImg.setBackground(back);
			labelImg.setMinSize(1366, 150);
		} catch (FileNotFoundException e){
			labelImg.setFont(font);
			labelImg.setText("SpeedyFila");
		}
		
		//immagine button undo e redo
		try {
			inUndoImg = new FileInputStream(System.getProperty(ENV)+"\\img\\undo2.png");
			inRedoImg = new FileInputStream(System.getProperty(ENV)+"\\img\\redo2.png");
			
			Image imgU = new Image(inUndoImg);
			Image imgR = new Image(inRedoImg);
			
			ImageView imgVU = new ImageView(imgU);
			ImageView imgVR = new ImageView(imgR);
			
			buttonUndo = new Button("", imgVU);
			buttonRedo = new Button("", imgVR);
		} catch (FileNotFoundException e) {
			buttonUndo = new Button("Undo");
			buttonRedo = new Button("Redo");
		}
		
		Color c = Color.rgb(142, 231, 199);
		BackgroundFill fill2 = new BackgroundFill(c,null,null);
		this.fill = new BackgroundFill(darkGreen, null, null);
		backB1 = new Background(fill2);
		backB2 = new Background(this.fill);
		buttonUndo.setBackground(backB1);
		buttonUndo.setMinSize(60, 50);
		
		final Button button = buttonUndo; 
		
		buttonUndo.addEventHandler(MouseEvent.MOUSE_ENTERED, e-> 
	            button.setStyle(styleHandCursor)  );
		
		buttonUndo.addEventHandler(MouseEvent.MOUSE_EXITED, e-> 
  	  			button.setEffect(null)  );
		
		buttonRedo.setBackground(backB1);
		buttonRedo.setMinSize(60, 50);
		
		final Button button2 = buttonRedo;
		buttonRedo.addEventHandler(MouseEvent.MOUSE_ENTERED, e-> 
				button2.setStyle(styleHandCursor)  );
		
		buttonRedo.addEventHandler(MouseEvent.MOUSE_EXITED, e-> 
				button2.setEffect(null)  );
		
		space = new Label();
		space.setBackground(backB1);
		space.setMinSize(200, 50);
		
		space2 = new Label();
		space2.setBackground(backB1);
		space2.setMinSize(230, 50);
		
		int i = 0;
		for(; i<this.n; i++) {
			bPages[i] = new Button(bNames[i]);
			bPages[i].setBackground(backB1);
			bPages[i].setMinSize(200, 50);
			bPages[i].setFont(font);
			bPages[i].setTextFill(Color.BLACK);
			
			final Button button3 = bPages[i];
			
			bPages[i].addEventHandler(MouseEvent.MOUSE_ENTERED, e-> 
				button3.setStyle(styleHandCursor)  );

			bPages[i].addEventHandler(MouseEvent.MOUSE_EXITED, e-> 
				button3.setEffect(null)  );
			
		}
		
		bPages[0].addEventHandler(MouseEvent.MOUSE_CLICKED, e-> {
			AttractionsView aV = new AttractionsView(gGC.getLoginGuiControl());
			aV.showScene(stage);
		});
		
		bPages[1].addEventHandler(MouseEvent.MOUSE_CLICKED, e-> {
			InsertFeedbackView iRV = new InsertFeedbackView(gGC.getLoginGuiControl());
			iRV.showScene(stage);
		});
		
		bPages[2].addEventHandler(MouseEvent.MOUSE_CLICKED, e-> {
			ConvertCouponView cCV = new ConvertCouponView(gGC.getLoginGuiControl());
			cCV.showScene(stage);
		});
		
		loginBox = new HBox();
		loginBox.setBackground(backB1);
		loginBox.setMinSize(246, 50);
		
		try {
			inLoginImg = new FileInputStream(System.getProperty(ENV)+"\\img\\login3.PNG");
			Image imgL = new Image(inLoginImg);
			imgVL = new ImageView(imgL);
			buttonLogin = new Button("", imgVL);
		} catch (FileNotFoundException e) {
			buttonLogin = new Button("Log in");
		}
		
		loginEvent = e-> gGC.login();
		
		logoutEvent = e-> gGC.logout();
				
		buttonLogin.setBackground(backB1);
		buttonLogin.setMaxWidth(100);
		buttonLogin.setMaxHeight(34);
	
		final Button button4 = buttonLogin;
		buttonLogin.addEventHandler(MouseEvent.MOUSE_ENTERED, e-> 
			button4.setStyle(styleHandCursor)
		);
		
		buttonLogin.addEventHandler(MouseEvent.MOUSE_EXITED, e-> 
			button4.setEffect(null)  );
		
		buttonLogin.addEventHandler(MouseEvent.MOUSE_CLICKED, loginEvent);		
		
		body = new HBox();
		info = new VBox();
		sideInfo = new VBox();
		messageBox = new HBox();
		
		body.setMinSize(1366, 500);
		
		info.setMinSize(966, 500);
		
		sideInfo.setBackground(this.backB1);
		sideInfo.setMinSize(400, 500);
		
		iconMessage = new Label("");
		iconMessage.setPrefSize(40, 30);
		iconMessage.setPadding(new Insets(0,0,0,10));
		iconMessage.setTextFill(Color.BLACK);
		iconMessage.setFont(font);
		
		labelMessage = new Label();
		labelMessage.setPrefSize(330, 30);
		labelMessage.setPadding(new Insets(0,0,0,10));
		labelMessage.setTextFill(Color.BLACK);
		labelMessage.setFont(font);
		
		messageBox.getChildren().addAll(iconMessage, labelMessage);
		sideInfo.getChildren().addAll(messageBox);
		body.getChildren().addAll(info, sideInfo);
		loginBox.getChildren().addAll(buttonLogin);
		lineButtons.getChildren().addAll(buttonUndo, buttonRedo,space,bPages[0],bPages[1],bPages[2], space2, loginBox);
		root.getChildren().addAll(labelImg, lineButtons, body);
		
		
		//info ----------------------
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
		bCloseVideoAds = new Button("Close Video Ads");
		bCloseVideoAds.setBorder(blackBorder);
		bCloseVideoAds.setBackground(backB2);
		bCloseVideoAds.setPrefWidth(200);
		bCloseVideoAds.setPrefHeight(40);
		bCloseVideoAds.setFont(fontSide);
		bCloseVideoAds.setTextFill(Color.BLACK);
		
		bCloseVideoAds.setVisible(false);
		
		bCloseVideoAds.addEventHandler(MouseEvent.MOUSE_CLICKED, e-> 
			closeVideoAds()
		);
		bCloseVideoAds.addEventHandler(MouseEvent.MOUSE_ENTERED, e-> 
			bCloseVideoAds.setStyle(styleHandCursor)
		);
		bCloseVideoAds.addEventHandler(MouseEvent.MOUSE_EXITED, e-> 
			bCloseVideoAds.setEffect(null)  
		);
		
		//side info
		bVideoAds = new Button("Gain 1 coin");
		bVideoAds.setBorder(blackBorder);
		bVideoAds.setBackground(backB2);
		bVideoAds.setPrefWidth(120);
		bVideoAds.setPrefHeight(40);
		bVideoAds.setFont(fontSide);
		bVideoAds.setTextFill(Color.BLACK);
		
		bVideoAds.setVisible(false);
		
		bVideoAds.addEventHandler(MouseEvent.MOUSE_CLICKED, e-> {
			showVideoAds();
			bVideoAds.setVisible(false);
			gGC.showVideoAds();
		});
		bVideoAds.addEventHandler(MouseEvent.MOUSE_ENTERED, e-> 
			bVideoAds.setStyle(styleHandCursor)
		);
		bVideoAds.addEventHandler(MouseEvent.MOUSE_EXITED, e-> 
			bVideoAds.setEffect(null)  
		);
		
		scene = new Scene(root, 1366, 700);
	}
	
	public void showButtonVideoAds() {
		bVideoAds.setVisible(true);
	}
	
	public void showVideoAds() {
		info.getChildren().clear();
		info.getChildren().addAll(lVideoAds,bCloseVideoAds);
		
		lVideoAds.setVisible(true);
		bCloseVideoAds.setVisible(true);
	}
	
	public void closeVideoAds() {
		info.getChildren().clear();
		info.getChildren().addAll(infoContainer);		
	}
	
	public void loginOn() {
	
		buttonLogin.removeEventHandler(MouseEvent.MOUSE_CLICKED, loginEvent);
		buttonLogin.addEventHandler(MouseEvent.MOUSE_CLICKED, logoutEvent);
		buttonLogin.setTranslateY(0);
		buttonLogin.setTranslateX(0);
		buttonLogin.setGraphic(null);
		buttonLogin.setFont(font);
		buttonLogin.setMinSize(200, 50);
		buttonLogin.setTextFill(Color.BLACK);
		buttonLogin.setText("Sign out");	
	}
	
	public void loginOff() {
		
		buttonLogin.setMinSize(100, 34);
		buttonLogin.setMaxWidth(100);
		buttonLogin.setMaxHeight(34);
		buttonLogin.setTranslateY(10);
		buttonLogin.setTranslateX(10);
		buttonLogin.setGraphic(imgVL);
		buttonLogin.setText("");
		buttonLogin.removeEventHandler(MouseEvent.MOUSE_CLICKED, logoutEvent);
		buttonLogin.addEventHandler(MouseEvent.MOUSE_CLICKED, loginEvent);
	}
	
	public abstract void showScene(Stage stage);
}
