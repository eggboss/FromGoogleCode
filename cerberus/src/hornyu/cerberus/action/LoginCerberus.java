package hornyu.cerberus.action;

import org.apache.log4j.Logger;

import hornyu.cerberus.entities.AuthUser;
import hornyu.cerberus.entities.CerberusUser;
import hornyu.cerberus.model.CerberusModel;
import hornyu.cerberus.model.LoginModel;

public class LoginCerberus implements Cerberus {
	private Logger log = Logger.getLogger(this.getClass());
	private CerberusModel model;
	private String account;
	private String passwd;
	private AuthUser user;
	
	/**
	 * 建構子
	 *
	 */
	public LoginCerberus(){
		model = new LoginModel();
	}
	/**
	 * 建構子
	 * @param model
	 */
	public LoginCerberus(CerberusModel model){
		this.model = model;
	}
	/**
	 * 建構子
	 * @param account 帳號
	 * @param passwd 密碼
	 */
	public LoginCerberus(String account, String passwd){
		this.account = account;
		this.passwd = passwd;
	}
	
	/**
	 * 登入驗證，使用建構子傳入帳號、密碼.
	 * @return
	 */
	public boolean login() {
		if(account == null || passwd == null){
			log.info(user.getAccount() + " Login failed(Account or Password is null.)!");
			return false;
		}
		Object tempObj = model.authenUser(account,passwd);
		if(tempObj == null){
			log.info(account + " Login Failed!");
			return false;
		}
		else{
			user = (AuthUser)tempObj;
			log.info(user.getAccount() + " Login Success!");
			return user.isAuthen();
		}
	}
	
	/**
	 * 登入驗證
	 * @param 帳號
	 * @param 密碼
	 * @return
	 */
	public boolean login(String account, String passwd) {
		Object tempObj = model.authenUser(account,passwd);
		if(tempObj == null){
			log.info(account + " Login Failed!");
			return false;
		}
		else{
			user = (AuthUser)tempObj;
			log.info(user.getAccount() + " Login Success!");
			return user.isAuthen();
		}
	}
	
	/**
	 * 取得登入物件
	 * @return AuthUser
	 */
	public AuthUser getUser() {
		if(user != null && user.isAuthen()){
			return getTask(user);
		}
		return user;
	}
	/**
	 * 取得登入者的Task
	 * @param user
	 * @return
	 */
	public AuthUser getTask(CerberusUser user){
		return model.getTaskByUser((AuthUser)user);
	}
	/**
	 * 測試
	 * @param args
	 */
	static public void main(String[] args){
		LoginCerberus login = new LoginCerberus();
		login.login("kk1" , "kk1");
	}
	/**
	 * 登出
	 */
	public void logout() {
		if(user!=null){
			log.info(user.getAccount() + "Logout!");
			user = null;
		}
	}
	
}
