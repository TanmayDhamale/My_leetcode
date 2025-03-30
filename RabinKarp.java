import java.util.*;

class RabinKarp {
    private static final int PRIME = 101; // A prime number for hashing

    public static List<Integer> rabinKarpSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        long patternHash = createHash(pattern, m);
        long textHash = createHash(text, m);
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i <= n - m; i++) {
            if (patternHash == textHash && text.substring(i, i + m).equals(pattern)) {
                result.add(i); // Found match
            }
            if (i < n - m) {
                textHash = recalculateHash(text, textHash, i, i + m, m);
            }
        }

        return result;
    }

    private static long createHash(String str, int length) {
        long hash = 0;
        for (int i = 0; i < length; i++) {
            hash += str.charAt(i) * Math.pow(PRIME, i);
        }
        return hash;
    }

    private static long recalculateHash(String str, long oldHash, int oldIndex, int newIndex, int patternLen) {
        long newHash = oldHash - str.charAt(oldIndex);
        newHash /= PRIME;
        newHash += str.charAt(newIndex) * Math.pow(PRIME, patternLen - 1);
        return newHash;
    }

    public static void main(String[] args) {
        String text = "ABABABCABABABCABABABC";
        String pattern = "ABABC";
        List<Integer> indices = rabinKarpSearch(text, pattern);

        if (indices.isEmpty()) {
            System.out.println("Pattern not found.");
        } else {
            System.out.println("Pattern found at indices: " + indices);
        }
    }
}