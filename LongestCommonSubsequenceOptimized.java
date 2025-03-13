class LongestCommonSubsequenceOptimized {
    public static int findLCS(String X, String Y) {
        int m = X.length(), n = Y.length();
        int[] prev = new int[n + 1], curr = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    curr[j] = 1 + prev[j - 1];  // Characters match
                } else {
                    curr[j] = Math.max(prev[j], curr[j - 1]);  // Max of excluding one char
                }
            }
            prev = curr.clone();
        }
        return prev[n];
    }

    public static void main(String[] args) {
        String X = "abcdgh";
        String Y = "abedfhr";
        System.out.println("Length of LCS: " + findLCS(X, Y));  // Output: 4 ("abdh")
    }
}