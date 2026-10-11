import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AssignmentProblems2 {

    static void checkPinLength(String pin) {
        if (pin.length() != 4) {
            System.out.println("Invalid PIN — must be exactly 4 digits.");
        } else {
            System.out.println("PIN length OK.");
        }
    }

    static String reverseEachWord(String sentence) {
        StringBuilder result = new StringBuilder();
        for (String word : sentence.split(" ")) {
            StringBuilder reversed = new StringBuilder();
            for (int i = word.length() - 1; i >= 0; i--) {
                reversed.append(word.charAt(i));
            }
            result.append(reversed).append(" ");
        }
        return result.toString().trim();
    }

    static void parseInventoryRecord(String csvLine) {
        String[] fields = csvLine.split(",");
        if (fields.length != 3) {
            System.out.println("Invalid Record");
            return;
        }
        System.out.println("Product: " + fields[0] + " | SKU: " + fields[1] + " | Qty: " + fields[2]);
    }

    static String normalizeCode(String raw) {
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed;
        }
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }

    static String validateAndFormat(String code) {
        if (code.length() != 13) {
            return "Invalid: wrong length";
        }
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: body must be digits";
            }
        }
        StringBuilder result = new StringBuilder();
        result.append("[").append(code.substring(0, 3)).append("] YEAR: ")
                .append(code.substring(3, 7)).append(" | CATALOG: ")
                .append(code.substring(7));
        return result.toString();
    }

    static void printFilteredWordFrequency(String feedback) {
        List<String> stopWords = Arrays.asList("the", "was", "and", "a", "is", "of", "in");
        String cleaned = feedback.toLowerCase().replace(".", "").replace(",", "");
        HashMap<String, Integer> frequency = new HashMap<>();
        for (String word : cleaned.split("\\s+")) {
            if (!stopWords.contains(word)) {
                frequency.put(word, frequency.getOrDefault(word, 0) + 1);
            }
        }
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(frequency.entrySet());
        entries.sort((a, b) -> b.getValue() - a.getValue());
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        System.out.println("1. ATM PIN Length Validator");
        checkPinLength("482");
        checkPinLength("4820");

        System.out.println("\n2. Word Reversal Encoder");
        System.out.println(reverseEachWord("hello club"));

        System.out.println("\n3. Product Inventory CSV Parser");
        parseInventoryRecord("Wireless Mouse,WM-2201,150");
        parseInventoryRecord("Wireless Mouse,150");

        System.out.println("\n4. Library ISBN Normalizer & Validator");
        System.out.println(validateAndFormat(normalizeCode(" pen2026004251 ")));
        System.out.println(validateAndFormat(normalizeCode("12N2026004251")));

        System.out.println("\n5. Stop-Word-Filtered Word Frequency Report");
        printFilteredWordFrequency("The mentor was great, the session was great and clear.");
    }
}
