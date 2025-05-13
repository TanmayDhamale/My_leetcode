import java.util.HashMap;
import java.util.Scanner;

public class CharFrequency {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input string
        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        // Use a HashMap to store character frequencies
        HashMap<Character, Integer> freqMap = new HashMap<>();

        for (char c : input.toCharArray()) {
            // Ignore spaces (optional)
            if (c != ' ') {
                freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            }
        }

        // Print character frequencies
        System.out.println("Character Frequencies:");
        for (char c : freqMap.keySet()) {
            System.out.println(c + " : " + freqMap.get(c));
        }

        sc.close();
    }
}