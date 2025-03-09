import java.util.*;

class CombinationBacktracking {
    public static void generateCombinations(int[] nums, int start, int k, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            generateCombinations(nums, i + 1, k, current, result);
            current.remove(current.size() - 1);  // Backtrack
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int k = 2;
        List<List<Integer>> result = new ArrayList<>();
        generateCombinations(nums, 0, k, new ArrayList<>(), result);

        System.out.println("Combinations of size " + k + ": " + result);
    }
}