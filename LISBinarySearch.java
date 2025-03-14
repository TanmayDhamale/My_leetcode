import java.util.*;

class LISBinarySearch {
    public static int lengthOfLIS(int[] nums) {
        List<Integer> sub = new ArrayList<>();

        for (int num : nums) {
            int idx = Collections.binarySearch(sub, num);
            if (idx < 0) idx = -(idx + 1);

            if (idx == sub.size()) {
                sub.add(num);  // Append if greater than all elements
            } else {
                sub.set(idx, num);  // Replace first greater element
            }
        }
        return sub.size(); // Length of the list is the LIS length
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Length of LIS: " + lengthOfLIS(nums)); // Output: 4
    }
}