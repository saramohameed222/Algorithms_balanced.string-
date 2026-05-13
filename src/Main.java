import java.util.Scanner;

public class Main {

    /**
     * Executes both algorithms on a given string and prints a clean comparative report.
     */
    public static void runComparisonTest(String testName, String s) {
        System.out.printf(" Test Case: %s%n", testName);
        System.out.printf(" Input String: \"%s\"%n", s);

        // Run Algorithm 1: Iterative Optimized
        long start1 = System.nanoTime();
        int res1 = BalancedStringBruteForce.longestBalancedSubstring(s);
        long end1 = System.nanoTime();
        double time1 = (end1 - start1) / 1_000_000.0;

        // Run Algorithm 2: Recursive Optimized
        long start2 = System.nanoTime();
        int res2 = BalancedStringBacktracking.longestBalancedSubstring(s);
        long end2 = System.nanoTime();
        double time2 = (end2 - start2) / 1_000_000.0;

        // Display results cleanly without "Expected" or "Correct" labels
        System.out.printf(" [Algo 1 - Iterative] Result Length = %d | Execution Time: %8.4f ms%n", res1, time1);
        System.out.printf(" [Algo 2 - Recursive] Result Length = %d | Execution Time: %8.4f ms%n", res2, time2);
        System.out.println("----------------------------------------------------------------------\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("======================================================================");
        System.out.println("          BALANCED STRING ALGORITHMS - COMPARISON & DEMO              ");
        System.out.println("======================================================================");
        System.out.println(" Project Idea: Find the length of the longest balanced substring.");
        System.out.println(" Balanced Definition: Exactly TWO distinct characters, equal frequencies.");
        System.out.println("======================================================================\n");

        // 1. Run Predefined Guideline Examples
        System.out.println(">>> PART 1: Guideline Examples Verification <<<\n");
        System.out.println("----------------------------------------------------------------------");
        runComparisonTest("Example 1", "cabbacc");
        runComparisonTest("Example 2", "abababa");
        runComparisonTest("Example 3", "aaaaaaa");

        // Additional Custom Test Cases to ensure robustness
        runComparisonTest("Custom 1 (Fully Balanced)", "aabb");
        runComparisonTest("Custom 2 (Three distinct letters)", "aabbcc");
        runComparisonTest("Custom 3 (Mixed long substring)", "xyaabbbbaayz");

        // 2. Interactive User Input Demo
        System.out.println(">>> PART 2: Interactive Testing <<<\n");
        while (true) {
            System.out.print("Enter a custom string to test (or type 'exit' to quit): ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            if (input.isEmpty()) {
                System.out.println("Please enter a valid non-empty string.\n");
                continue;
            }

            // Ensure input consists strictly of alphabetical letters to avoid out-of-bounds indexing crashes
            if (!input.matches("[a-zA-Z]+")) {
                System.out.println("Invalid input! Please enter a string containing only letters (a-z).\n");
                continue;
            }

            // Safely convert to lowercase to match 'a'-'z' array frequency mapping logic
            input = input.toLowerCase();

            // Run both algorithms
            long start1 = System.nanoTime();
            int r1 = BalancedStringBruteForce.longestBalancedSubstring(input);
            long end1 = System.nanoTime();

            long start2 = System.nanoTime();
            int r2 = BalancedStringBacktracking.longestBalancedSubstring(input);
            long end2 = System.nanoTime();

            System.out.println("\n--- Interactive Results ---");
            System.out.printf(" [Algo 1 - Iterative] Length: %d | Time: %.4f ms%n", r1, (end1 - start1) / 1_000_000.0);
            System.out.printf(" [Algo 2 - Recursive] Length: %d | Time: %.4f ms%n", r2, (end2 - start2) / 1_000_000.0);
            System.out.println("---------------------------\n");
        }

        System.out.println("Thank you! Project validation successfully finished.");
        scanner.close();
    }
}