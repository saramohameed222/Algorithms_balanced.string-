/**
 * Longest Balanced Substring — Algorithm 1: Brute Force (Non-Recursive)
 *
 * Strategy:
 *   Use two nested loops to generate every possible substring of S.
 *   For each substring, count the frequency of every character and
 *   check the balanced condition:
 *     (1) exactly 2 distinct characters
 *     (2) both appear the same number of times
 *   Track the length of the longest balanced substring found.
 *
 * Time Complexity : O(n^3)
 *   - O(n^2) substrings × O(n) to check each one
 * Space Complexity: O(1)  (only a fixed 26-element array)
 */
public class BalancedStringBruteForce {

    /**
     * Checks whether the given string is balanced:
     * exactly 2 distinct characters, each with equal frequency.
     */
    public static boolean isBalanced(String s) {
        int[] freq = new int[26]; //(26) from A-Z

        // Count frequency of each character
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }

        int distinctCount = 0;
        int firstFreq     = -1;

        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                distinctCount++;
                if (firstFreq == -1) {
                    firstFreq = freq[i];          // record the first seen frequency
                } else if (freq[i] != firstFreq) {
                    return false;                 // frequencies differ → not balanced
                }
            }
        }
        return distinctCount == 2;                // must have exactly 2 distinct chars
    }

    /**
     * Returns the length of the longest balanced substring of S.
     * Iterates over all O(n^2) substrings with two nested for-loops.
     */
    public static int longestBalancedSubstring(String s) {
        int n      = s.length();
        int maxLen = 0;

        for (int i = 0; i < n; i++) {                   // start index
            for (int j = i + 2; j <= n; j++) {           // end index (min length 2)
                String sub = s.substring(i, j);
                if (isBalanced(sub)) {
                    maxLen = Math.max(maxLen, sub.length());
                }
            }
        }
        return maxLen;
    }

    // ---------------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("=== Algorithm 1: Brute Force (Non-Recursive) ===");
        System.out.println();
        runTest("cabbacc", 4);
        runTest("abababa", 6);
        runTest("aaaaaaa", 0);
        runTest("aabbab",  6);
        runTest("abcabc",  2);
        runTest("aabb",    4);
        runTest("ab",      2);
    }

    private static void runTest(String s, int expected) {
        int result = longestBalancedSubstring(s);
        String status = (result == expected) ? "PASS" : "FAIL";
        System.out.printf("[%s]  Input: %-12s  Expected: %d  Got: %d%n",
                status, "\"" + s + "\"", expected, result);
    }
}
