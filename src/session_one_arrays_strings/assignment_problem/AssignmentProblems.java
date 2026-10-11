public class AssignmentProblems {

    static void checkDuplicateSeats(int[] seatNumbers) {
        boolean found = false;
        for (int i = 0; i < seatNumbers.length; i++) {
            boolean reported = false;
            for (int j = 0; j < i; j++) {
                if (seatNumbers[j] == seatNumbers[i]) {
                    reported = true;
                }
            }
            for (int j = i + 1; j < seatNumbers.length && !reported; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
                    found = true;
                    break;
                }
            }
        }
        if (!found) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    static void checkTypingAccuracy(String original, String typed) {
        int matched = 0;
        int firstMismatch = -1;
        for (int i = 0; i < original.length(); i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatch == -1) {
                firstMismatch = i;
            }
        }
        System.out.printf("Matched: %d/%d | Accuracy: %.2f%% | ", matched, original.length(),
                matched * 100.0 / original.length());
        if (firstMismatch == -1) {
            System.out.println("No Mismatches");
        } else {
            System.out.println("First Mismatch at position " + (firstMismatch + 1)
                    + " ('" + original.charAt(firstMismatch) + "' vs '" + typed.charAt(firstMismatch) + "')");
        }
    }

    static void findLongestStreak(String signalLog) {
        char longestColor = signalLog.charAt(0);
        int longest = 1;
        int current = 1;
        for (int i = 1; i < signalLog.length(); i++) {
            current = signalLog.charAt(i) == signalLog.charAt(i - 1) ? current + 1 : 1;
            if (current > longest) {
                longest = current;
                longestColor = signalLog.charAt(i);
            }
        }
        System.out.println("Longest Streak: '" + longestColor + "' repeated " + longest + " times");
    }

    static void analyzeInventory(int[] sectionA, int[] sectionB) {
        int totalA = 0, totalB = 0, highest = 0, highestItem = 0;
        String highestSection = "";
        for (int i = 0; i < sectionA.length; i++) {
            totalA += sectionA[i];
            totalB += sectionB[i];
            if (sectionA[i] > highest) {
                highest = sectionA[i];
                highestSection = "Section A";
                highestItem = i + 1;
            }
            if (sectionB[i] > highest) {
                highest = sectionB[i];
                highestSection = "Section B";
                highestItem = i + 1;
            }
        }
        System.out.println("Section A Total: " + totalA + " | Section B Total: " + totalB
                + " | Status: " + (totalA == totalB ? "Balanced" : "Not Balanced")
                + " | Highest Quantity: " + highest + " (" + highestSection + ", Item " + highestItem + ")");
    }

    static void classifyWordLengths(String review) {
        int shortCount = 0, mediumCount = 0, longCount = 0;
        for (String word : review.split(" ")) {
            if (word.length() <= 4) {
                shortCount++;
            } else if (word.length() <= 8) {
                mediumCount++;
            } else {
                longCount++;
            }
        }
        System.out.println("Short: " + shortCount + " | Medium: " + mediumCount + " | Long: " + longCount);
    }

    public static void main(String[] args) {
        System.out.println("1. Exam Hall Seat Duplication Checker");
        checkDuplicateSeats(new int[]{101, 102, 103, 102, 105});
        checkDuplicateSeats(new int[]{101, 102, 103, 104, 105});

        System.out.println("\n2. Typing Speed Test Accuracy Checker");
        checkTypingAccuracy("hello world", "hello worlt");
        checkTypingAccuracy("coding", "coding");

        System.out.println("\n3. Traffic Signal Streak Analyzer");
        findLongestStreak("RRGGGYRR");
        findLongestStreak("RRRRYYGG");

        System.out.println("\n4. Warehouse Inventory Balancer");
        analyzeInventory(new int[]{20, 15, 30}, new int[]{25, 10, 30});

        System.out.println("\n5. Movie Review Word Length Profiler");
        classifyWordLengths("This movie was absolutely fantastic and thrilling");
    }
}
