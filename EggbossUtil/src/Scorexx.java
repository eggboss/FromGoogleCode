import java.util.ArrayList;
import java.util.List;
/**
 * 計算學生平均成績和科目平均(OO的寫法)
 * 
 * 　　我看到題目時是先在想如何可以做到全動態，就是學生、科目都可以動態新增，
 * 這裡科目是固定的，只有學生可以態動新增。
 * 
 * 　　再來就是決定那些是物件，所謂物件有兩個重要的性值，就是屬性和行為，
 * 就科目而言，其屬性就是科目名稱和成績，沒有特別的行為，就只用來記錄，
 * 而學生有姓名、科目，其中科目是多筆的，其行為有算出科目成績總和還有平均。
 * 
 * 　　一開始寫時其實沒有定義這麼多啦，這兩個物件也都只有定出性值而已，
 * 其行為是後來需要用到才加的，總而言之，就是一個“班級”會有很多“學生”，
 * 一個學生會有很多“科目”成績。
 * 
 * 班級－＊學生－姓名
 * 　　　　　｜
 * 　　　　　　－＊科目－名稱
 * 　　　　　　　　　｜
 * 　　　　　　　　　　－成績
 * 　　　　
 * 
 * @author Kirk
 *
 */
public class Scorexx { // 這個是主要的class => 裡面有main這個method
	
	private List personList = new ArrayList(); // 用來放所有的學生
	
	/**
	 * 增加一個學生
	 * @param person 學生物件
	 */
	public void addToPersonList(Person person){
		this.personList.add(person);
	}
	
	/**
	 * 取得所有的學生
	 * @return
	 */
	public List getPersonList() {
		return personList;
	}

	static public void main(String[] args){
		Scorexx sc = new Scorexx(); // 先產生一個實體(實例)
		// 學生一
		Person person1 = new Person(); // 產生一個學生物件
		person1.setName("Amy"); // 填入姓名
		
		// 因為科目名稱會一直用到，所以會寫成Constant的型式，如此一來如果以後要改成中文的，只要去修改Constant這個class就行了
		person1.addCourse(new Course(Constant.CONSTANT_CHINESE,67)); // 填入科目名稱和成績
		person1.addCourse(new Course(Constant.CONSTANT_ENGLISH,80)); // 填入科目名稱和成績
		person1.addCourse(new Course(Constant.CONSTANT_MATH,94)); // 填入科目名稱和成績
		// 學生二
		Person person2 = new Person();
		person2.setName("Jane");
		person2.addCourse(new Course(Constant.CONSTANT_CHINESE,95));
		person2.addCourse(new Course(Constant.CONSTANT_ENGLISH,80));
		person2.addCourse(new Course(Constant.CONSTANT_MATH,65));
		// 學生三
		Person person3 = new Person();
		person3.setName("Peter");
		person3.addCourse(new Course(Constant.CONSTANT_CHINESE,88));
		person3.addCourse(new Course(Constant.CONSTANT_ENGLISH,84));
		person3.addCourse(new Course(Constant.CONSTANT_MATH,81));
		// 學生四
		Person person4 = new Person();
		person4.setName("Harry");
		person4.addCourse(new Course(Constant.CONSTANT_CHINESE,91));
		person4.addCourse(new Course(Constant.CONSTANT_ENGLISH,92));
		person4.addCourse(new Course(Constant.CONSTANT_MATH,79));
		
		sc.addToPersonList(person1); // 把學生一一加入
		sc.addToPersonList(person2);
		sc.addToPersonList(person3);
		sc.addToPersonList(person4);
		
		// 印科目名稱
		System.out.println("\t"+Constant.CONSTANT_CHINESE+"\t"+Constant.CONSTANT_ENGLISH+"\t"+Constant.CONSTANT_MATH);
		
		// 取得所有的學生列表
		List personList = sc.getPersonList();
		
		// 一一取得學生
		for(int i=0;i<personList.size();i++){
			Person person = (Person)personList.get(i); // 取出來得由Object轉成Person
			System.out.print(person.getName()+"\t"); // 印出名字
			List scoreList = person.getCourseList(); // 由學生物件取得所有科目列表
			for(int j=0;j<scoreList.size();j++){
				Course Course = (Course) scoreList.get(j);
				System.out.print(Course.getScore()+"\t"); // 印出成績
			}
			System.out.print(person.getAvg()); // 取得成績並印出
			System.out.println(); // 換行
		}
		
		// 取得各科平均並印出
		System.out.print("\t"+Constant.calAvg(personList, Constant.CONSTANT_CHINESE)+"\t"+Constant.calAvg(personList, Constant.CONSTANT_ENGLISH)+"\t"+Constant.calAvg(personList, Constant.CONSTANT_MATH)+"\t"+Constant.calAllAvg(personList));
	}
}

/**
 * 學生物件
 * @author Kirk
 *
 */
class Person{
	private String name; // 名稱
	private List courseList = new ArrayList(); // 科目成績列表
	
	/**
	 * 取得科目成績列表
	 * @return
	 */
	public List getCourseList() {
		return courseList;
	}
	/**
	 * 取得學生名稱
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * 設定學生名稱
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 新增科目成績物件
	 * @param course
	 */
	public void addCourse(Course course){
		this.courseList.add(course);
	}
	/**
	 * 取得所有科目成績總和
	 * @return
	 */
	public double getSum(){
		double sum = 0d;
		for(int i=0;i<courseList.size();i++){
			Course course = (Course)courseList.get(i);
			sum += course.getScore();
		}
		return sum;
	}
	/**
	 * 取得所有科目成績平均
	 * @return
	 */
	public double getAvg(){
		int listSize = courseList.size();
		if(listSize>0){
			
			return (getSum()/listSize);
		}else{
			return 0;
		}
	}
	/**
	 * 依科目名稱取得科目物件
	 * @param courseName 科目名稱
	 * @return
	 */
	public Course getCourseByName(String courseName){
		for(int i=0;i<getCourseList().size();i++){
			Course course = (Course)getCourseList().get(i);
			if(course.getName().equals(courseName)) return course;
		}
		return null;
	}
}
/**
 * 科目物件
 * @author Kirk
 *
 */
class Course{
	private String name; // 科目名稱
	private double score; // 科目成績
	
	/**
	 * 這是預設的建構子，如果沒有加就不能 Course c = new Course(); 而一定要Course c = new Course(xx,yy);
	 */
	Course(){};
	
	/**
	 * 建構子，只是方便在Person裡新增
	 * @param name
	 * @param score
	 */
	Course(String name, double score){
		this.name = name;
		this.score = score;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
}
/**
 * 這用來定義常數
 * @author Kirk
 *
 */
class Constant{
	static public final String CONSTANT_CHINESE = "Chinese";
	static public final String CONSTANT_ENGLISH = "English";
	static public final String CONSTANT_MATH = "Math";
	
	/**
	 * 依科目名稱算出平均
	 * @param personList
	 * @param courseName
	 * @return
	 */
	static public double calAvg(List personList,String courseName){
		double sum = 0d;
		for(int i=0;i<personList.size();i++){
			Person person = (Person)personList.get(i);
			Course course = person.getCourseByName(courseName);
			if(course!=null){
				sum += course.getScore();
			}
		}
		return sum/personList.size();
	}
	
	static public double calAllAvg(List personList){
		double sum = 0d;
		int count = 0;
		for(int i=0;i<personList.size();i++){
			Person person = (Person)personList.get(i);
			List cList = person.getCourseList();
			for(int j=0;j<cList.size();j++){
				Course course = (Course)cList.get(j);
				if(course!=null){
					sum += course.getScore();
					count++;
				}
			}
		}
		return sum/count;
	}

}
