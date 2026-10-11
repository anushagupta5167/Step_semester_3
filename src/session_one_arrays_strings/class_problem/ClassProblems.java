import java.util.Arrays;
import java.util.Random;

public class ClassProblems {

    static String playRound(String player, String computer) {
        if (player.equals(computer)) {
            return "Draw";
        }
        if (player.equals("Rock") && computer.equals("Scissors")
                || player.equals("Paper") && computer.equals("Rock")
                || player.equals("Scissors") && computer.equals("Paper")) {
            return "Player Wins";
        }
        return "Computer Wins";
    }

    static boolean isPalindromeIterative(String text) {
        for (int i = 0, j = text.length() - 1; i < j; i++, j--) {
            if (text.charAt(i) != text.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    static boolean isPalindromeRecursive(String text) {
        if (text.length() <= 1) {
            return true;
        }
        if (text.charAt(0) != text.charAt(text.length() - 1)) {
            return false;
        }
        return isPalindromeRecursive(text.substring(1, text.length() - 1));
    }

    static boolean isPalindromeArrayReversal(String text) {
        char[] original = text.toCharArray();
        char[] reversed = new char[original.length];
        for (int i = 0; i < original.length; i++) {
            reversed[i] = original[original.length - 1 - i];
        }
        return Arrays.equals(original, reversed);
    }

    static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        }
        if (bmi < 25) {
            return "Normal";
        }
        if (bmi < 30) {
            return "Overweight";
        }
        return "Obese";
    }

    static void printWellnessReport(double[] heights, double[] weights) {
        System.out.println("Person | Height (m) | Weight (kg) | BMI   | Status");
        for (int i = 0; i < heights.length; i++) {
            double bmi = weights[i] / (heights[i] * heights[i]);
            System.out.printf("%-6d | %-10.2f | %-11.1f | %-5.2f | %s%n",
                    i + 1, heights[i], weights[i], bmi, getBmiStatus(bmi));
        }
    }

    static char findFirstNonRepeatingChar(String text) {
        int[] frequency = new int[256];
        for (int i = 0; i < text.length(); i++) {
            frequency[text.charAt(i)]++;
        }
        for (int i = 0; i < text.length(); i++) {
            if (frequency[text.charAt(i)] == 1) {
                return text.charAt(i);
            }
        }
        return '\0';
    }

    static String reverseCustomerName(String customerName) {
        String reversed = "";
        for (int i = customerName.length() - 1; i >= 0; i--) {
            reversed += customerName.charAt(i);
        }
        return reversed;
    }

    public static void main(String[] args) {
        System.out.println("1. Rock-Paper-Scissors Game");
        String[] moves = {"Rock", "Paper", "Scissors"};
        String[] playerMoves = {"Rock", "Paper", "Scissors", "Rock", "Paper"};
        Random random = new Random();
        int wins = 0, losses = 0, draws = 0;
        System.out.println("Round | Player Move | Computer Move | Result");
        for (int i = 0; i < playerMoves.length; i++) {
            String computerMove = moves[random.nextInt(3)];
            String result = playRound(playerMoves[i], computerMove);
            if (result.equals("Player Wins")) {
                wins++;
            } else if (result.equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }
            System.out.printf("%-5d | %-11s | %-13s | %s%n", i + 1, playerMoves[i], computerMove, result);
        }
        System.out.printf("Wins: %d | Losses: %d | Draws: %d | Win %% = %.1f%%%n",
                wins, losses, draws, wins * 100.0 / playerMoves.length);

        System.out.println("\n2. Palindrome Checker");
        for (String text : new String[]{"madam", "hello"}) {
            System.out.println("\"" + text + "\"");
            System.out.println("Iterative: " + (isPalindromeIterative(text) ? "Palindrome" : "Not Palindrome")
                    + " | Recursive: " + (isPalindromeRecursive(text) ? "Palindrome" : "Not Palindrome")
                    + " | Array Reversal: " + (isPalindromeArrayReversal(text) ? "Palindrome" : "Not Palindrome"));
        }

        System.out.println("\n3. BMI Calculator");
        double[] heights = {1.75, 1.60, 1.82, 1.55, 1.68, 1.90, 1.58, 1.72, 1.65, 1.80};
        double[] weights = {70, 90, 65, 45, 80, 110, 50, 75, 60, 95};
        printWellnessReport(heights, weights);

        System.out.println("\n4. First Non-Repeating Character");
        for (String text : new String[]{"swiss", "aabbcc"}) {
            char result = findFirstNonRepeatingChar(text);
            if (result == '\0') {
                System.out.println("No Non-Repeating Character Found");
            } else {
                System.out.println("First Non-Repeating Character: '" + result + "'");
            }
        }

        System.out.println("\n5. Reverse Customer Name");
        String customerName = "Sunil";
        System.out.println("Original Name: " + customerName);
        System.out.println("Reversed Name: " + reverseCustomerName(customerName));
    }
}
