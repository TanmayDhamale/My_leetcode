import java.util.Arrays;

class TripletSumTwoPointers {
    public static boolean findTriplet(int[] arr, int target) {
        Arrays.sort(arr); // Ensure the array is sorted
        int n = arr.length;

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;

            while (left < right) {
                int sum = arr[i] + arr[left] + arr[right];

                if (sum == target) {
                    System.out.println("Triplet found: (" + arr[i] + ", " + arr[left] + ", " + arr[right] + ")");
                    return true;
                } else if (sum < target) {
                    left++; // Move right to increase sum
                } else {
                    right--; // Move left to decrease sum
                }
            }
        }
        System.out.println("No triplet found");
        return false;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 6, 8, 10, 12};
        int target = 18;
        System.out.println("Sorted Array: " + Arrays.toString(arr));
        findTriplet(arr, target);
    }
}