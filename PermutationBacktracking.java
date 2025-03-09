import java.util.*;

class PermutationBacktracking {
    public static void generatePermutations(int[] nums, int index, List<List<Integer>> result) {
        if (index == nums.length) {
            List<Integer> permutation = new ArrayList<>();
            for (int num : nums) permutation.add(num);
            result.add(permutation);
            return;
        }
        
        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);
            generatePermutations(nums, index + 1, result);
            swap(nums, index, i);  // Backtrack
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> result = new ArrayList<>();
        generatePermutations(nums, 0, result);

        System.out.println("Permutations: " + result);
    }
}