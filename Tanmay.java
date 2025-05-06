// import java.util.Scanner;

// public class Tanmay {
//     public static void main(String[] args)
//     {
//         System.out.println ("Enter your name ");
//         Scanner sc = new Scanner(System.in);
//         String name = sc.nextLine();

//         // System.out.println("Welcome"+" "+name);

//         System.out.println("Relation with Tanmay ?");
//         String relation = sc.nextLine();

//         // System.out.println("Your relation with Tanmay is"+"  "+relation);
//         System.out.println("welcome"   + name + " , Tanmay's"   + relation);
//     }
    
    
// }
import java.util.Scanner;

public class Tanmay {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine().trim(); // .trim() removes extra spaces

        System.out.print("Your relation with Tanmay: ");
        String relation = scanner.nextLine().trim();

        System.out.println("\nWelcome " + name + "!");
        System.out.println("Your relation with Tanmay is: " + relation);
        System.out.println("Welcome " + name + ", Tanmay's " + relation + "!");

        scanner.close(); // Always close Scanner
    }
}