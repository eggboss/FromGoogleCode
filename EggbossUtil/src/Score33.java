public class Score33 {
	 public static void main(String[] args) {
      int i, j, personSum, courseSum, totalSum;                      // �ŧiint���A�ܼ�
      String[] name={"Amy","Jane","Peter","Harry"};                  // �ŧi�@��String�}�C�ê�l��
      int[][] score={{67,80,94},{95,80,65},{88,84,81},{91,92,70}};   // �ŧi�G��int�}�C�ê�l��
      
      System.out.print("             Chinese          English          Math     �ӤH����"+"\n");
                                                                     // ��X�Ĥ@�Ctitle
      for ( i=0; i < score.length; i++) {                            // name.length=score.length=4
      	 System.out.print(name[i]+"\t");                             // �GString�Mint�}�C�i�@�P��X
      	 personSum=0;                                                // �N�ӤH���Z�`�M��l��
      	 for ( j=0; j < score[j].length; j++) {
      	    personSum += score[i][j];                               
      	    System.out.print("\t"+score[i][j]+"\t");                 // �Q�α_���j��C�X����
      	 }
      	 System.out.println(personSum/3.0);                          // ��X�ӤH����
      }
      System.out.print("�C�쥭��");
      
      totalSum=0;                                                    // ���Z���Z�`�M��l��
      for ( j=0; j < score[j].length; j++) {
      	 courseSum=0;                                                // �C���`�M��l��
         for ( i=0; i < score.length; i++) {
            courseSum += score[i][j];
            totalSum += score[i][j]; 
         }
         System.out.print("\t"+courseSum/4.0+"\t");                  // ��X�C�쥭��
      }
      System.out.print(totalSum/12.0);                               // ��X���Z�`����
   }  
}