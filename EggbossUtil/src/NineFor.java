public class NineFor {
   public static void main(String[] args) {
      int i , j=1;
   
      for(i=1;i<=9;i++) {
      	for(;j<=9;j++) {
      	   if(j<=3) {
      	      System.out.print(j+"*"+i+"="+i*j+"\t");
      	      if(j==3&&i==9) {
      	      	 i=1;
      	         System.out.println();
      	         System.out.println();
      	      } 
      	      else if(j==3) {
      	         j=1;
      	         break;
      	      }
      	   }
      	
      	   if(j>3&&j<=6) {
      	      System.out.print(j+"*"+i+"="+i*j+"\t");
      	      if(j==6&&i==9) {
      	      	 i=1;
      	         System.out.println();
      	         System.out.println();
      	      }
      	      else if(j==6) {
      	         j=4;
      	         break;
      	      }
      	   }
      	
      	   if(j>6) {
      	      System.out.print(j+"*"+i+"="+i*j+"\t");  
      	      if(j==9) {
      	         j=7;
      	         break;
      	      }
      	   }
         }
         System.out.println();  
      }
   }
}