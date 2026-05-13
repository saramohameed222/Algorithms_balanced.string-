public class BalancedStringBacktracking {

    private static int maxLen;
    private static int maxStart;

    private static boolean isBalanced(int[] freq) {

        int distinctCount = 0;
        int firstFreq = -1;

        for (int f : freq) {
            if (f > 0) {
                distinctCount++;
                if (firstFreq == -1) {
                    firstFreq = f;
                } else if (f != firstFreq) {
                    return false;
                }
            }
        }

        return distinctCount == 2;
    }

    private static void backtrack(String s, int start, int end, int[] freq, int n) {

        if (n - start < 2) return;
        if (end > n) return;

        if (end - start >= 2) {
            if (isBalanced(freq)) {
                if (end - start > maxLen) {
                    maxLen = end - start;
                    maxStart = start;
                }
            }
        }

        if (end < n) {
            freq[s.charAt(end) - 'a']++;
            backtrack(s, start, end + 1, freq, n);
            freq[s.charAt(end) - 'a']--;
        }

        if (end == n) {
            int[] newFreq = new int[26];
            newFreq[s.charAt(start + 1) - 'a']++;
            backtrack(s, start + 1, start + 2, newFreq, n);
        }
    }

    public static String longestBalancedSubstring(String s) {

        if (s == null || s.length() < 2) return "";

        maxLen = 0;
        maxStart = -1;

        int[] initialFreq = new int[26];
        initialFreq[s.charAt(0) - 'a']++;
        initialFreq[s.charAt(1) - 'a']++;

        backtrack(s, 0, 2, initialFreq, s.length());

        if (maxLen == 0) return "";
        return s.substring(maxStart, maxStart + maxLen);
    }
}