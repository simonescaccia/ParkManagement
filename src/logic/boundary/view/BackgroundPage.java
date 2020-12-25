package logic.boundary.view;

import java.io.*;
import javafx.application.Application;

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
		
		root = new VBox();
		lineButtons = new HBox();
		
		font = new Font("Comic Sans MS", 20);
		final String env= "user.dir";
		
		inBImg = new FileInputStream(System.getProperty(env)+"\\img\\backgroundImage.jpg");
		inUndoImg = new FileInputStream(System.getProperty(env)+"\\img\\undo2.png");
		inRedoImg = new FileInputStream(System.getProperty(env)+"\\img\\redo2.png");
		
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
		
		inLoginImg = new FileInputStream(System.getProperty(env)+"\\img\\login2.jpg");
		Image imgL = new Image(inLoginImg);
		ImageView imgVL = new ImageView(imgL);
		
		
		buttonLogin = new Button("", imgVL);
		buttonLogin.setBackground(backB1);
		buttonLogin.setMaxWidth(176);
		buttonLogin.setMaxWidth(34);
		
		final Button button4 = buttonLogin;
		buttonLogin.addEventHandler(MouseEvent.MOUSE_ENTERED, e-> 
			login()  );

		buttonLogin.addEventHandler(MouseEvent.MOUSE_EXITED, e-> 
			button4.setEffect(null)  );
		
		
		body = new HBox();
		info = new VBox();
		sideInfo = new VBox();
		
		body.setMinSize(1366, 500);
		
		info.setMinSize(966, 500);
		
		sideInfo.setBackground(this.backB1);
		sideInfo.setMinSize(400, 500);
		
		body.getChildren().addAll(info, sideInfo);
		loginBox.getChildren().addAll(buttonLogin);
		lineButtons.getChildren().addAll(buttonUndo, buttonRedo,space,bPages[0],bPages[1],bPages[2],bPages[3], loginBox);
		root.getChildren().addAll(labelImg, lineButtons, body);
		
		scene = new Scene(root, 1366, 700);
	}
	
	public abstract void login();
	
}
