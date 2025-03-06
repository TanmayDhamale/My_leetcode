import java.util.HashMap;

class LongestUniqueSubstring {
    public static int longestUniqueSubstring(String s) {
        HashMap<Character, Integer> charIndex = new HashMap<>();
        int left = 0, maxLength = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // If character is repeated, move left pointer
            if (charIndex.containsKey(currentChar)) {
                left = Math.max(left, charIndex.get(currentChar) + 1);
            }

            // Store/update the latest index of the character
            charIndex.put(currentChar, right);

            // Update max length of unique substring
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println("Length of longest substring without repeating characters: " + longestUniqueSubstring(s));
    }
}