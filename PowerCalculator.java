import java.util.Scanner;

public class PowerCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input base and exponent
        System.out.print("Enter base (a): ");
        int base = sc.nextInt();

        System.out.print("Enter exponent (b): ");
        int exponent = sc.nextInt();

        long result = 1;

        // Calculate a^b
        for (int i = 1; i <= exponent; i++) {
            result *= base;
        }

        System.out.println(base + "^" + exponent + " = " + result);
        sc.close();
    }
}