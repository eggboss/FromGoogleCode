package kirk.ws.demo;

import kirk.ws.demo.bean.UserBean;

public class UserService{
	static private UserBean user = null;
	
    public UserBean getUser(){
    	if(user==null){
    		user = new UserBean();
    		user.setName("Kirk");
    		user.setMobile("0912345678");
    		user.setAge(32);
    	}
    	return user;
    }
    public void updateUser(UserBean newUser){
    	user.setName(newUser.getName());
    	user.setMobile(newUser.getMobile());
    	user.setAge(newUser.getAge());
    }
    public String printUserInfo(){
    	return getUser().printUserInfo();
    }
}
