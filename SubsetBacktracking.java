import java.util.*;

class SubsetBacktracking {
    public static void generateSubsets(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        // Include the current element
        current.add(nums[index]);
        generateSubsets(nums, index + 1, current, result);
        
        // Backtrack (Remove the last element and explore next possibility)
        current.remove(current.size() - 1);
        generateSubsets(nums, index + 1, current, result);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = new ArrayList<>();
        generateSubsets(nums, 0, new ArrayList<>(), result);

        System.out.println("Subsets: " + result);
    }
}