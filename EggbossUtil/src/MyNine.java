
public class MyNine {
	static public void main(String[] args){
		int count = 1;
		while(count<4){
			for(int j=1;j<=9;j++){
				for(int i=1;i<=3;i++){
					int ii = i+(count-1)*3;
					System.out.print(ii+"*"+j+"="+(ii*j)+"\t");
				}
				System.out.println();
			}
			count++;
			System.out.println();
		}
	}
}
