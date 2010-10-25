import java.util.ArrayList;
import java.util.List;
/**
 * �p��ǥͥ������Z�M��إ���(OO���g�k)
 * 
 * �@�@�ڬݨ��D�خɬO���b�Q�p��i�H������ʺA�A�N�O�ǥ͡B��س��i�H�ʺA�s�W�A
 * �o�̬�جO�T�w���A�u���ǥͥi�H�A�ʷs�W�C
 * 
 * �@�@�A�ӴN�O�M�w���ǬO����A�ҿת��󦳨�ӭ��n���ʭȡA�N�O�ݩʩM�欰�A
 * �N��ئӨ��A���ݩʴN�O��ئW�٩M���Z�A�S���S�O���欰�A�N�u�ΨӰO���A
 * �Ӿǥͦ��m�W�B��ءA�䤤��جO�h�����A��欰����X��ئ��Z�`�M�٦������C
 * 
 * �@�@�@�}�l�g�ɨ��S���w�q�o��h�աA�o��Ӫ���]���u���w�X�ʭȦӤw�A
 * ��欰�O��ӻݭn�Ψ�~�[���A�`�Ө����A�N�O�@�ӡ��Z�š��|���ܦh���ǥ͡��A
 * �@�Ӿǥͷ|���ܦh����ء����Z�C
 * 
 * �Z�šС��ǥ͡Щm�W
 * �@�@�@�@�@�U
 * �@�@�@�@�@�@�С���ءЦW��
 * �@�@�@�@�@�@�@�@�@�U
 * �@�@�@�@�@�@�@�@�@�@�Ц��Z
 * �@�@�@�@
 * 
 * @author Kirk
 *
 */
public class Scorexx { // �o�ӬO�D�n��class => �̭���main�o��method
	
	private List personList = new ArrayList(); // �Ψө�Ҧ����ǥ�
	
	/**
	 * �W�[�@�Ӿǥ�
	 * @param person �ǥͪ���
	 */
	public void addToPersonList(Person person){
		this.personList.add(person);
	}
	
	/**
	 * ���o�Ҧ����ǥ�
	 * @return
	 */
	public List getPersonList() {
		return personList;
	}

	static public void main(String[] args){
		Scorexx sc = new Scorexx(); // �����ͤ@�ӹ���(���)
		// �ǥͤ@
		Person person1 = new Person(); // ���ͤ@�Ӿǥͪ���
		person1.setName("Amy"); // ��J�m�W
		
		// �]����ئW�ٷ|�@���Ψ�A�ҥH�|�g��Constant�������A�p���@�Ӧp�G�H��n�令���媺�A�u�n�h�ק�Constant�o��class�N��F
		person1.addCourse(new Course(Constant.CONSTANT_CHINESE,67)); // ��J��ئW�٩M���Z
		person1.addCourse(new Course(Constant.CONSTANT_ENGLISH,80)); // ��J��ئW�٩M���Z
		person1.addCourse(new Course(Constant.CONSTANT_MATH,94)); // ��J��ئW�٩M���Z
		// �ǥͤG
		Person person2 = new Person();
		person2.setName("Jane");
		person2.addCourse(new Course(Constant.CONSTANT_CHINESE,95));
		person2.addCourse(new Course(Constant.CONSTANT_ENGLISH,80));
		person2.addCourse(new Course(Constant.CONSTANT_MATH,65));
		// �ǥͤT
		Person person3 = new Person();
		person3.setName("Peter");
		person3.addCourse(new Course(Constant.CONSTANT_CHINESE,88));
		person3.addCourse(new Course(Constant.CONSTANT_ENGLISH,84));
		person3.addCourse(new Course(Constant.CONSTANT_MATH,81));
		// �ǥͥ|
		Person person4 = new Person();
		person4.setName("Harry");
		person4.addCourse(new Course(Constant.CONSTANT_CHINESE,91));
		person4.addCourse(new Course(Constant.CONSTANT_ENGLISH,92));
		person4.addCourse(new Course(Constant.CONSTANT_MATH,79));
		
		sc.addToPersonList(person1); // ��ǥͤ@�@�[�J
		sc.addToPersonList(person2);
		sc.addToPersonList(person3);
		sc.addToPersonList(person4);
		
		// �L��ئW��
		System.out.println("\t"+Constant.CONSTANT_CHINESE+"\t"+Constant.CONSTANT_ENGLISH+"\t"+Constant.CONSTANT_MATH);
		
		// ���o�Ҧ����ǥͦC��
		List personList = sc.getPersonList();
		
		// �@�@���o�ǥ�
		for(int i=0;i<personList.size();i++){
			Person person = (Person)personList.get(i); // ���X�ӱo��Object�নPerson
			System.out.print(person.getName()+"\t"); // �L�X�W�r
			List scoreList = person.getCourseList(); // �Ѿǥͪ�����o�Ҧ���ئC��
			for(int j=0;j<scoreList.size();j++){
				Course Course = (Course) scoreList.get(j);
				System.out.print(Course.getScore()+"\t"); // �L�X���Z
			}
			System.out.print(person.getAvg()); // ���o���Z�æL�X
			System.out.println(); // ����
		}
		
		// ���o�U�쥭���æL�X
		System.out.print("\t"+Constant.calAvg(personList, Constant.CONSTANT_CHINESE)+"\t"+Constant.calAvg(personList, Constant.CONSTANT_ENGLISH)+"\t"+Constant.calAvg(personList, Constant.CONSTANT_MATH)+"\t"+Constant.calAllAvg(personList));
	}
}

/**
 * �ǥͪ���
 * @author Kirk
 *
 */
class Person{
	private String name; // �W��
	private List courseList = new ArrayList(); // ��ئ��Z�C��
	
	/**
	 * ���o��ئ��Z�C��
	 * @return
	 */
	public List getCourseList() {
		return courseList;
	}
	/**
	 * ���o�ǥͦW��
	 * @return
	 */
	public String getName() {
		return name;
	}
	/**
	 * �]�w�ǥͦW��
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * �s�W��ئ��Z����
	 * @param course
	 */
	public void addCourse(Course course){
		this.courseList.add(course);
	}
	/**
	 * ���o�Ҧ���ئ��Z�`�M
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
	 * ���o�Ҧ���ئ��Z����
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
	 * �̬�ئW�٨��o��ت���
	 * @param courseName ��ئW��
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
 * ��ت���
 * @author Kirk
 *
 */
class Course{
	private String name; // ��ئW��
	private double score; // ��ئ��Z
	
	/**
	 * �o�O�w�]���غc�l�A�p�G�S���[�N���� Course c = new Course(); �Ӥ@�w�nCourse c = new Course(xx,yy);
	 */
	Course(){};
	
	/**
	 * �غc�l�A�u�O��K�bPerson�̷s�W
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
 * �o�Ψөw�q�`��
 * @author Kirk
 *
 */
class Constant{
	static public final String CONSTANT_CHINESE = "Chinese";
	static public final String CONSTANT_ENGLISH = "English";
	static public final String CONSTANT_MATH = "Math";
	
	/**
	 * �̬�ئW�ٺ�X����
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
