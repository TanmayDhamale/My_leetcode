import java.util.Arrays;

class RadixSort {
    // Function to get the maximum value in the array
    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int num : arr) {
            if (num > max)
                max = num;
        }
        return max;
    }

    // Counting sort used by Radix Sort (Sorts based on digit position)
    private static void countingSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10]; // Digits 0-9

        // Count occurrences of digits
        for (int num : arr) {
            count[(num / exp) % 10]++;
        }

        // Cumulative count
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (int i = n - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // Copy sorted numbers back to original array
        System.arraycopy(output, 0, arr, 0, n);
    }

    // Radix Sort function
    public static void radixSort(int[] arr) {
        int max = getMax(arr);

        // Apply counting sort for each digit
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    public static void main(String[] args) {
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
        System.out.println("Before Sorting: " + Arrays.toString(arr));

        radixSort(arr);

        System.out.println("After Sorting: " + Arrays.toString(arr));
    }
}