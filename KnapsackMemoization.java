import java.util.Arrays;

class KnapsackMemoization {
    public static int knapsack(int[] weights, int[] values, int n, int W, int[][] dp) {
        if (n == 0 || W == 0) return 0;

        if (dp[n][W] != -1) return dp[n][W]; // Use precomputed value

        if (weights[n - 1] > W)
            return dp[n][W] = knapsack(weights, values, n - 1, W, dp);

        return dp[n][W] = Math.max(values[n - 1] + knapsack(weights, values, n - 1, W - weights[n - 1], dp),
                                   knapsack(weights, values, n - 1, W, dp));
    }

    public static void main(String[] args) {
        int[] weights = {2, 3, 4, 5};
        int[] values = {3, 4, 5, 6};
        int W = 5;
        int n = weights.length;
        int[][] dp = new int[n + 1][W + 1];

        for (int[] row : dp) Arrays.fill(row, -1);

        System.out.println("Max Value: " + knapsack(weights, values, n, W, dp));
    }
}