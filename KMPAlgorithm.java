class KMPAlgorithm {
    // Function to compute LPS array
    private static int[] computeLPS(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int j = 0; // Length of previous longest prefix suffix

        for (int i = 1; i < m; i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = lps[j - 1]; // Backtrack to previous LPS position
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                j++;
                lps[i] = j;
            }
        }
        return lps;
    }

    // KMP Algorithm for Pattern Searching
    public static void KMPSearch(String text, String pattern) {
        int n = text.length();
        int m = pattern.length();
        int[] lps = computeLPS(pattern);
        int i = 0, j = 0;

        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
            if (j == m) {
                System.out.println("Pattern found at index " + (i - j));
                j = lps[j - 1]; // Use LPS array to skip unnecessary comparisons
            } else if (i < n && text.charAt(i) != pattern.charAt(j)) {
                if (j > 0) {
                    j = lps[j - 1]; // Move to the previous LPS position
                } else {
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        String text = "ababcabcabababd";
        String pattern = "ababd";

        KMPSearch(text, pattern);
    }
}