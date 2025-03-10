class KnapsackRecursive {
    public static int knapsack(int[] weights, int[] values, int n, int W) {
        if (n == 0 || W == 0) return 0; // Base case: no items or no capacity
        
        if (weights[n - 1] > W) // If item weight exceeds current capacity, skip it
            return knapsack(weights, values, n - 1, W);
        
        // Take max of including or excluding the current item
        return Math.max(values[n - 1] + knapsack(weights, values, n - 1, W - weights[n - 1]),
                        knapsack(weights, values, n - 1, W));
    }

    public static void main(String[] args) {
        int[] weights = {2, 3, 4, 5};
        int[] values = {3, 4, 5, 6};
        int W = 5;
        int n = weights.length;
        System.out.println("Max Value: " + knapsack(weights, values, n, W));
    }
}
