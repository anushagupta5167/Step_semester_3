import java.text.DecimalFormat;

public class AssignmentProblems6 {

    static class BookInventory {
        String title;
        String author;
        int copiesAvailable;

        BookInventory(String title, String author, int copiesAvailable) {
            this.title = title;
            this.author = author;
            this.copiesAvailable = copiesAvailable;
        }

        void printEntry() {
            System.out.println(title + " by " + author + " - " + copiesAvailable + " copies available");
        }
    }

    static class PayrollAccount {
        private double basicSalary;
        private double bonus;

        public PayrollAccount(double openingSalary) {
            if (openingSalary < 0) {
                System.out.println("Warning: negative salary, starting at 0");
                basicSalary = 0;
            } else {
                basicSalary = openingSalary;
            }
        }

        public void creditBonus(double amount) {
            if (amount <= 0) {
                System.out.println("Bonus rejected: amount must be positive");
                return;
            }
            bonus += amount;
            System.out.println("Bonus credited: Rs " + amount);
        }

        public void deductTax(double percent) {
            if (percent < 0 || percent > 100) {
                System.out.println("Tax rejected: percent must be between 0 and 100");
                return;
            }
            basicSalary -= basicSalary * percent / 100;
            System.out.println("Tax deducted: " + new DecimalFormat("0.##").format(percent) + "%");
        }

        public double getNetSalary() {
            return basicSalary + bonus;
        }
    }

    static class Employee {
        String empId;
        String empName;
        double salary;
        boolean isIntern;

        public Employee(String empId, String empName, double salary) {
            this.empId = empId;
            this.empName = empName;
            this.salary = salary;
            this.isIntern = false;
        }

        public Employee(String empId, String empName) {
            this(empId, empName, 0);
            this.isIntern = true;
        }

        void printProfile() {
            System.out.println(empId + " | " + empName + " | Rs " + salary + " | Intern: " + isIntern);
        }
    }

    static class HallTicket {
        String studentName;
        int seatNumber;

        HallTicket(String studentName, int seatNumber) {
            this.studentName = studentName;
            this.seatNumber = seatNumber;
        }
    }

    static class CompanyEmployee {
        static String companyName = "Bright Horizon Technologies";
        static int employeeCount = 0;
        String empName;
        double salary;

        CompanyEmployee(String empName, double salary) {
            this.empName = empName;
            this.salary = salary;
            employeeCount++;
        }

        static void printCompanyInfo() {
            System.out.println(companyName);
            System.out.println("Employees on record: " + employeeCount);
        }
    }

    public static void main(String[] args) {
        System.out.println("1. Library Inventory Management");
        BookInventory[] books = {
                new BookInventory("Clean Code", "Robert C. Martin", 3),
                new BookInventory("Effective Java", "Joshua Bloch", 5),
                new BookInventory("Refactoring", "Martin Fowler", 0),
                new BookInventory("Design Patterns", "GoF", 2)
        };
        for (BookInventory book : books) {
            book.printEntry();
        }

        System.out.println("\n2. Payroll Salary Management");
        PayrollAccount account = new PayrollAccount(50000);
        account.creditBonus(5000);
        account.deductTax(10);
        System.out.println("Net salary: Rs " + account.getNetSalary());

        System.out.println("\n3. Employee Profile Creation");
        new Employee("E-101", "Divya", 65000).printProfile();
        new Employee("E-102", "Arjun").printProfile();

        System.out.println("\n4. Exam Hall Ticket Reference Management");
        HallTicket priya = new HallTicket("Priya", 0);
        HallTicket copy = priya;
        copy.seatNumber = 45;
        HallTicket separate = new HallTicket("Priya", 45);
        System.out.println("Priya's seatNumber (via first variable): " + priya.seatNumber);
        System.out.println("copy == priya: " + (copy == priya));
        System.out.println("separate == priya: " + (separate == priya));

        System.out.println("\n5. Employee and Company Information Management");
        new CompanyEmployee("Asha", 50000);
        new CompanyEmployee("Ravi", 45000);
        new CompanyEmployee("Neha", 40000);
        CompanyEmployee.printCompanyInfo();
    }
}
