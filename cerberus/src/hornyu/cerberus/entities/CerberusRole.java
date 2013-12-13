package hornyu.cerberus.entities;

import java.util.List;

import hornyu.entities.AbstractEntity;

public class CerberusRole extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String note;

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List getKeyNames() {
		return null;
	}

	public String getTableName() {
		return null;
	}

}
