package hornyu.cerberus.entities;

import java.util.ArrayList;
import java.util.List;

import hornyu.entities.AbstractEntity;

public class CerberusTask extends AbstractEntity {
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
	
	public String toString(){
		return this.name;
	}

	public List getKeyNames() {
		List<String> list = new ArrayList<String>();
		list.add("id");
		return list;
	}

	public String getTableName(){
		return "cerberus_task";
	}

}
