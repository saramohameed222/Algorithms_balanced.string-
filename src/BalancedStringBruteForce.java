import java.util.Scanner;

public class BalancedStringBruteForce {

    public static boolean isBalanced(String s) {
        int[] freq = new int[26];

        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }

        int distinctCount = 0;
        int firstFreq = -1;

        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                distinctCount++;

                if (firstFreq == -1) {
                    firstFreq = freq[i];
                } else if (freq[i] != firstFreq) {
                    return false;
                }
            }
        }

        return distinctCount == 2;
    }

    public static int longestBalancedSubstring(String s) {
        int n = s.length();
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 2; j <= n; j++) {

                String sub = s.substring(i, j);

                if (isBalanced(sub)) {
                    maxLen = Math.max(maxLen, sub.length());
                }
            }
        }

        return maxLen;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("=== Algorithm 1: Brute Force (Non-Recursive) ===");
        System.out.println();

        System.out.print("Enter the string: ");
        String s = input.nextLine();

        int result = longestBalancedSubstring(s);

        System.out.println();
        System.out.println("Longest Balanced Substring Length = " + result);

        input.close();
    }
}