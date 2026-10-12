public class ClassProblems7 {

    static class PiggyBank {
        private final String id;
        private int savings;

        PiggyBank(String id) {
            this.id = id;
        }

        void deposit(int amount) {
            savings += amount;
        }

        void withdraw(int amount) {
            if (amount > savings) {
                System.out.println("Withdraw rejected: insufficient savings");
                return;
            }
            savings -= amount;
        }

        int getSavings() {
            return savings;
        }
    }

    static class Scorecard {
        private final boolean[] results;
        private int recorded;

        Scorecard(int questions) {
            results = new boolean[questions];
        }

        void recordAnswer(boolean correct) {
            if (recorded < results.length) {
                results[recorded++] = correct;
            }
        }

        int getScore() {
            int score = 0;
            for (int i = 0; i < recorded; i++) {
                if (results[i]) {
                    score++;
                }
            }
            return score;
        }
    }

    static final class NameTag {
        private final String firstName;
        private final String lastName;

        NameTag(String fullName) {
            String[] parts = fullName.split(" ");
            firstName = parts[0];
            lastName = parts[1];
        }

        String getNickname() {
            return firstName + " " + lastName.charAt(0) + ".";
        }
    }

    static class Locker {
        private final int lockerNumber;
        private String code;

        Locker(int lockerNumber, String code) {
            this.lockerNumber = lockerNumber;
            this.code = code;
        }

        boolean changeCode(String currentCode, String newCode) {
            if (!code.equals(currentCode)) {
                return false;
            }
            code = newCode;
            return true;
        }
    }

    static class AttendanceSheet {
        private final String[] present;
        private int count;

        AttendanceSheet(int maxSize) {
            present = new String[maxSize];
        }

        void markPresent(String name) {
            if (!isPresent(name) && count < present.length) {
                present[count++] = name;
            }
        }

        int getPresentCount() {
            return count;
        }

        boolean isPresent(String name) {
            for (int i = 0; i < count; i++) {
                if (present[i].equals(name)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println("1. The Piggy Bank");
        PiggyBank piggyBank = new PiggyBank("PB-1");
        piggyBank.deposit(100);
        System.out.println("savings = " + piggyBank.getSavings());
        piggyBank.withdraw(30);
        System.out.println("savings = " + piggyBank.getSavings());
        piggyBank.withdraw(500);
        System.out.println("savings = " + piggyBank.getSavings());

        System.out.println("\n2. The Quiz Scorecard");
        Scorecard scorecard = new Scorecard(4);
        scorecard.recordAnswer(true);
        scorecard.recordAnswer(true);
        scorecard.recordAnswer(false);
        scorecard.recordAnswer(true);
        System.out.println("Score: " + scorecard.getScore());

        System.out.println("\n3. The Nickname Tag");
        System.out.println(new NameTag("Maria Gomez").getNickname());

        System.out.println("\n4. The Locker Code");
        Locker locker = new Locker(101, "1234");
        System.out.println(locker.changeCode("1234", "5678") ? "success" : "rejected");
        System.out.println(locker.changeCode("0000", "9999") ? "success" : "rejected");

        System.out.println("\n5. The Attendance Sheet");
        AttendanceSheet sheet = new AttendanceSheet(30);
        sheet.markPresent("Ana");
        sheet.markPresent("Ben");
        sheet.markPresent("Ana");
        System.out.println("Present count: " + sheet.getPresentCount());
        System.out.println("Ben present: " + sheet.isPresent("Ben"));
        System.out.println("Chen present: " + sheet.isPresent("Chen"));
    }
}
