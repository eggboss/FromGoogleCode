package hornyu.cerberus.action;

import hornyu.cerberus.entities.AuthUser;

public interface Cerberus {
	
	public boolean login(); 
	
	public boolean login(String account, String passwd); 
	
	public void logout();
	
	public AuthUser getUser();
	
}
