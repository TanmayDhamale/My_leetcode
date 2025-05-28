import java.util.Scanner;

public class AddSpaces {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        StringBuilder spaced = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            spaced.append(input.charAt(i));
            if (i < input.length() - 1) {
                spaced.append(' '); // Add space between characters
            }
        }

        System.out.println("String with spaces: " + spaced.toString());
    }
}