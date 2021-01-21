package logic.control.controlapplicativo;

import logic.control.bean.LoginBean;
import logic.entities.dao.ParkVisitorDAO;
import logic.exception.DBFailureException;

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
			boolean b = ParkVisitorDAO.searchUserByID(idUser);
			
			if(!b) {
				ParkVisitorDAO.insertNewUser(idUser);
			}
		} catch (DBFailureException e) {
			e.printStackTrace();
			throw new DBFailureException(e.getMessage());
		}
	}
	
	
}
