import java.util.*;

class ZAlgorithm {
    public static List<Integer> searchPattern(String text, String pattern) {
        String concat = pattern + "$" + text;
        int[] Z = calculateZArray(concat);
        List<Integer> result = new ArrayList<>();
        int patternLength = pattern.length();

        for (int i = 0; i < Z.length; i++) {
            if (Z[i] == patternLength) {
                result.add(i - patternLength - 1); // Adjust for pattern and '$'
            }
        }

        return result;
    }

    private static int[] calculateZArray(String str) {
        int n = str.length();
        int[] Z = new int[n];
        int left = 0, right = 0;

        for (int i = 1; i < n; i++) {
            if (i <= right) {
                Z[i] = Math.min(right - i + 1, Z[i - left]);
            }
            while (i + Z[i] < n && str.charAt(Z[i]) == str.charAt(i + Z[i])) {
                Z[i]++;
            }
            if (i + Z[i] - 1 > right) {
                left = i;
                right = i + Z[i] - 1;
            }
        }
        return Z;
    }

    public static void main(String[] args) {
        String text = "ababcababcababc";
        String pattern = "ababc";
        List<Integer> indices = searchPattern(text, pattern);

        if (indices.isEmpty()) {
            System.out.println("Pattern not found.");
        } else {
            System.out.println("Pattern found at indices: " + indices);
        }
    }
}