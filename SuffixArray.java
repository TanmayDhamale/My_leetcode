import java.util.Arrays;
import java.util.Comparator;

public class SuffixArray {

    // A class to hold suffix and its index
    static class Suffix {
        String suffix;
        int index;

        Suffix(String suffix, int index) {
            this.suffix = suffix;
            this.index = index;
        }
    }

    // Function to build the suffix array
    static int[] buildSuffixArray(String text) {
        int n = text.length();
        Suffix[] suffixes = new Suffix[n];

        // Store suffixes with their original indices
        for (int i = 0; i < n; i++) {
            suffixes[i] = new Suffix(text.substring(i), i);
        }

        // Sort the suffixes lexicographically
        Arrays.sort(suffixes, Comparator.comparing(s -> s.suffix));

        // Store the result (only indices)
        int[] suffixArray = new int[n];
        for (int i = 0; i < n; i++) {
            suffixArray[i] = suffixes[i].index;
        }

        return suffixArray;
    }

    public static void main(String[] args) {
        String text = "banana";
        int[] suffixArr = buildSuffixArray(text);

        System.out.println("Suffix Array for \"" + text + "\":");
        for (int i : suffixArr) {
            System.out.print(i + " ");
        }
    }
}