package kirk.displaytag.bean;

public class PersonBean {
	private String name;
	private String nickname;
	private int age;
	private String mobile;
	private String email;
	
	public PersonBean(String name, String nickname, int age, String mobile, String email) {
		super();
		this.name = name;
		this.nickname = nickname;
		this.age = age;
		this.mobile = mobile;
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
