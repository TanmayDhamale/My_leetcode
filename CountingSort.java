import java.util.Arrays;

class CountingSort {
    public static void countingSort(int[] arr) {
        int n = arr.length;
        if (n == 0) return;

        int max = Arrays.stream(arr).max().getAsInt(); // Find maximum value
        int min = Arrays.stream(arr).min().getAsInt(); // Find minimum value
        int range = max - min + 1; // Determine range

        int[] count = new int[range];
        int[] output = new int[n];

        // Count occurrences
        for (int num : arr) {
            count[num - min]++;
        }

        // Cumulative count
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        // Place elements in sorted order
        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        // Copy sorted array back to original
        System.arraycopy(output, 0, arr, 0, n);
    }

    public static void main(String[] args) {
        int[] arr = {4, 2, 2, 8, 3, 3, 1};
        System.out.println("Before Sorting: " + Arrays.toString(arr));

        countingSort(arr);

        System.out.println("After Sorting: " + Arrays.toString(arr));
    }
}