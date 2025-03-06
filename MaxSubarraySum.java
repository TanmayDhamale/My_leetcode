class MaxSubarraySum {
    public static int maxSubarraySum(int[] arr, int k) {
        int n = arr.length;
        if (n < k) {
            System.out.println("Invalid input: window size is larger than array size.");
            return -1;
        }

        int maxSum = 0, windowSum = 0;

        // Calculate sum of first window
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        maxSum = windowSum;

        // Slide the window across the array
        for (int i = k; i < n; i++) {
            windowSum += arr[i] - arr[i - k];  // Add new element, remove old element
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 1, 3, 2};
        int k = 3;
        System.out.println("Max sum of subarray of size " + k + " = " + maxSubarraySum(arr, k));
    }
}