package hornyu.cerberus.model;

import hornyu.cerberus.entities.AuthUser;
import hornyu.cerberus.entities.CerberusUser;

public interface CerberusModel {
	/**
	 * 驗證帳號密碼
	 * @param account 帳號
	 * @param passwd 密碼
	 * @return
	 */
	public CerberusUser authenUser(String account, String passwd);
	/**
	 * 取得使用者的登入物件
	 * @param account 帳號
	 * @return
	 */
	public AuthUser getUser(String account);
	/**
	 * 取得使用者的Task
	 * @param user 登入物件
	 * @return
	 */
	public AuthUser getTaskByUser(AuthUser user);
}
