import java.util.Scanner;

public class BalancedStringBruteForce {

    /**
     * Efficient Non-Recursive Algorithm to find the length of the longest balanced substring.
     * Time Complexity: O(N^2) - Iterates through all starting indices and expands the window dynamically.
     * Space Complexity: O(1) auxiliary space (uses a fixed-size 26 integer array).
     */
    public static int longestBalancedSubstring(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }

        int n = s.length();
        int maxLen = 0;

        // Iterate over each possible starting position of the substring
        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];
            int distinctCount = 0;

            // Expand the end position dynamically to the right
            for (int j = i; j < n; j++) {
                int charIdx = s.charAt(j) - 'a';
                
                // If this is the first time seeing this character in the current window
                if (freq[charIdx] == 0) {
                    distinctCount++;
                }
                freq[charIdx]++;

                // Pruning Optimization: A balanced string MUST have exactly 2 distinct characters.
                // If we encounter a 3rd distinct character, no further expansion from start index 'i'
                // can ever be valid, so we can immediately break out of the inner loop.
                if (distinctCount > 2) {
                    break;
                }

                // If the window contains exactly 2 distinct characters, verify if their counts are equal
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
                    // Update max length if frequencies match
                    if (count1 == count2) {
                        maxLen = Math.max(maxLen, j - i + 1);
                    }
                }
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("==================================================");
        System.out.println("   Algorithm 1: Optimized Iterative Approach      ");
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