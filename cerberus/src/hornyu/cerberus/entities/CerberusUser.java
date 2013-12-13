package hornyu.cerberus.entities;

import java.util.ArrayList;
import java.util.List;

import hornyu.entities.AbstractEntity;

public class CerberusUser extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	private String account;
	private String passwd;
	private String note;
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public List getKeyNames() {
		List<String> list = new ArrayList<String>();
		list.add("id");
		return list;
	}

	public String getTableName() {
		return "cerberus_user";
	}

}
