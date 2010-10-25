public class Score33 {
	 public static void main(String[] args) {
      int i, j, personSum, courseSum, totalSum;                      // 宣告int型態變數
      String[] name={"Amy","Jane","Peter","Harry"};                  // 宣告一維String陣列並初始化
      int[][] score={{67,80,94},{95,80,65},{88,84,81},{91,92,70}};   // 宣告二維int陣列並初始化
      
      System.out.print("             Chinese          English          Math     個人平均"+"\n");
                                                                     // 輸出第一列title
      for ( i=0; i < score.length; i++) {                            // name.length=score.length=4
      	 System.out.print(name[i]+"\t");                             // 故String和int陣列可一同輸出
      	 personSum=0;                                                // 將個人成績總和初始化
      	 for ( j=0; j < score[j].length; j++) {
      	    personSum += score[i][j];                               
      	    System.out.print("\t"+score[i][j]+"\t");                 // 利用巢狀迴圈列出分數
      	 }
      	 System.out.println(personSum/3.0);                          // 輸出個人平均
      }
      System.out.print("每科平均");
      
      totalSum=0;                                                    // 全班成績總和初始化
      for ( j=0; j < score[j].length; j++) {
      	 courseSum=0;                                                // 每科總和初始化
         for ( i=0; i < score.length; i++) {
            courseSum += score[i][j];
            totalSum += score[i][j]; 
         }
         System.out.print("\t"+courseSum/4.0+"\t");                  // 輸出每科平均
      }
      System.out.print(totalSum/12.0);                               // 輸出全班總平均
   }  
}