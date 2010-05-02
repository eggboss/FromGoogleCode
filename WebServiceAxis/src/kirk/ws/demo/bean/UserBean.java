package kirk.ws.demo.bean;

public class UserBean {
	String name;
	String mobile;
	int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String printUserInfo(){
		return " UserName:"+name+"\n Mobile:"+mobile+"\n age:"+age;
	}
	
}
