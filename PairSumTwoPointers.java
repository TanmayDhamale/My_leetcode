import java.util.Arrays;

class PairSumTwoPointers {
    public static boolean findPair(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == target) {
                System.out.println("Pair found: (" + arr[left] + ", " + arr[right] + ")");
                return true;
            } else if (sum < target) {
                left++; // Move right to increase sum
            } else {
                right--; // Move left to decrease sum
            }
        }
        System.out.println("No pair found");
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 6, 8, 9};
        int target = 10;
        System.out.println("Sorted Array: " + Arrays.toString(arr));
        findPair(arr, target);
    }
}