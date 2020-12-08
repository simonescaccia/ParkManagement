package view;

import java.io.*;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;

public class Home extends Application{

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception{
		
		stage.setTitle("SpeedyFila");
		stage.setScene(homeScene());
		
		
		stage.show();
	}
	
	protected Scene homeScene() throws FileNotFoundException{
		VBox root = new VBox();
		HBox lineButtons = new HBox();
		
		Scene scene = new Scene(root, 1366, 700);
		
		Font font = new Font("Comic Sans MS", 20);
		
		FileInputStream inBImg = new FileInputStream(System.getProperty("user.dir")+"\\img\\backgroundImage.jpg");
		FileInputStream inUndoImg = new FileInputStream(System.getProperty("user.dir")+"\\img\\undo2.png");
		FileInputStream inRedoImg = new FileInputStream(System.getProperty("user.dir")+"\\img\\redo2.png");
		
		Image imgB = new Image(inBImg);
		Image imgU = new Image(inUndoImg);
		Image imgR = new Image(inRedoImg);
		
		ImageView imgVU = new ImageView(imgU);
		ImageView imgVR = new ImageView(imgR);
		
		Label labelImg = new Label();
		Button buttonUndo = new Button("", imgVU);
		Button buttonRedo = new Button("", imgVR);
		
		DropShadow shadow = new DropShadow();
		
		BackgroundSize bSize = new BackgroundSize(1366, 150, false, false, true, true); 
		BackgroundImage bImg = new BackgroundImage(imgB, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, null, bSize);
		Background back = new Background(bImg);
		labelImg.setBackground(back);
		labelImg.setMinSize(1366, 150);
		
		Color c = Color.rgb(142, 231, 199);
		BackgroundFill fill = new BackgroundFill(c,null,null);
		Background backB1 = new Background(fill);
		buttonUndo.setBackground(backB1);
		buttonUndo.setMinSize(60, 50);
		
		buttonUndo.addEventHandler(MouseEvent.MOUSE_ENTERED,
	            new EventHandler<MouseEvent>() {
	              @Override
	              public void handle(MouseEvent e) {
	            	  buttonUndo.setEffect(shadow);
	              }
	            });
		
		buttonUndo.addEventHandler(MouseEvent.MOUSE_EXITED,
	            new EventHandler<MouseEvent>() {
	              @Override
	              public void handle(MouseEvent e) {
	            	  buttonUndo.setEffect(null);
	              }
	            });
		
		buttonRedo.setBackground(backB1);
		buttonRedo.setMinSize(60, 50);
		
		buttonRedo.addEventHandler(MouseEvent.MOUSE_ENTERED,
	            new EventHandler<MouseEvent>() {
	              @Override
	              public void handle(MouseEvent e) {
	            	  buttonRedo.setEffect(shadow);
	              }
	            });
		
		buttonRedo.addEventHandler(MouseEvent.MOUSE_EXITED,
	            new EventHandler<MouseEvent>() {
	              @Override
	              public void handle(MouseEvent e) {
	            	  buttonRedo.setEffect(null);
	              }
	            });
		
		Label space = new Label();
		space.setBackground(backB1);
		space.setMinSize(200, 50);
		
		int n = 4;
		
		Button[] bPages = new Button[n]; 
		String[] bNames = {"Attractions", "Your Reports", "Coupons", "Favourite"};
		
		int i = 0;
		for(; i<n; i++) {
			bPages[i] = new Button(bNames[i]);
			bPages[i].setBackground(backB1);
			bPages[i].setMinSize(200, 50);
			bPages[i].setFont(font);
			bPages[i].setTextFill(Color.BLACK);
			
			final Button button = bPages[i];
			
			bPages[i].addEventHandler(MouseEvent.MOUSE_ENTERED,
		            new EventHandler<MouseEvent>() {
		              @Override
		              public void handle(MouseEvent e) {
		            	  button.setEffect(shadow);
		              }
		            });

			bPages[i].addEventHandler(MouseEvent.MOUSE_EXITED,
		            new EventHandler<MouseEvent>() {
		              @Override
		              public void handle(MouseEvent e) {
		            	  button.setEffect(null);
		              }
		            });
			
		}
		
		HBox loginBox = new HBox();
		loginBox.setBackground(backB1);
		loginBox.setMinSize(246, 50);
		
		FileInputStream inLoginImg = new FileInputStream(System.getProperty("user.dir")+"\\img\\login2.jpg");
		Image imgL = new Image(inLoginImg);
		ImageView imgVL = new ImageView(imgL);
		
		
		Button buttonLogin = new Button("", imgVL);
		buttonLogin.setBackground(backB1);
		buttonLogin.setMaxWidth(176);
		buttonLogin.setMaxWidth(34);
		
		buttonLogin.addEventHandler(MouseEvent.MOUSE_ENTERED,
	            new EventHandler<MouseEvent>() {
	              @Override
	              public void handle(MouseEvent e) {
	            	  buttonLogin.setEffect(shadow);
	              }
	            });

		buttonLogin.addEventHandler(MouseEvent.MOUSE_EXITED,
	            new EventHandler<MouseEvent>() {
	              @Override
	              public void handle(MouseEvent e) {
	            	  buttonLogin.setEffect(null);
	              }
	            });
		
		
		
		HBox body = new HBox();
		VBox info = new VBox();
		VBox sideInfo = new VBox();
		
		body.setMinSize(1366, 500);
		
		info.setMinSize(966, 500);
		
		sideInfo.setBackground(backB1);
		sideInfo.setMinSize(400, 500);

		body.getChildren().addAll(info, sideInfo);
		loginBox.getChildren().addAll(buttonLogin);
		lineButtons.getChildren().addAll(buttonUndo, buttonRedo,space,bPages[0],bPages[1],bPages[2],bPages[3], loginBox);
		root.getChildren().addAll(labelImg, lineButtons, body);
		return scene;
		
	}
}
