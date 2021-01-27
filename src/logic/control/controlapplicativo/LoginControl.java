package logic.control.controlapplicativo;

import logic.control.bean.LoginBean;
import logic.entities.dao.ParkVisitorDAO;
import logic.entities.model.ParkVisitor;
import logic.exception.DBFailureException;
import logic.exception.ParkVisitorNotFoundException;

public class LoginControl {

	private LoginBean lB;
	
	public LoginControl() {
		lB = new LoginBean();
	}
	
	public void setLoginBean(LoginBean lBean) {
		lB = lBean;
	}
	
	public LoginBean getLoginBean() {
		return lB;
	}	
	
	public void verifyUserOnDB(String idUser) throws DBFailureException{
		
		//verifica se l'utente è già stato inserito nel database
		//aggiungi il Park Visitor se non presente nel database		
		try {
			ParkVisitor pV = ParkVisitorDAO.selectParkVisitor(idUser);
			
			if(pV.getUserID() == null) {
				ParkVisitorDAO.insertNewUser(idUser);
			}
			
		} catch (DBFailureException | ParkVisitorNotFoundException e) {
			e.printStackTrace();
			throw new DBFailureException(e.getMessage());
		}
	}
	
	
}
