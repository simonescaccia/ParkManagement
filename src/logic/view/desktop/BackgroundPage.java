package logic.view.desktop;

import java.io.*;
import javafx.application.Application;

import javafx.event.EventHandler;
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

public abstract class BackgroundPage extends Application{

	protected VBox root;
	protected HBox lineButtons;
	protected HBox loginBox;
	protected HBox body;
	protected VBox info;
	protected VBox sideInfo;
	
	protected Label labelImg;
	
	protected Button buttonUndo;
	protected Button buttonRedo;
	protected Button buttonLogin;
	
	protected Font font;
	
	protected Background backB1;
	
	protected FileInputStream inBImg;
	protected FileInputStream inUndoImg;
	protected FileInputStream inRedoImg;
	protected FileInputStream inLoginImg;
	
	protected Label space;
	
	protected int n = 4; 
	protected Button[] bPages = new Button[n]; 
	protected String[] bNames = {"Attractions", "Your Reports", "Coupons", "Favourite"};
	
	protected Scene scene;
	
	@Override
	public void start(Stage stage) throws Exception{
		
	}
	
	protected BackgroundPage() throws FileNotFoundException {
		
		this.root = new VBox();
		this.lineButtons = new HBox();
		
		this.font = new Font("Comic Sans MS", 20);
		final String env= "user.dir";
		
		this.inBImg = new FileInputStream(System.getProperty(env)+"\\img\\backgroundImage.jpg");
		this.inUndoImg = new FileInputStream(System.getProperty(env)+"\\img\\undo2.png");
		this.inRedoImg = new FileInputStream(System.getProperty(env)+"\\img\\redo2.png");
		
		Image imgB = new Image(this.inBImg);
		Image imgU = new Image(this.inUndoImg);
		Image imgR = new Image(this.inRedoImg);
		
		ImageView imgVU = new ImageView(imgU);
		ImageView imgVR = new ImageView(imgR);
		
		this.labelImg = new Label();
		this.buttonUndo = new Button("", imgVU);
		this.buttonRedo = new Button("", imgVR);
		
		DropShadow shadow = new DropShadow();
		
		BackgroundSize bSize = new BackgroundSize(1366, 150, false, false, true, true); 
		BackgroundImage bImg = new BackgroundImage(imgB, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, bSize);
		Background back = new Background(bImg);
		this.labelImg.setBackground(back);
		this.labelImg.setMinSize(1366, 150);
		
		Color c = Color.rgb(142, 231, 199);
		BackgroundFill fill = new BackgroundFill(c,null,null);
		this.backB1 = new Background(fill);
		this.buttonUndo.setBackground(this.backB1);
		this.buttonUndo.setMinSize(60, 50);
		
		final Button button = this.buttonUndo; 
		
		this.buttonUndo.addEventHandler(MouseEvent.MOUSE_ENTERED,
	            new EventHandler<MouseEvent>() {
	              @Override
	              public void handle(MouseEvent e) {
	            	  button.setEffect(shadow);
	              }
	            });
		
		this.buttonUndo.addEventHandler(MouseEvent.MOUSE_EXITED,
	            new EventHandler<MouseEvent>() {
	              @Override
	              public void handle(MouseEvent e) {
	            	  button.setEffect(null);
	              }
	            });
		
		this.buttonRedo.setBackground(this.backB1);
		this.buttonRedo.setMinSize(60, 50);
		
		final Button button2 = this.buttonRedo;
		this.buttonRedo.addEventHandler(MouseEvent.MOUSE_ENTERED,
	            new EventHandler<MouseEvent>() {
	              @Override
	              public void handle(MouseEvent e) {
	            	  button2.setEffect(shadow);
	              }
	            });
		
		this.buttonRedo.addEventHandler(MouseEvent.MOUSE_EXITED,
	            new EventHandler<MouseEvent>() {
	              @Override
	              public void handle(MouseEvent e) {
	            	  button2.setEffect(null);
	              }
	            });
		
		this.space = new Label();
		this.space.setBackground(this.backB1);
		this.space.setMinSize(200, 50);
		
		int i = 0;
		for(; i<this.n; i++) {
			this.bPages[i] = new Button(this.bNames[i]);
			this.bPages[i].setBackground(backB1);
			this.bPages[i].setMinSize(200, 50);
			this.bPages[i].setFont(this.font);
			this.bPages[i].setTextFill(Color.BLACK);
			
			final Button button3 = bPages[i];
			
			this.bPages[i].addEventHandler(MouseEvent.MOUSE_ENTERED,
		            new EventHandler<MouseEvent>() {
		              @Override
		              public void handle(MouseEvent e) {
		            	  button3.setEffect(shadow);
		              }
		            });

			this.bPages[i].addEventHandler(MouseEvent.MOUSE_EXITED,
		            new EventHandler<MouseEvent>() {
		              @Override
		              public void handle(MouseEvent e) {
		            	  button3.setEffect(null);
		              }
		            });
			
		}
		
		this.loginBox = new HBox();
		this.loginBox.setBackground(this.backB1);
		this.loginBox.setMinSize(246, 50);
		
		this.inLoginImg = new FileInputStream(System.getProperty(env)+"\\img\\login2.jpg");
		Image imgL = new Image(this.inLoginImg);
		ImageView imgVL = new ImageView(imgL);
		
		
		this.buttonLogin = new Button("", imgVL);
		this.buttonLogin.setBackground(backB1);
		this.buttonLogin.setMaxWidth(176);
		this.buttonLogin.setMaxWidth(34);
		
		final Button button4 = this.buttonLogin;
		this.buttonLogin.addEventHandler(MouseEvent.MOUSE_ENTERED,
	            new EventHandler<MouseEvent>() {
	              @Override
	              public void handle(MouseEvent e) {
	            	  button4.setEffect(shadow);
	              }
	            });

		this.buttonLogin.addEventHandler(MouseEvent.MOUSE_EXITED,
	            new EventHandler<MouseEvent>() {
	              @Override
	              public void handle(MouseEvent e) {
	            	  button4.setEffect(null);
	              }
	            });
		
		
		this.body = new HBox();
		this.info = new VBox();
		this.sideInfo = new VBox();
		
		this.body.setMinSize(1366, 500);
		
		this.info.setMinSize(966, 500);
		
		this.sideInfo.setBackground(this.backB1);
		this.sideInfo.setMinSize(400, 500);
		
		this.body.getChildren().addAll(this.info, this.sideInfo);
		this.loginBox.getChildren().addAll(this.buttonLogin);
		this.lineButtons.getChildren().addAll(this.buttonUndo, this.buttonRedo,this.space,this.bPages[0],this.bPages[1],this.bPages[2],this.bPages[3], this.loginBox);
		this.root.getChildren().addAll(this.labelImg, this.lineButtons, this.body);
		
		this.scene = new Scene(this.root, 1366, 700);
	}
	
}
