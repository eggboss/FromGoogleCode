package hornyu.cerberus.model;

import hornyu.cerberus.entities.AuthUser;
import hornyu.cerberus.entities.CerberusRole;
import hornyu.cerberus.entities.CerberusRoleTask;
import hornyu.cerberus.entities.CerberusRoleView;
import hornyu.cerberus.entities.CerberusTask;
import hornyu.cerberus.entities.CerberusUser;
import hornyu.cerberus.entities.CerberusUserRole;
import hornyu.cerberus.entities.CerberusUserTask;
import hornyu.cerberus.util.CryptoUtil;
import hornyu.entities.AbstractEntity;
import hornyu.model.AbstractAdminModel;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

public class CerberusEditModel extends AbstractAdminModel {
	private Logger log = Logger.getLogger(this.getClass());
	/**
	 * 新增驗證資料
	 * @param account
	 * @param passwd
	 * @param desc
	 */
	public void addAuthData(String account, String passwd, String note){
		CerberusUser user = new CerberusUser();
		user.setAccount(account);
		user.setPasswd(CryptoUtil.getCryptoString(passwd)); // 密碼加密
		user.setNote(note);
		
		user.setTxnMode(CerberusUser.INSERT);
		saveOrUpdate(user);
	}
	/**
	 * 新增驗證資料
	 * @param account
	 * @param passwd
	 */
	public void addAuthData(String account, String passwd){
		addAuthData(account, passwd, null);
	}
	/**
	 * 修改Password
	 * @param account
	 * @param passwd
	 */
	public void updAuthPasswd(String account, String passwd){
		String sql = "select * from cerberus_user where account = ? ";
		Object[] params = {account};
		CerberusUser user = (CerberusUser)executeOneRowQuery(sql, params, CerberusUser.class);
		user.setPasswd(CryptoUtil.getCryptoString(passwd)); // 密碼加密
		
		user.setTxnMode(CerberusUser.UPDATE);
		saveOrUpdate(user);
		log.info(account + " Change Password Success !");
	}
	
	/**
	 * 新增角色
	 * @param name
	 * @param desc
	 */
	public void addRole(String name, String note){
		CerberusRole role = new CerberusRole();
		role.setName(name);
		role.setNote(note);
		
		role.setTxnMode(CerberusRole.INSERT);
		saveOrUpdate(role);
		log.info("Add Role " + name + " Success !");
	}
	/**
	 * 新增TASK
	 * @param name
	 * @param note
	 */
	public void addTask(String name, String note){
		CerberusTask task = new CerberusTask();
		task.setName(name);
		task.setNote(note);
		
		task.setTxnMode(CerberusTask.INSERT);
		saveOrUpdate(task);
		log.info("Add Task " + name + " Success !");
	}
	
	/**
	 * 賦與Role給Account
	 * @param userId
	 * @param roleId
	 */
	public void putRoleToAccount(long userId, long roleId){
		CerberusUserRole userRole = new CerberusUserRole();
		userRole.setUserId(userId);
		userRole.setRoleId(roleId);
		
		userRole.setTxnMode(CerberusUserRole.INSERT);
		saveOrUpdate(userRole);
	}
	
	
	
	/**
	 * 賦與Role給Account
	 * @param account
	 * @param roleId
	 */
	public void putRoleToAccount(String account, long roleId){
		LoginModel lModel = new LoginModel();
		AuthUser user = lModel.getUser(account);
		
		CerberusUserRole userRole = new CerberusUserRole();
		userRole.setUserId(user.getId());
		userRole.setRoleId(roleId);
		
		userRole.setTxnMode(CerberusUserRole.INSERT);
		saveOrUpdate(userRole);
	}
	
	/**
	 * 賦與Role給Account
	 * @param userRole
	 */
	public void putRoleToAccount(CerberusUserRole userRole){
		userRole.setTxnMode(CerberusUserRole.INSERT);
		saveOrUpdate(userRole);
	}
	
	/**
	 * 賦與Task給Account
	 * @param accountId
	 * @param taskId
	 */
	public void putTaskToAccount(long accountId, long taskId){
		CerberusUserTask userTask = new CerberusUserTask();
		userTask.setUserId(accountId);
		userTask.setTaskId(taskId);
		
		userTask.setTxnMode(CerberusUserTask.INSERT);
		saveOrUpdate(userTask);
	}
	/**
	 * 賦與Task給Account
	 * @param userTask
	 */
	public void putTaskToAccount(CerberusUserTask userTask){
		userTask.setTxnMode(CerberusUserTask.INSERT);
		saveOrUpdate(userTask);
	}
	
	/**
	 * 賦與Task給Role
	 * @param roleId
	 * @param taskId
	 */
	public void putTaskToRole(long roleId, long taskId){
		CerberusRoleTask roleTask = new CerberusRoleTask();
		roleTask.setRoleId(roleId);
		roleTask.setTaskId(taskId);
		
		roleTask.setTxnMode(CerberusRoleTask.INSERT);
		saveOrUpdate(roleTask);
	}
	
	public void removeTaskFromRole(long roleId, long taskId){
		String sql = "select * from cerberus_role_task where roleId = ? and taskId = ? ";
		Object[] params = {roleId, taskId};
		Object rtObj = executeOneRowQuery(sql, params, CerberusRoleTask.class);
		if(rtObj != null){
			CerberusRoleTask roleTask = (CerberusRoleTask)rtObj;
			roleTask.setTxnMode(CerberusRoleTask.DELETE);
			saveOrUpdate(roleTask);
		}
		//TODO
	}
	
//	public void removeTaskFromUser(String aaount, long taskId){
//		StringBuffer sql = new StringBuffer();
//		sql.append("select ut.* ")
//		   .append("from cerberus_user_task ut, cerberus_user u ")
//		   .append("where ut.userId")
	
	/**
	 * 賦與Task給Role
	 * @param roleTask
	 */
	public void putTaskToRole(CerberusRoleTask roleTask){
		roleTask.setTxnMode(CerberusRoleTask.INSERT);
		saveOrUpdate(roleTask);
	}
	/**
	 * 取得Role列表
	 * @return
	 */
	public List getAllRoles(){
		String sql = "select * from cerberus_role order by id";
		Object[] params = {};
		return executePreparedQuery(sql, params, CerberusRole.class);
	}
	/**
	 * 取得所有Role的TaskList
	 * @return
	 */
	public List getAllRolesAndTasks(){
		String sql = "select * from cerberus_role order by id";
		Object[] params = {};
		List roleList = executePreparedQuery(sql, params, CerberusRoleView.class);
		
		for(Iterator itr = roleList.iterator(); itr.hasNext();){
			CerberusRoleView roleView = (CerberusRoleView)itr.next();
			roleView.setTaskList(getAllTaskByRoleId(roleView.getId()));
		}
		return roleList;
	}
	
	private CerberusRoleView getRoleView(long roleId){
		String sql = "select * from cerberus_role where id = ? ";
		Object[] params = {roleId};
		Object roleView = executeOneRowQuery(sql, params, CerberusRoleView.class);
		if(roleView == null)
			return null;
		else
			return (CerberusRoleView)roleView;
	}
	
	/**
	 * 取得這個Role的所有Task
	 * @param roleId
	 * @return
	 */
	public List getAllTaskByRoleId(long roleId){
		StringBuffer sql = new StringBuffer();
		sql.append("select ct.* ")
		   .append("from cerberus_task ct, cerberus_role_task crt ")
		   .append("where ct.id = crt.taskId ")
		   .append("and crt.roleId = ? ")
		   .append("order by id ");
		Object[] params = {roleId};
		List taskList = executePreparedQuery(sql.toString(), params, CerberusTask.class);
		return taskList;
	}
	/**
	 * 取得角色及其任務列表
	 * @param roleId
	 * @return
	 */
	public CerberusRoleView getRoleWithTaskList(long roleId){
		CerberusRoleView roleView = getRoleView(roleId);
		roleView.setTaskList(getAllTaskByRoleId(roleId));
		return roleView;
	}
	
	/**
	 * 刪除這個角色的所有任務
	 * @param roleId 角色代碼
	 */
	public void delAllTaskByRole(long roleId){
		String sql = "select * from cerberus_role_task where roleId = ? order by id";
		Object[] params = {roleId};
		List roleTaskList = executePreparedQuery(sql, params, CerberusRoleTask.class);
		
		for(Iterator itr = roleTaskList.iterator();itr.hasNext();){
			CerberusRoleTask crt = (CerberusRoleTask)itr.next();
			crt.setTxnMode(CerberusRoleTask.DELETE);
			saveOrUpdate(crt);
		}
	}
	/**
	 * 設定角色的任務
	 * @param roleId 角色代碼
	 * @param taskIdArr 任務代碼陣列
	 */
	public void putTasksToRole(long roleId, long[] taskIdArr){
		if(taskIdArr != null){
			for(int i=0; i<taskIdArr.length; i++ ){
				putTaskToRole(roleId, taskIdArr[i]);
			}
			log.info("Put " + taskIdArr.length + " Tasks to the Role !");
		}
		log.error("taskIdArr is null !");
	}
	/**
	 * 取得所有任務列表
	 * @return
	 */
	public List getAllTask(){
		String sql = "select * from cerberus_task order by id ";
		Object[] params = {};
		return executePreparedQuery(sql, params, CerberusTask.class);
	}
	/**
	 * 取得尚未賦與的任務
	 * @param roleId
	 * @return
	 */
	public List getOtherTask(long roleId){
		List tsakList = getAllTaskByRoleId(roleId);
		StringBuffer idString = new StringBuffer();
		for(Iterator itr = tsakList.iterator(); itr.hasNext();){
			CerberusTask task = (CerberusTask)itr.next();
			idString.append(task.getId());
			if(itr.hasNext()){
				idString.append(",");
			}
		}
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * ")
		   .append("from cerberus_task ")
		   .append("where id not in (" + idString.toString() + ") ")
		   .append("order by id ");
		Object[] params = {};
		return executePreparedQuery(sql.toString(), params, CerberusTask.class);
	}
	
	@Override
	public void beforeDeleteTransaction(AbstractEntity entity) {
	}

	@Override
	public void beforeDeleteTransaction(List entities) {
	}

	@Override
	public void beforeSaveTransaction(AbstractEntity entity) {
	}

	@Override
	public void beforeSaveTransaction(List entities) {
	}

	@Override
	public void beforeUpdateTransaction(AbstractEntity entity) {
	}

	@Override
	public void beforeUpdateTransaction(List entities) {
	}

}
