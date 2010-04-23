package sendbox;

import hornyu.cerberus.model.CerberusEditModel;

public class ListTotalTask {
//	static public void main(String[] args){
//		Cerberus cerberus = new LoginCerberus();
//		cerberus.login("eggboss", "123");
//		AuthUser user = cerberus.getUser();
//		
//		System.out.println(user.getAccount() + " has ");
//		
//		List roleList = user.getRoleList();
//		for(Iterator itr = roleList.iterator();itr.hasNext();){
//			CerberusRole role = (CerberusRole)itr.next();
//			System.out.println(role.getName() + " Role !");
//		}
//		
//		List taskList = user.getTaskList();
//		for(Iterator itr = taskList.iterator();itr.hasNext();){
//			CerberusTask task = (CerberusTask)itr.next();
//			System.out.println(task.getName() + " Task !");
//		}
//	}
	
	static public void main(String[] args){
		CerberusEditModel ceModel = new CerberusEditModel();
		ceModel.updAuthPasswd("kirkshu", "123");
//		ceModel.addAuthData("test", "pass");
	}
}
