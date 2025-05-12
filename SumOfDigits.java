import java.util.Scanner;

public class SumOfDigits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number
        System.out.print("Enter a number: ");
        int number = sc.nextInt();

        int sum = 0;

        // Calculate sum of digits
        while (number != 0) {
            sum += number % 10;   // Get last digit
            number /= 10;         // Remove last digit
        }

        System.out.println("Sum of digits: " + sum);
        sc.close();
    }
}