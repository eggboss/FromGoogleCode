public class Timetable33 {
   public static void main(String[] args) {
      int i, j, sum;                                               // �ܼƫŧi
      
      for ( i = 1; i <= 9; i++) {                                  // ���D�X1-3���E�E���k��
         for ( j = 1; j <= 3; j++) {
            sum = i * j;
            System.out.print(j + "*" + i + "=" + sum + "\t");      // �Ni�Aj�W�٤�����X�H�D��
         }
         System.out.println();
      }
      System.out.println("----------------------");                // ��X�@�D���j�u
      
      for ( i = 1; i <= 9; i++) {                                  // �D�X4-6���E�E���k��
         for ( j = 4; j <= 6; j++) {                               
            sum = i * j;
            System.out.print(j + "*" + i + "=" + sum + "\t");      // �Ni�Aj�W�٤����H�D��
         }
         System.out.println();
      }
      System.out.println("----------------------");                // ��X�@�D���j�u

      for ( i = 1; i <= 9; i++) {                                  // �D�X7-9���E�E���k��              
         for ( j = 7; j <= 9; j++) {                               
            sum = i * j;
            System.out.print(j + "*" + i + "=" + sum + "\t");
         }
         System.out.println();                                     // �Y�i�D�o�T�q���E�E���k��
      }
      
      System.out.println();                                        // ���j�u��i�ε��Ѥ�k/*...*/����
   }
}