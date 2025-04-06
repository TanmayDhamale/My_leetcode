public class EggDropping {

    // Function to get minimum number of trials needed in worst case
    public static int eggDrop(int eggs, int floors) {
        int[][] dp = new int[eggs + 1][floors + 1];

        // Base cases:
        // 1. One egg: we try all floors one by one
        for (int j = 1; j <= floors; j++) {
            dp[1][j] = j;
        }

        // 2. Zero or one floor: number of trials = floor count
        for (int i = 1; i <= eggs; i++) {
            dp[i][0] = 0;
            dp[i][1] = 1;
        }

        // Fill the rest of the table
        for (int i = 2; i <= eggs; i++) {
            for (int j = 2; j <= floors; j++) {
                dp[i][j] = Integer.MAX_VALUE;

                // Try dropping from each floor x (1 to j)
                for (int x = 1; x <= j; x++) {
                    int breaks = dp[i - 1][x - 1];  // Egg breaks
                    int survives = dp[i][j - x];   // Egg doesn't break
                    int trials = 1 + Math.max(breaks, survives);
                    dp[i][j] = Math.min(dp[i][j], trials);
                }
            }
        }

        return dp[eggs][floors];
    }

    public static void main(String[] args) {
        int eggs = 2;
        int floors = 10;

        System.out.println("Minimum number of trials in worst case with "
                + eggs + " eggs and " + floors + " floors is: " + eggDrop(eggs, floors));
    }
}