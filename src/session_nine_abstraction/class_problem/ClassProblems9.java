import java.util.Scanner;

public class ClassProblems9 {

    static abstract class Plot {
        String owner;

        Plot(String owner) {
            this.owner = owner;
        }

        abstract double area();
    }

    static class Circle extends Plot {
        double radius;

        Circle(String owner, double radius) {
            super(owner);
            this.radius = radius;
        }

        double area() {
            return Math.PI * radius * radius;
        }
    }

    static class Rectangle extends Plot {
        double length;
        double width;

        Rectangle(String owner, double length, double width) {
            super(owner);
            this.length = length;
            this.width = width;
        }

        double area() {
            return length * width;
        }
    }

    static class Triangle extends Plot {
        double base;
        double height;

        Triangle(String owner, double base, double height) {
            super(owner);
            this.base = base;
            this.height = height;
        }

        double area() {
            return 0.5 * base * height;
        }
    }

    static Plot createPlot(String[] parts) {
        double first = Double.parseDouble(parts[2]);
        switch (parts[0]) {
            case "CIRCLE":
                return new Circle(parts[1], first);
            case "RECTANGLE":
                return new Rectangle(parts[1], first, Double.parseDouble(parts[3]));
            default:
                return new Triangle(parts[1], first, Double.parseDouble(parts[3]));
        }
    }

    static void gardenReport(Scanner in) {
        int n = Integer.parseInt(in.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            String[] parts = in.nextLine().trim().split("\\s+");
            Plot plot = createPlot(parts);
            System.out.printf("%s (%s): %.2f%n", plot.owner, parts[0], plot.area());
            total += plot.area();
        }
        System.out.printf("Total Area: %.2f%n", total);
    }

    static abstract class Staff {
        String name;

        Staff(String name) {
            this.name = name;
        }

        abstract double pay();
    }

    static class FullTime extends Staff {
        double weeklySalary;

        FullTime(String name, double weeklySalary) {
            super(name);
            this.weeklySalary = weeklySalary;
        }

        double pay() {
            return weeklySalary;
        }
    }

    static class Hourly extends Staff {
        double hours;
        double rate;

        Hourly(String name, double hours, double rate) {
            super(name);
            this.hours = hours;
            this.rate = rate;
        }

        double pay() {
            return hours <= 40 ? hours * rate : 40 * rate + (hours - 40) * rate * 1.5;
        }
    }

    static class Intern extends Staff {
        double stipend;

        Intern(String name, double stipend) {
            super(name);
            this.stipend = stipend;
        }

        double pay() {
            return stipend;
        }
    }

    static Staff createStaff(String[] parts) {
        switch (parts[0]) {
            case "FULLTIME":
                return new FullTime(parts[1], Double.parseDouble(parts[2]));
            case "HOURLY":
                return new Hourly(parts[1], Double.parseDouble(parts[2]), Double.parseDouble(parts[3]));
            default:
                return new Intern(parts[1], Double.parseDouble(parts[2]));
        }
    }

    static void weeklyPay(Scanner in) {
        int n = Integer.parseInt(in.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            Staff staff = createStaff(in.nextLine().trim().split("\\s+"));
            System.out.printf("%s: %.2f%n", staff.name, staff.pay());
            total += staff.pay();
        }
        System.out.printf("Total Payroll: %.2f%n", total);
    }

    static abstract class Item {
        String title;
        int daysLate;

        Item(String title, int daysLate) {
            this.title = title;
            this.daysLate = daysLate;
        }

        abstract double fine();
    }

    static class Book extends Item {
        Book(String title, int daysLate) {
            super(title, daysLate);
        }

        double fine() {
            return 2.0 * daysLate;
        }
    }

    static class Dvd extends Item {
        Dvd(String title, int daysLate) {
            super(title, daysLate);
        }

        double fine() {
            return Math.min(50, 5.0 * daysLate);
        }
    }

    static class Magazine extends Item {
        Magazine(String title, int daysLate) {
            super(title, daysLate);
        }

        double fine() {
            return 1.0 * daysLate;
        }
    }

    static Item createItem(String[] parts) {
        int daysLate = Integer.parseInt(parts[2]);
        switch (parts[0]) {
            case "BOOK":
                return new Book(parts[1], daysLate);
            case "DVD":
                return new Dvd(parts[1], daysLate);
            default:
                return new Magazine(parts[1], daysLate);
        }
    }

    static void lateFines(Scanner in) {
        int n = Integer.parseInt(in.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            Item item = createItem(in.nextLine().trim().split("\\s+"));
            System.out.printf("%s: %.2f%n", item.title, item.fine());
            total += item.fine();
        }
        System.out.printf("Total Fines: %.2f%n", total);
    }

    static abstract class Connection {
        int units;

        Connection(int units) {
            this.units = units;
        }

        abstract double bill();
    }

    static class Home extends Connection {
        Home(int units) {
            super(units);
        }

        double bill() {
            return 5.0 * Math.min(units, 100) + 7.0 * Math.max(0, units - 100);
        }
    }

    static class Shop extends Connection {
        Shop(int units) {
            super(units);
        }

        double bill() {
            return 8.0 * units + 100;
        }
    }

    static class Factory extends Connection {
        Factory(int units) {
            super(units);
        }

        double bill() {
            return Math.max(1000, 6.0 * units);
        }
    }

    static Connection createConnection(String type, int units) {
        switch (type) {
            case "HOME":
                return new Home(units);
            case "SHOP":
                return new Shop(units);
            default:
                return new Factory(units);
        }
    }

    static void electricityBills(Scanner in) {
        int n = Integer.parseInt(in.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            String[] parts = in.nextLine().trim().split("\\s+");
            double bill = createConnection(parts[0], Integer.parseInt(parts[1])).bill();
            System.out.printf("%s: %.2f%n", parts[0], bill);
            total += bill;
        }
        System.out.printf("Total: %.2f%n", total);
    }

    static abstract class Booking {
        static final double BOOKING_FEE = 50;
        double distanceKm;

        Booking(double distanceKm) {
            this.distanceKm = distanceKm;
        }

        abstract double fare();

        double total() {
            return fare() + BOOKING_FEE;
        }
    }

    static class Bus extends Booking {
        Bus(double distanceKm) {
            super(distanceKm);
        }

        double fare() {
            return 2 * distanceKm;
        }
    }

    static class Train extends Booking {
        Train(double distanceKm) {
            super(distanceKm);
        }

        double fare() {
            return 1.5 * distanceKm;
        }
    }

    static class Flight extends Booking {
        Flight(double distanceKm) {
            super(distanceKm);
        }

        double fare() {
            return 2500 + 4 * distanceKm;
        }
    }

    static Booking createBooking(String mode, double distanceKm) {
        switch (mode) {
            case "BUS":
                return new Bus(distanceKm);
            case "TRAIN":
                return new Train(distanceKm);
            default:
                return new Flight(distanceKm);
        }
    }

    static void travelBookings(Scanner in) {
        int n = Integer.parseInt(in.nextLine().trim());
        for (int i = 0; i < n; i++) {
            String[] parts = in.nextLine().trim().split("\\s+");
            System.out.printf("%s: %.2f%n", parts[0], createBooking(parts[0], Double.parseDouble(parts[1])).total());
        }
    }

    public static void main(String[] args) {
        System.out.println("1. Garden Plot Area Report");
        gardenReport(new Scanner("3\nCIRCLE Asha 5\nRECTANGLE Ravi 4 6\nTRIANGLE Neha 10 3\n"));

        System.out.println("\n2. Weekly Staff Pay");
        weeklyPay(new Scanner("3\nFULLTIME Asha 12000\nHOURLY Ravi 45 200\nINTERN Neha 5000\n"));

        System.out.println("\n3. Library Late Fine Counter");
        lateFines(new Scanner("3\nBOOK Algebra 4\nDVD Inception 12\nMAGAZINE Sports 3\n"));

        System.out.println("\n4. Electricity Connection Billing");
        electricityBills(new Scanner("3\nHOME 150\nSHOP 90\nFACTORY 120\n"));

        System.out.println("\n5. Travel Booking with a Common Fee");
        travelBookings(new Scanner("3\nBUS 200\nTRAIN 300\nFLIGHT 500\n"));
    }
}
