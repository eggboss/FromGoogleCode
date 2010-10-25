public class Timetable33 {
   public static void main(String[] args) {
      int i, j, sum;                                               // 變數宣告
      
      for ( i = 1; i <= 9; i++) {                                  // 先求出1-3的九九乘法表
         for ( j = 1; j <= 3; j++) {
            sum = i * j;
            System.out.print(j + "*" + i + "=" + sum + "\t");      // 將i，j名稱互換輸出以求解
         }
         System.out.println();
      }
      System.out.println("----------------------");                // 輸出一道分隔線
      
      for ( i = 1; i <= 9; i++) {                                  // 求出4-6的九九乘法表
         for ( j = 4; j <= 6; j++) {                               
            sum = i * j;
            System.out.print(j + "*" + i + "=" + sum + "\t");      // 將i，j名稱互換以求解
         }
         System.out.println();
      }
      System.out.println("----------------------");                // 輸出一道分隔線

      for ( i = 1; i <= 9; i++) {                                  // 求出7-9的九九乘法表              
         for ( j = 7; j <= 9; j++) {                               
            sum = i * j;
            System.out.print(j + "*" + i + "=" + sum + "\t");
         }
         System.out.println();                                     // 即可求得三段式九九乘法表
      }
      
      System.out.println();                                        // 分隔線亦可用註解方法/*...*/取消
   }
}