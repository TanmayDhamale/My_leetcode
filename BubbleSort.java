public class BubbleSort {
    public static void bubbleSort(int arr[]) {
        int n = arr.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            // If no two elements were swapped, the array is already sorted
            if (!swapped) break;
        }
    }

    public static void printArray(int arr[]) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String args[]) {
        int arr[] = {74, 64, 45, 52, 32, 21, 90};
        bubbleSort(arr);
        System.out.println("Sorted array:");
        printArray(arr);
    }
}
