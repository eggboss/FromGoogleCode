
public class NN {
	public static void main(String[] args){
		for(int i=1;i<=3;i++){
			for(int j=1;j<=9;j++){
				System.out.print(1 + "*" + j + "=" + (i*j) + "\t");
				if(j%3==0)
					System.out.println();
			}
		}
	}
}
