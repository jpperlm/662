package blackjack;
import java.util.InputMismatchException;
import java.util.Scanner;

public interface Inputable {

   public default Integer waitResponse(Object[] options) {
      return this.waitResponse(options, "");
   }
   public default Integer waitResponse(Object[] options, String exit) {
      Integer i = 1;
      for (Object opt: options) {
         System.out.println("| " + i + ") " +  opt);
         ++i;
      }
      Integer maxInput = options.length;
      if (exit != "") {
         maxInput++;
         System.out.println("| " + i + ") " +  exit);
      }
      Scanner input = new Scanner(System.in);
      int number = 0;
      while (number == 0){
         try {
            System.out.print("Enter a number choice: ");
            number = input.nextInt();
            if (number > maxInput || number < 0) {
               System.out.println("Nice try... ");
               number = 0;
            }
         } catch (InputMismatchException ex) {
            System.out.println("That wasn't a number...");
            input.nextLine();
            continue;
         }
      }
      return number - 1;
   }

}
