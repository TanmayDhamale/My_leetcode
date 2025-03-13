class LongestCommonSubsequence {
    // Function to compute LCS using Bottom-Up DP
    public static int findLCS(String X, String Y) {
        int m = X.length();
        int n = Y.length();
        int[][] dp = new int[m + 1][n + 1];

        // Fill DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];  // Characters match
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);  // Max of excluding one char
                }
            }
        }
        return dp[m][n];  // LCS length
    }

    // Driver Code
    public static void main(String[] args) {
        String X = "abcdgh";
        String Y = "abedfhr";
        System.out.println("Length of LCS: " + findLCS(X, Y)); // Output: 4 ("abdh")
    }
}