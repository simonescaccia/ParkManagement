package logic.boundary.desktop.view;

import java.io.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.boundary.desktop.controlgrafico.GenericGuiControl;

public abstract class GenericView extends Application{

	protected VBox root;
	protected HBox lineButtons;
	protected HBox loginBox;
	protected HBox body;
	protected VBox info;
	protected VBox sideInfo;
	protected HBox messageBox;
	
	protected ImageView imgVL;
	
	protected Label labelImg;
	
	protected Button buttonUndo;
	protected Button buttonRedo;
	protected Button buttonLogin;
	
	protected Label iconMessage;
	protected Label labelMessage;
	
	protected Font font;
	
	protected Background backB1;
	
	protected FileInputStream inBImg;
	protected FileInputStream inUndoImg;
	protected FileInputStream inRedoImg;
	protected FileInputStream inLoginImg;
	
	protected Label space;
	protected Label space2;
	
	protected int n = 4; 
	protected Button[] bPages = new Button[n]; 
	protected String[] bNames = {"Attractions", "Your Reports", "Coupons", "Favourite"};
	
	protected Scene scene;
	
	protected EventHandler<MouseEvent> loginEvent;
	protected EventHandler<MouseEvent> logoutEvent;
	
	protected GenericGuiControl gGC;
	
	public Label getLabelMessage() {
		return labelMessage;
	}
	
	public Label getIconMessage() {
		return iconMessage;
	}
	
	@Override
	public void start(Stage stage) throws Exception{
		
	}
	
	protected GenericView() {
		
		root = new VBox();
		lineButtons = new HBox();
		
		font = new Font("Comic Sans MS", 20);
		final String env= "user.dir";
		
		try {
			inBImg = new FileInputStream(System.getProperty(env)+"\\img\\backgroundImage.jpg");
			inUndoImg = new FileInputStream(System.getProperty(env)+"\\img\\undo2.png");
			inRedoImg = new FileInputStream(System.getProperty(env)+"\\img\\redo2.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
			
		Image imgB = new Image(inBImg);
		Image imgU = new Image(inUndoImg);
		Image imgR = new Image(inRedoImg);
		
		ImageView imgVU = new ImageView(imgU);
		ImageView imgVR = new ImageView(imgR);
		
		labelImg = new Label();
		buttonUndo = new Button("", imgVU);
		buttonRedo = new Button("", imgVR);
		
		DropShadow shadow = new DropShadow();
		
		BackgroundSize bSize = new BackgroundSize(1366, 150, false, false, true, true); 
		BackgroundImage bImg = new BackgroundImage(imgB, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, bSize);
		Background back = new Background(bImg);
		labelImg.setBackground(back);
		labelImg.setMinSize(1366, 150);
		
		Color c = Color.rgb(142, 231, 199);
		BackgroundFill fill = new BackgroundFill(c,null,null);
		backB1 = new Background(fill);
		buttonUndo.setBackground(backB1);
		buttonUndo.setMinSize(60, 50);
		
		final Button button = buttonUndo; 
		
		buttonUndo.addEventHandler(MouseEvent.MOUSE_ENTERED, e-> 
	            button.setEffect(shadow)  );
		
		buttonUndo.addEventHandler(MouseEvent.MOUSE_EXITED, e-> 
  	  			button.setEffect(null)  );
		
		buttonRedo.setBackground(backB1);
		buttonRedo.setMinSize(60, 50);
		
		final Button button2 = buttonRedo;
		buttonRedo.addEventHandler(MouseEvent.MOUSE_ENTERED, e-> 
				button2.setEffect(shadow)  );
		
		buttonRedo.addEventHandler(MouseEvent.MOUSE_EXITED, e-> 
				button2.setEffect(null)  );
		
		space = new Label();
		space.setBackground(backB1);
		space.setMinSize(200, 50);
		
		space2 = new Label();
		space2.setBackground(backB1);
		space2.setMinSize(30, 50);
		
		int i = 0;
		for(; i<this.n; i++) {
			bPages[i] = new Button(bNames[i]);
			bPages[i].setBackground(backB1);
			bPages[i].setMinSize(200, 50);
			bPages[i].setFont(font);
			bPages[i].setTextFill(Color.BLACK);
			
			final Button button3 = bPages[i];
			
			bPages[i].addEventHandler(MouseEvent.MOUSE_ENTERED, e-> 
				button3.setEffect(shadow)  );

			bPages[i].addEventHandler(MouseEvent.MOUSE_EXITED, e-> 
				button3.setEffect(null)  );
			
		}
		
		loginBox = new HBox();
		loginBox.setBackground(backB1);
		loginBox.setMinSize(246, 50);
		
		try {
			inLoginImg = new FileInputStream(System.getProperty(env)+"\\img\\login3.PNG");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Image imgL = new Image(inLoginImg);
		imgVL = new ImageView(imgL);
		
		loginEvent = e-> login();
		
		logoutEvent = e-> logout();
		
		buttonLogin = new Button("", imgVL);
		buttonLogin.setBackground(backB1);
		buttonLogin.setMaxWidth(100);
		buttonLogin.setMaxHeight(34);
	
		final Button button4 = buttonLogin;
		buttonLogin.addEventHandler(MouseEvent.MOUSE_ENTERED, e-> 
			button4.setStyle("-fx-cursor: hand;")
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
		body.getChildren().addAll(info, sideInfo);
		loginBox.getChildren().addAll(buttonLogin);
		lineButtons.getChildren().addAll(buttonUndo, buttonRedo,space,bPages[0],bPages[1],bPages[2],bPages[3], space2, loginBox);
		root.getChildren().addAll(labelImg, lineButtons, body);
		
		scene = new Scene(root, 1366, 700);
	}
	
	public void login() {
		gGC.login(this);
	}
	
	public void logout() {
		gGC.logout(this);
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
}
