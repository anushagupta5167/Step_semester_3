public class ClassProblems6 {

    static class PlacementRecord {
        String studentName;
        String company;
        double packageLpa;

        PlacementRecord(String studentName, String company, double packageLpa) {
            this.studentName = studentName;
            this.company = company;
            this.packageLpa = packageLpa;
        }

        void printRecord() {
            System.out.println(studentName + " -> " + company + " @ " + packageLpa + " LPA");
        }
    }

    static class MessWallet {
        private double balance;

        MessWallet(double openingBalance) {
            if (openingBalance < 0) {
                System.out.println("Warning: negative opening balance, starting at 0");
                balance = 0;
            } else {
                balance = openingBalance;
            }
        }

        void topUp(double amount) {
            if (amount <= 0) {
                System.out.println("Top-up rejected: amount must be positive");
                return;
            }
            balance += amount;
        }

        void deduct(double amount) {
            if (amount > balance) {
                System.out.println("Deduct rejected: insufficient balance");
                return;
            }
            balance -= amount;
        }

        double getBalance() {
            return balance;
        }
    }

    static class Course {
        String code;
        String title;
        int credits;
        int labCredits;

        public Course(String code, String title, int credits, int labCredits) {
            this.code = code;
            this.title = title;
            this.credits = credits;
            this.labCredits = labCredits;
        }

        public Course(String code, String title, int credits) {
            this(code, title, credits, 0);
        }

        int totalCredits() {
            return credits + labCredits;
        }
    }

    static class IdCard {
        String name;
        int booksIssued;

        IdCard(String name, int booksIssued) {
            this.name = name;
            this.booksIssued = booksIssued;
        }
    }

    static class Student {
        static String collegeName = "SRM Institute of Science and Technology";
        static int studentCount = 0;
        String name;
        int attendance;

        Student(String name, int attendance) {
            this.name = name;
            this.attendance = attendance;
            studentCount++;
        }

        static void printCollegeInfo() {
            System.out.println(collegeName);
            System.out.println("Students created: " + studentCount);
        }
    }

    public static void main(String[] args) {
        System.out.println("1. Student Placement Record Management");
        PlacementRecord[] records = {
                new PlacementRecord("Ravi", "TCS", 4.5),
                new PlacementRecord("Anitha", "Zoho", 6.2),
                new PlacementRecord("Karthik", "Infosys", 4.0)
        };
        for (PlacementRecord record : records) {
            record.printRecord();
        }

        System.out.println("\n2. Hostel Mess Wallet Management");
        MessWallet wallet = new MessWallet(500);
        wallet.topUp(200);
        System.out.println("Balance after top-up: " + wallet.getBalance());
        wallet.deduct(1000);
        System.out.println("Final balance: " + wallet.getBalance());

        System.out.println("\n3. Course Credit Management");
        Course theory = new Course("21CSC201J", "Data Structures", 4);
        Course lab = new Course("21CSC205L", "DSA Lab", 3, 1);
        System.out.println(theory.code + " total credits: " + theory.totalCredits());
        System.out.println(lab.code + " total credits: " + lab.totalCredits());

        System.out.println("\n4. Library ID Card Management");
        IdCard ravi = new IdCard("Ravi", 0);
        IdCard duplicate = ravi;
        duplicate.booksIssued = 3;
        IdCard separate = new IdCard("Ravi", 3);
        System.out.println("Ravi's booksIssued (via first variable): " + ravi.booksIssued);
        System.out.println("duplicate == ravi: " + (duplicate == ravi));
        System.out.println("separate == ravi: " + (separate == ravi));

        System.out.println("\n5. Student and College Information Management");
        new Student("Asha", 90);
        new Student("Ravi", 85);
        Student.printCollegeInfo();
    }
}
