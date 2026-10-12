import java.time.LocalDate;
import java.util.Scanner;

public class AssignmentProblems8 {

    static abstract class Customer {
        double amount;

        Customer(double amount) {
            this.amount = amount;
        }

        abstract double finalAmount();
    }

    static class Student extends Customer {
        Student(double amount) {
            super(amount);
        }

        double finalAmount() {
            return amount * 0.90;
        }
    }

    static class Staff extends Customer {
        Staff(double amount) {
            super(amount);
        }

        double finalAmount() {
            return amount * 0.95;
        }
    }

    static class Guest extends Customer {
        Guest(double amount) {
            super(amount);
        }

        double finalAmount() {
            return amount + 10;
        }
    }

    static Customer createCustomer(String type, double amount) {
        switch (type) {
            case "STUDENT":
                return new Student(amount);
            case "STAFF":
                return new Staff(amount);
            default:
                return new Guest(amount);
        }
    }

    static void canteenBilling(Scanner in) {
        int n = Integer.parseInt(in.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            String[] parts = in.nextLine().trim().split("\\s+");
            double amount = createCustomer(parts[0], Double.parseDouble(parts[1])).finalAmount();
            System.out.printf("%s: %.2f%n", parts[0], amount);
            total += amount;
        }
        System.out.printf("Total: %.2f%n", total);
    }

    static abstract class Vehicle {
        int hours;

        Vehicle(int hours) {
            this.hours = hours;
        }

        abstract double charge();
    }

    static class Bike extends Vehicle {
        Bike(int hours) {
            super(hours);
        }

        double charge() {
            return 10 * hours;
        }
    }

    static class Car extends Vehicle {
        Car(int hours) {
            super(hours);
        }

        double charge() {
            return 30 + 20 * (hours - 1);
        }
    }

    static class Truck extends Vehicle {
        Truck(int hours) {
            super(hours);
        }

        double charge() {
            return Math.max(100, 50 * hours);
        }
    }

    static Vehicle createVehicle(String type, int hours) {
        switch (type) {
            case "BIKE":
                return new Bike(hours);
            case "CAR":
                return new Car(hours);
            default:
                return new Truck(hours);
        }
    }

    static void parkingCharges(Scanner in) {
        int n = Integer.parseInt(in.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            String[] parts = in.nextLine().trim().split("\\s+");
            double charge = createVehicle(parts[0], Integer.parseInt(parts[1])).charge();
            System.out.printf("%s: %.2f%n", parts[0], charge);
            total += charge;
        }
        System.out.printf("Total: %.2f%n", total);
    }

    static abstract class Room {
        int units;

        Room(int units) {
            this.units = units;
        }

        abstract double bill();
    }

    static class SingleRoom extends Room {
        SingleRoom(int units) {
            super(units);
        }

        double bill() {
            return 8.0 * units;
        }
    }

    static class SharedRoom extends Room {
        int occupants;

        SharedRoom(int units, int occupants) {
            super(units);
            this.occupants = occupants;
        }

        double bill() {
            return 6.0 * units / occupants;
        }
    }

    static class AcRoom extends Room {
        AcRoom(int units) {
            super(units);
        }

        double bill() {
            return 10.0 * units + 200;
        }
    }

    static Room createRoom(String[] parts) {
        int units = Integer.parseInt(parts[1]);
        switch (parts[0]) {
            case "SINGLE":
                return new SingleRoom(units);
            case "SHARED":
                return new SharedRoom(units, Integer.parseInt(parts[2]));
            default:
                return new AcRoom(units);
        }
    }

    static void hostelBills(Scanner in) {
        int n = Integer.parseInt(in.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            String[] parts = in.nextLine().trim().split("\\s+");
            double bill = createRoom(parts).bill();
            System.out.printf("%s: %.2f%n", parts[0], bill);
            total += bill;
        }
        System.out.printf("Total: %.2f%n", total);
    }

    static abstract class Employee {
        String name;
        double salary;

        Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }

        abstract double bonus();
    }

    static class FullTime extends Employee {
        FullTime(String name, double salary) {
            super(name, salary);
        }

        double bonus() {
            return salary * 0.10;
        }
    }

    static class PartTime extends Employee {
        PartTime(String name, double salary) {
            super(name, salary);
        }

        double bonus() {
            return salary * 0.05;
        }
    }

    static class Intern extends Employee {
        Intern(String name, double salary) {
            super(name, salary);
        }

        double bonus() {
            return 2000;
        }
    }

    static Employee createEmployee(String type, String name, double salary) {
        switch (type) {
            case "FULLTIME":
                return new FullTime(name, salary);
            case "PARTTIME":
                return new PartTime(name, salary);
            default:
                return new Intern(name, salary);
        }
    }

    static void festivalBonus(Scanner in) {
        int n = Integer.parseInt(in.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            String[] parts = in.nextLine().trim().split("\\s+");
            Employee employee = createEmployee(parts[0], parts[1], Double.parseDouble(parts[2]));
            System.out.printf("%s: %.2f%n", employee.name, employee.bonus());
            total += employee.bonus();
        }
        System.out.printf("Total Bonus: %.2f%n", total);
    }

    static abstract class Plan {
        String name;
        LocalDate startDate;

        Plan(String name, LocalDate startDate) {
            this.name = name;
            this.startDate = startDate;
        }

        abstract int validityDays();

        LocalDate renewalDate() {
            return startDate.plusDays(validityDays());
        }
    }

    static class Basic extends Plan {
        Basic(String name, LocalDate startDate) {
            super(name, startDate);
        }

        int validityDays() {
            return 30;
        }
    }

    static class StandardPlan extends Plan {
        StandardPlan(String name, LocalDate startDate) {
            super(name, startDate);
        }

        int validityDays() {
            return 90;
        }
    }

    static class Premium extends Plan {
        Premium(String name, LocalDate startDate) {
            super(name, startDate);
        }

        int validityDays() {
            return 365;
        }
    }

    static Plan createPlan(String type, String name, LocalDate startDate) {
        switch (type) {
            case "BASIC":
                return new Basic(name, startDate);
            case "STANDARD":
                return new StandardPlan(name, startDate);
            default:
                return new Premium(name, startDate);
        }
    }

    static void renewalReminders(Scanner in) {
        int n = Integer.parseInt(in.nextLine().trim());
        for (int i = 0; i < n; i++) {
            String[] parts = in.nextLine().trim().split("\\s+");
            Plan plan = createPlan(parts[0], parts[1], LocalDate.parse(parts[2]));
            System.out.println(plan.name + ": " + plan.renewalDate());
        }
    }

    public static void main(String[] args) {
        System.out.println("1. The Canteen Billing Counter");
        canteenBilling(new Scanner("3\nSTUDENT 200\nSTAFF 300\nGUEST 150\n"));

        System.out.println("\n2. The Campus Parking Charge Calculator");
        parkingCharges(new Scanner("4\nBIKE 3\nCAR 4\nTRUCK 1\nCAR 1\n"));

        System.out.println("\n3. The Hostel Electricity Bill");
        hostelBills(new Scanner("3\nSINGLE 120\nSHARED 150 3\nAC 100\n"));

        System.out.println("\n4. The Festival Bonus Calculator");
        festivalBonus(new Scanner("3\nFULLTIME Asha 50000\nPARTTIME Ravi 30000\nINTERN Neha 15000\n"));

        System.out.println("\n5. The Streaming Plan Renewal Reminder");
        renewalReminders(new Scanner("4\nBASIC Asha 2024-01-15\nSTANDARD Ravi 2024-02-01\nPREMIUM Neha 2024-03-10\nBASIC Kiran 2024-12-20\n"));
    }
}
