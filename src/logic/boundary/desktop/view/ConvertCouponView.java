package logic.boundary.desktop.view;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import logic.boundary.desktop.controlgrafico.ConvertCouponGuiControl;
import logic.boundary.desktop.controlgrafico.LoginGuiControl;

public class ConvertCouponView extends GenericView{

	public ConvertCouponView(LoginGuiControl lGC) {
		super.gGC = new ConvertCouponGuiControl(this);
		super.gGC.setLoginGuiControl(lGC);
	}
	
	public void showScene(Stage stage){
		
		super.stage = stage;
		
		if(super.gGC.getLoginGuiControl().getLoginControl().getLoginBean().getUserID() != null) {
			super.loginOn();
		}
		
		//pageButton background
		super.bPages[2].setBackground(backB2);
		
		int coins = ((ConvertCouponGuiControl)super.gGC).getCoins();
		if(coins != -1) {
			Label coinsL = new Label();
			coinsL.setFont(fontSide);
			coinsL.setTextFill(Color.BLACK);
			coinsL.setText("Your coins: "+coins);
			
			
			super.sideInfo.getChildren().addAll(coinsL);
		} 
		
		stage.setScene(super.scene);
	
	}
}
