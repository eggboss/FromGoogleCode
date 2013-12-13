package hornyu.cerberus.entities;

import java.util.List;

public class CerberusRoleView extends CerberusRole {
	private static final long serialVersionUID = 1L;
	private List taskList;
	
	public List getTaskList() {
		return taskList;
	}
	public void setTaskList(List taskList) {
		this.taskList = taskList;
	}
}
