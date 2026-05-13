import java.util.Scanner;

public class BalancedStringBacktracking {

    /**
     * Helper method to recursively expand the substring window starting at 'start' to 'end'.
     * Accumulates character frequencies dynamically to achieve optimal efficiency.
     */
    private static int expandRecursive(String s, int start, int end, int[] freq, int distinctCount) {
        // Base Case: If the end pointer exceeds string length, stop expansion
        if (end > s.length()) {
            return 0;
        }

        // Include the new character at (end - 1) into our frequency map
        int charIdx = s.charAt(end - 1) - 'a';
        if (freq[charIdx] == 0) {
            distinctCount++;
        }
        freq[charIdx]++;

        // Pruning Optimization: If distinct characters exceed 2, stop expanding further
        // because we cannot remove characters to make it balanced again.
        if (distinctCount > 2) {
            freq[charIdx]--; // Backtrack state before returning
            return 0;
        }

        int currentBalancedLen = 0;
        // Check if the current window is perfectly balanced
        if (distinctCount == 2) {
            int count1 = -1;
            int count2 = -1;
            for (int k = 0; k < 26; k++) {
                if (freq[k] > 0) {
                    if (count1 == -1) {
                        count1 = freq[k];
                    } else {
                        count2 = freq[k];
                        break;
                    }
                }
            }
            if (count1 == count2) {
                currentBalancedLen = end - start;
            }
        }

        // Recurse to check longer substrings starting at the same 'start' index
        int maxSubsequent = expandRecursive(s, start, end + 1, freq, distinctCount);

        // Backtrack frequency state to leave the array clean for other explorations
        freq[charIdx]--;

        return Math.max(currentBalancedLen, maxSubsequent);
    }

    /**
     * Helper method to recursively try all possible starting indices for the substring.
     */
    private static int tryAllStartsRecursive(String s, int start) {
        // Base Case: Substrings must be at least length 2, so start can go up to length - 2
        if (start >= s.length() - 1) {
            return 0;
        }

        int[] freq = new int[26];
        // Find the longest balanced substring starting exactly at 'start'
        int maxCurrentStart = expandRecursive(s, start, start + 1, freq, 0);
        
        // Find the longest balanced substring starting at subsequent indices
        int maxNextStarts = tryAllStartsRecursive(s, start + 1);

        return Math.max(maxCurrentStart, maxNextStarts);
    }

    /**
     * Efficient Recursive Algorithm to find the length of the longest balanced substring.
     * Time Complexity: O(N^2) - Explores each valid window exactly once without redundant branching.
     * Space Complexity: O(N) auxiliary space for the recursion call stack.
     */
    public static int longestBalancedSubstring(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        return tryAllStartsRecursive(s, 0);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("==================================================");
        System.out.println("   Algorithm 2: Optimized Recursive Approach      ");
        System.out.println("==================================================");
        System.out.println();

        System.out.print("Enter the string: ");
        String s = input.nextLine().trim();

        if (s.isEmpty()) {
            System.out.println("Input cannot be empty.");
            input.close();
            return;
        }

        // Standalone protection ensuring strictly alphabetical letters are processed
        if (!s.matches("[a-zA-Z]+")) {
            System.out.println("Invalid input! Please enter an alphabetical string containing only letters (a-z).");
            input.close();
            return;
        }

        s = s.toLowerCase();

        long startTime = System.nanoTime();
        int result = longestBalancedSubstring(s);
        long endTime = System.nanoTime();

        System.out.println("\nLongest Balanced Substring Length = " + result);
        System.out.printf("Execution Time: %.4f ms%n", (endTime - startTime) / 1_000_000.0);

        input.close();
    }
}