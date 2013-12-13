package hornyu.cerberus.entities;

import java.util.ArrayList;
import java.util.List;

import hornyu.entities.AbstractEntity;

public class CerberusRoleTask extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	private long roleId;
	private long taskId;
	

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	@Override
	public List getKeyNames() {
		List<String> list = new ArrayList<String>();
		list.add("id");
		return list;
	}

	@Override
	public String getTableName() {
		return "cerberus_role_task";
	}

}
