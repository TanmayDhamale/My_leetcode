public class MatrixChainMultiplication {

    // Function to find the most efficient way to multiply the given matrices
    public static int matrixChainOrder(int[] dims) {
        int n = dims.length;
        int[][] dp = new int[n][n];

        // dp[i][j] = Minimum number of scalar multiplications needed to compute matrix A[i]A[i+1]...A[j] = A[i..j]
        // The cost is zero when multiplying one matrix
        for (int len = 2; len < n; len++) {
            for (int i = 1; i < n - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + dims[i - 1] * dims[k] * dims[j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }

        return dp[1][n - 1];
    }

    public static void main(String[] args) {
        int[] dimensions = {40, 20, 30, 10, 30}; // Dimensions of matrices
        int minMultiplications = matrixChainOrder(dimensions);
        System.out.println("Minimum number of multiplications is: " + minMultiplications);
    }
}