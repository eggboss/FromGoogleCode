package hornyu.cerberus.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import hornyu.cerberus.entities.AuthUser;
import hornyu.cerberus.entities.CerberusRole;
import hornyu.cerberus.entities.CerberusTask;
import hornyu.cerberus.entities.CerberusUser;
import hornyu.cerberus.util.CryptoUtil;
import hornyu.entities.AbstractEntity;
import hornyu.model.AbstractAdminModel;

public class LoginModel extends AbstractAdminModel implements CerberusModel{
	private Logger log = Logger.getLogger(this.getClass());
	/**
	 * 認證使用者
	 * @param account
	 * @param passwd
	 * @return
	 */
	public CerberusUser authenUser(String account, String passwd){
		String sql = "select * from cerberus_user where account = ? and passwd = ?";
		Object[] params = {account, CryptoUtil.getCryptoString(passwd)}; // 密碼加密
		Object user = executeOneRowQuery(sql, params, AuthUser.class);
		if(user != null){
			log.info(account + " Pass Authen. !");
			return ((AuthUser)user).setAuthen(true);
		}
		return null;
	}
	
	/**
	 * 使用userId取得User
	 * @param userId
	 * @return
	 */
	public AuthUser getUser(String account){
		String sql = "select * from cerberus_user where account = ? ";
		Object[] params = {account};
		return (AuthUser)executeOneRowQuery(sql, params, AuthUser.class);
	}
	
	/**
	 * 使用userId取得user的role
	 * @param userId
	 * @return
	 */
	public List getRoleByUserId(long userId){
		StringBuffer sql = new StringBuffer();
		sql.append("select r.* ")
		   .append("from cerberus_role r, cerberus_user_role ur ")
		   .append("where ur.userId = ? ")
		   .append("and ur.roleId = r.id ")
		   .append("order by r.id ");
		Object[] params = {userId};
		List roleList = executePreparedQuery(sql.toString(), params, CerberusRole.class);
		return roleList;
	}
	/**
	 * 使用由roleId組成的字串查詢task
	 * @param roleIdString
	 * @return
	 */
	public List getTaskByRoleIdString(String roleIdString){
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* ")
		   .append("from cerberus_task t, cerberus_role_task rt ")
		   .append("where rt.roleId in ( ? ) ")
		   .append("and rt.taskId = t.id ")
		   .append("order by t.id ");
		Object[] params = {roleIdString};
		List taskList = executePreparedQuery(sql.toString(), params, CerberusTask.class);
		return taskList;
	}
	
	private String getRoleIdString(List roleList){
		StringBuffer roleIdStirng = new StringBuffer();
		for(Iterator itr = roleList.iterator();itr.hasNext();){
			CerberusRole role = (CerberusRole)itr.next();
			roleIdStirng.append(role.getId());
			if(itr.hasNext()){
				roleIdStirng.append(",");
			}
		}
		return roleIdStirng.toString();
	}
	/**
	 * 取得user所有的task
	 * @param user
	 * @return user
	 */
	public AuthUser getTaskByUser(AuthUser user){
		List roleList = getRoleByUserId(user.getId());
		user.setRoleList( roleList );
		
		String roleIdStirng = getRoleIdString(roleList);
		
//		List roleTaskList = getTaskByRoleIdString(roleIdStirng);
//		List userTaskList = getTaskByUserId(user.getId());
//		user.setTaskList(margeList(roleTaskList, userTaskList));
		
		user.setTaskList(getTask(roleIdStirng, user.getId()));
		
		return user;
	}
	
	/**
	 * 使用roleId取得Task List
	 * @param roleId
	 * @return
	 */
	public List getTaskByRoleId(long roleId){
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* ")
		   .append("from cerberus_task t, cerberus_role_task rt ")
		   .append("where rt.roleId = ? ")
		   .append("and rt.taskId = t.id ")
		   .append("order by t.id ");
		Object[] params = {roleId};
		List taskList = executePreparedQuery(sql.toString(), params, CerberusTask.class);
		return taskList;
	}
	
	public List getTask(String roleIdString, long userId){
//		StringBuffer sql = new StringBuffer();
//		sql.append("select DISTINCT t.* ")
//		   .append("from cerberus_task t, cerberus_role_task rt, cerberus_user_task uk ")
//		   .append("where (rt.roleId in ( ? ) ")
//		   .append("and rt.taskId = t.id) ")
//		   .append("or (uk.taskId = t.id ")
//		   .append("and uk.userId = ?) ")
//		   .append("order by t.id ");
//		Object[] params = {roleIdString, userid};
//		List taskList = executePreparedQuery(sql.toString(), params, Task.class);
//		return taskList;
		List roleTaskList = getTaskByRoleIdString(roleIdString);
		List userTaskList = getTaskByUserId(userId);
		return margeList(roleTaskList, userTaskList);
	}
	
	
	/**
	 * 使用userId取得Task List
	 * @param userId
	 * @return
	 */
	public List getTaskByUserId(long userId){
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* ")
		   .append("from cerberus_task t, cerberus_user_task ut ")
		   .append("where ut.userId = ? ")
		   .append("and ut.taskId = t.id ")
		   .append("order by t.id ");
		Object[] params = {userId};
		List taskList = executePreparedQuery(sql.toString(), params, CerberusTask.class);
		return taskList;
	}
	/*
	 * 測試
	 */
	static public void main(String[] args){
		LoginModel model = new LoginModel();
//		List ll = model.getTaskByUserId(1l);
//		List ll = model.getRoleByUserId(1l);
//		List ll = model.getTaskByRoleId(1l);
//		List ll = model.getTaskByRoleIdString("2");
		List ll = model.getTask("2",2l);
		System.out.println(ll.size());
	}
	
	@SuppressWarnings("unchecked")
	public List margeList(List list1, List list2){
		if(list1==null && list2==null){
			return new ArrayList(0);
		}
		else if(list1!=null && list2!=null && list1.size() == 0 && list2.size() == 0){
			return list1;
		}
		else if(list1 == null || list1.size() == 0 && list2 != null){
			return list2;
		}else if(list2 == null || list2.size() == 0 && list1 != null){
			return list1;
		}
		int list1size = list1.size();
		int list2size = list2.size();
		int totalSize = list1size + list2size;
		ArrayList mList = new ArrayList(totalSize);
		for(int i=0; i<list1size; i++){
			mList.add(list1.get(i));
		}
		for(int j=0; j<list2size; j++){
			mList.add(list2.get(j));
		}
		return mList;
	}
	
	public void beforeDeleteTransaction(AbstractEntity entity) {
	}

	public void beforeDeleteTransaction(List entities) {
	}

	public void beforeSaveTransaction(AbstractEntity entity) {
	}

	public void beforeSaveTransaction(List entities) {
	}

	public void beforeUpdateTransaction(AbstractEntity entity) {
	}

	public void beforeUpdateTransaction(List entities) {
	}

}
