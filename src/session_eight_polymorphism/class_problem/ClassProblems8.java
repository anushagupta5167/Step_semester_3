import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassProblems8 {

    static abstract class Payment {
        double amount;

        Payment(double amount) {
            this.amount = amount;
        }

        abstract double finalAmount();
    }

    static class Card extends Payment {
        Card(double amount) {
            super(amount);
        }

        double finalAmount() {
            return amount * 1.02;
        }
    }

    static class Wallet extends Payment {
        Wallet(double amount) {
            super(amount);
        }

        double finalAmount() {
            return amount * 1.01;
        }
    }

    static class BankTransfer extends Payment {
        BankTransfer(double amount) {
            super(amount);
        }

        double finalAmount() {
            return amount;
        }
    }

    static Payment createPayment(String type, double amount) {
        switch (type) {
            case "CARD":
                return new Card(amount);
            case "WALLET":
                return new Wallet(amount);
            default:
                return new BankTransfer(amount);
        }
    }

    static void paymentFees(Scanner in) {
        int n = Integer.parseInt(in.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            String[] parts = in.nextLine().trim().split("\\s+");
            double amount = createPayment(parts[0], Double.parseDouble(parts[1])).finalAmount();
            System.out.printf("%s: %.2f%n", parts[0], amount);
            total += amount;
        }
        System.out.printf("Total: %.2f%n", total);
    }

    static abstract class LibraryItem {
        String title;

        LibraryItem(String title) {
            this.title = title;
        }

        abstract int borrowDays();

        LocalDate dueDate() {
            return LocalDate.of(2023, 10, 26).plusDays(borrowDays());
        }
    }

    static class Book extends LibraryItem {
        Book(String title) {
            super(title);
        }

        int borrowDays() {
            return 14;
        }
    }

    static class Dvd extends LibraryItem {
        Dvd(String title) {
            super(title);
        }

        int borrowDays() {
            return 7;
        }
    }

    static class Magazine extends LibraryItem {
        Magazine(String title) {
            super(title);
        }

        int borrowDays() {
            return 3;
        }
    }

    static LibraryItem createItem(String type, String title) {
        switch (type) {
            case "BOOK":
                return new Book(title);
            case "DVD":
                return new Dvd(title);
            default:
                return new Magazine(title);
        }
    }

    static void dueDates(Scanner in) {
        int n = Integer.parseInt(in.nextLine().trim());
        for (int i = 0; i < n; i++) {
            String line = in.nextLine().trim();
            int space = line.indexOf(' ');
            LibraryItem item = createItem(line.substring(0, space), line.substring(space + 1).replace("\"", ""));
            System.out.println(item.title + ": " + item.dueDate());
        }
    }

    static abstract class Delivery {
        double weight;
        double distance;

        Delivery(double weight, double distance) {
            this.weight = weight;
            this.distance = distance;
        }

        abstract double fee();
    }

    static class StandardDelivery extends Delivery {
        StandardDelivery(double weight, double distance) {
            super(weight, distance);
        }

        double fee() {
            return 5 + 0.50 * weight + 0.10 * distance;
        }
    }

    static class ExpressDelivery extends Delivery {
        ExpressDelivery(double weight, double distance) {
            super(weight, distance);
        }

        double fee() {
            return 15 + 1.00 * weight + 0.20 * distance;
        }
    }

    static class InternationalDelivery extends Delivery {
        double customsFee;

        InternationalDelivery(double weight, double distance, double customsFee) {
            super(weight, distance);
            this.customsFee = customsFee;
        }

        double fee() {
            return 25 + 2.00 * weight + 0.50 * distance + customsFee;
        }
    }

    static Delivery createDelivery(String[] parts) {
        double weight = Double.parseDouble(parts[1]);
        double distance = Double.parseDouble(parts[2]);
        switch (parts[0]) {
            case "STANDARD":
                return new StandardDelivery(weight, distance);
            case "EXPRESS":
                return new ExpressDelivery(weight, distance);
            default:
                return new InternationalDelivery(weight, distance, Double.parseDouble(parts[3]));
        }
    }

    static void deliveryFees(Scanner in) {
        int n = Integer.parseInt(in.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            String[] parts = in.nextLine().trim().split("\\s+");
            double fee = createDelivery(parts).fee();
            System.out.printf("%s: %.2f%n", parts[0], fee);
            total += fee;
        }
        System.out.printf("Total: %.2f%n", total);
    }

    static abstract class Question {
        String correct;
        String student;
        int points;

        Question(String correct, String student, int points) {
            this.correct = correct;
            this.student = student;
            this.points = points;
        }

        abstract double score();
    }

    static class ExactMatchQuestion extends Question {
        ExactMatchQuestion(String correct, String student, int points) {
            super(correct, student, points);
        }

        double score() {
            return student.equals(correct) ? points : 0;
        }
    }

    static class Essay extends Question {
        Essay(String correct, String student, int points) {
            super(correct, student, points);
        }

        double score() {
            int hits = 0;
            for (String keyword : correct.split(",")) {
                if (student.toLowerCase().contains(keyword.trim().toLowerCase())) {
                    hits++;
                }
            }
            if (hits >= 2) {
                return points * 0.75;
            }
            return hits == 1 ? points * 0.50 : 0;
        }
    }

    static void grade(Scanner in) {
        int n = Integer.parseInt(in.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            String line = in.nextLine().trim();
            String type = line.substring(0, line.indexOf(' '));
            int points = Integer.parseInt(line.substring(line.lastIndexOf(' ') + 1));
            List<String> quoted = new ArrayList<>();
            Matcher matcher = Pattern.compile("\"([^\"]*)\"").matcher(line);
            while (matcher.find()) {
                quoted.add(matcher.group(1));
            }
            Question question = type.equals("ESSAY")
                    ? new Essay(quoted.get(1), quoted.get(2), points)
                    : new ExactMatchQuestion(quoted.get(1), quoted.get(2), points);
            System.out.printf("%s: %.2f%n", type, question.score());
            total += question.score();
        }
        System.out.printf("Total Score: %.2f%n", total);
    }

    static abstract class Journey {
        double distance;

        Journey(double distance) {
            this.distance = distance;
        }

        abstract double fare();
    }

    static class Bus extends Journey {
        Bus(double distance) {
            super(distance);
        }

        double fare() {
            return Math.min(10, 2 + 0.10 * distance);
        }
    }

    static class Train extends Journey {
        Train(double distance) {
            super(distance);
        }

        double fare() {
            return 3 + 0.15 * distance;
        }
    }

    static class Metro extends Journey {
        double peakHourFactor;

        Metro(double distance, double peakHourFactor) {
            super(distance);
            this.peakHourFactor = peakHourFactor;
        }

        double fare() {
            return (1.50 + 0.20 * distance) * peakHourFactor;
        }
    }

    static Journey createJourney(String[] parts) {
        double distance = Double.parseDouble(parts[1]);
        switch (parts[0]) {
            case "BUS":
                return new Bus(distance);
            case "TRAIN":
                return new Train(distance);
            default:
                return new Metro(distance, Double.parseDouble(parts[2]));
        }
    }

    static void transportFares(Scanner in) {
        int n = Integer.parseInt(in.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            String[] parts = in.nextLine().trim().split("\\s+");
            double fare = createJourney(parts).fare();
            System.out.printf("%s: %.2f%n", parts[0], fare);
            total += fare;
        }
        System.out.printf("Total: %.2f%n", total);
    }

    public static void main(String[] args) {
        System.out.println("1. Payment System Fee Calculation");
        paymentFees(new Scanner("3\nCARD 1000\nWALLET 500\nBANKTRANSFER 2000\n"));

        System.out.println("\n2. Library Item Due Date Calculator");
        dueDates(new Scanner("3\nBOOK \"1984\"\nDVD \"The Matrix\"\nMAGAZINE \"Forbes Issue 500\"\n"));

        System.out.println("\n3. Delivery Fee Calculator");
        deliveryFees(new Scanner("3\nSTANDARD 10 50\nEXPRESS 5 20\nINTERNATIONAL 20 100 30\n"));

        System.out.println("\n4. Examination Question Grader");
        grade(new Scanner("4\n"
                + "MCQ \"What is the capital of France?\" \"Paris\" \"Paris\" 10\n"
                + "TF \"The Earth is flat?\" \"False\" \"True\" 5\n"
                + "ESSAY \"Name two primary OOP principles.\" \"Inheritance, Polymorphism, Encapsulation\" \"Polymorphism is one.\" 20\n"
                + "ESSAY \"Describe abstraction and composition.\" \"Abstraction, Composition\" \"I talked about abstraction.\" 15\n"));

        System.out.println("\n5. Public Transport Fare Calculator");
        transportFares(new Scanner("3\nBUS 15\nTRAIN 50\nMETRO 10 1.5\n"));
    }
}
