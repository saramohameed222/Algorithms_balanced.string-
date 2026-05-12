import java.util.Scanner;

public class BalancedStringBacktracking {

    private static int maxLen;

    private static int[] getFreq(String s, int start, int end) {

        int[] freq = new int[26];

        for (int i = start; i < end; i++) {
            freq[s.charAt(i) - 'a']++;
        }

        return freq;
    }

    private static boolean isBalanced(int[] freq) {

        int distinctCount = 0;
        int firstFreq = -1;

        for (int f : freq) {

            if (f > 0) {

                distinctCount++;

                if (firstFreq == -1) {
                    firstFreq = f;
                }
                else if (f != firstFreq) {
                    return false;
                }
            }
        }

        return distinctCount == 2;
    }

    private static void backtrack(String s, int start, int end, int n) {

        if (n - start < 2)
            return;

        if (end > n) {
            backtrack(s, start + 1, start + 2, n);
            return;
        }

        if (end - start >= 2) {

            int[] freq = getFreq(s, start, end);

            if (isBalanced(freq)) {
                maxLen = Math.max(maxLen, end - start);
            }
        }

        backtrack(s, start, end + 1, n);

        if (end == n) {
            backtrack(s, start + 1, start + 2, n);
        }
    }

    public static int longestBalancedSubstring(String s) {

        if (s == null || s.length() < 2)
            return 0;

        maxLen = 0;

        backtrack(s, 0, 2, s.length());

        return maxLen;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("Algorithm 2: Recursive Backtracking");
        System.out.println();

        System.out.print("Enter the string: ");
        String s = input.nextLine();

        int result = longestBalancedSubstring(s);

        System.out.println();
        System.out.println("Longest Balanced Substring Length = " + result);

        input.close();
    }
}