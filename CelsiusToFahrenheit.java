import java.util.Scanner;

public class CelsiusToFahrenheit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input temperature in Celsius
        System.out.print("Enter temperature in Celsius: ");
        float celsius = sc.nextFloat();

        // Conversion formula: F = (C × 9/5) + 32
        float fahrenheit = (celsius * 9 / 5) + 32;

        // Output the result
        System.out.println(celsius + "°C = " + fahrenheit + "°F");

        sc.close();
    }
}