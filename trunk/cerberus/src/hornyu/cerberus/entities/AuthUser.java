package hornyu.cerberus.entities;

import java.util.Iterator;
import java.util.List;

public class AuthUser extends CerberusUser {
	private static final long serialVersionUID = 1L;
	
	private List roleList;
	private List taskList;
	private boolean authen = false;
	/**
	 * 是否有此授權
	 * @param taskName Task名稱
	 * @return
	 */
	public boolean doAs(String taskName){
		List taskList = getTaskList();
		if(taskList == null || taskList.size() < 1){
			return false;
		}
		
		for(Iterator itr = taskList.iterator();itr.hasNext();){
			CerberusTask task = (CerberusTask)itr.next();
			if(task.getName().equalsIgnoreCase(taskName)){
				return true;
			}
		}
		return false;
	}
	
	public String toString(){
		return getAccount();
	}
	
	public boolean isAuthen() {
		return authen;
	}
	public AuthUser setAuthen(boolean authen) {
		this.authen = authen;
		return this;
	}
	public List getRoleList() {
		return roleList;
	}
	public void setRoleList(List roleList) {
		this.roleList = roleList;
	}
	public List getTaskList() {
		return taskList;
	}
	public void setTaskList(List taskList) {
		this.taskList = taskList;
	}
}
