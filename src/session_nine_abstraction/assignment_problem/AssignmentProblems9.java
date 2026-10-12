import java.util.Scanner;

public class AssignmentProblems9 {

    static abstract class Ticket {
        static final double CONVENIENCE_FEE = 20;
        int count;

        Ticket(int count) {
            this.count = count;
        }

        abstract double price();

        double amount() {
            return count * (price() + CONVENIENCE_FEE);
        }
    }

    static class Regular extends Ticket {
        Regular(int count) {
            super(count);
        }

        double price() {
            return 150;
        }
    }

    static class Premium extends Ticket {
        Premium(int count) {
            super(count);
        }

        double price() {
            return 250;
        }
    }

    static class Recliner extends Ticket {
        Recliner(int count) {
            super(count);
        }

        double price() {
            return 400;
        }
    }

    static Ticket createTicket(String seat, int count) {
        switch (seat) {
            case "REGULAR":
                return new Regular(count);
            case "PREMIUM":
                return new Premium(count);
            default:
                return new Recliner(count);
        }
    }

    static void movieTickets(Scanner in) {
        int n = Integer.parseInt(in.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            String[] parts = in.nextLine().trim().split("\\s+");
            double amount = createTicket(parts[0], Integer.parseInt(parts[1])).amount();
            System.out.printf("%s: %.2f%n", parts[0], amount);
            total += amount;
        }
        System.out.printf("Total: %.2f%n", total);
    }

    interface Insurable {
        double INSURANCE_RATE = 0.02;

        double insurance();
    }

    static abstract class Parcel {
        double weightKg;
        double declaredValue;

        Parcel(double weightKg, double declaredValue) {
            this.weightKg = weightKg;
            this.declaredValue = declaredValue;
        }

        abstract double charge();
    }

    static class StandardParcel extends Parcel {
        StandardParcel(double weightKg, double declaredValue) {
            super(weightKg, declaredValue);
        }

        double charge() {
            return 40 + 10 * weightKg;
        }
    }

    static class ExpressParcel extends Parcel implements Insurable {
        ExpressParcel(double weightKg, double declaredValue) {
            super(weightKg, declaredValue);
        }

        double charge() {
            return 80 + 15 * weightKg;
        }

        public double insurance() {
            return INSURANCE_RATE * declaredValue;
        }
    }

    static class FragileParcel extends StandardParcel implements Insurable {
        FragileParcel(double weightKg, double declaredValue) {
            super(weightKg, declaredValue);
        }

        double charge() {
            return super.charge() + 50;
        }

        public double insurance() {
            return INSURANCE_RATE * declaredValue;
        }
    }

    static Parcel createParcel(String[] parts) {
        double weight = Double.parseDouble(parts[1]);
        double value = Double.parseDouble(parts[2]);
        switch (parts[0]) {
            case "STANDARD":
                return new StandardParcel(weight, value);
            case "EXPRESS":
                return new ExpressParcel(weight, value);
            default:
                return new FragileParcel(weight, value);
        }
    }

    static void parcelDesk(Scanner in) {
        int n = Integer.parseInt(in.nextLine().trim());
        double grandTotal = 0;
        for (int i = 0; i < n; i++) {
            String[] parts = in.nextLine().trim().split("\\s+");
            Parcel parcel = createParcel(parts);
            double insurance = parcel instanceof Insurable ? ((Insurable) parcel).insurance() : 0;
            double total = parcel.charge() + insurance;
            System.out.printf("%s: Charge=%.2f Insurance=%.2f Total=%.2f%n", parts[0], parcel.charge(), insurance, total);
            grandTotal += total;
        }
        System.out.printf("Grand Total: %.2f%n", grandTotal);
    }

    interface UsesBus {
        double TRANSPORT_FEE = 12000;
    }

    static abstract class Student {
        String name;

        Student(String name) {
            this.name = name;
        }

        abstract double tuition();

        double fee() {
            return tuition() + (this instanceof UsesBus ? UsesBus.TRANSPORT_FEE : 0);
        }
    }

    static class DayScholar extends Student implements UsesBus {
        DayScholar(String name) {
            super(name);
        }

        double tuition() {
            return 40000;
        }
    }

    static class Hosteller extends Student {
        Hosteller(String name) {
            super(name);
        }

        double tuition() {
            return 40000 + 60000;
        }
    }

    static class Scholar extends Student implements UsesBus {
        Scholar(String name) {
            super(name);
        }

        double tuition() {
            return 40000 / 2;
        }
    }

    static Student createStudent(String type, String name) {
        switch (type) {
            case "DAY_SCHOLAR":
                return new DayScholar(name);
            case "HOSTELLER":
                return new Hosteller(name);
            default:
                return new Scholar(name);
        }
    }

    static void collegeFees(Scanner in) {
        int n = Integer.parseInt(in.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            String[] parts = in.nextLine().trim().split("\\s+");
            Student student = createStudent(parts[0], parts[1]);
            System.out.printf("%s: %.2f%n", student.name, student.fee());
            total += student.fee();
        }
        System.out.printf("Total Collected: %.2f%n", total);
    }

    interface NightService {
        double NIGHT_SURCHARGE = 0.20;
    }

    static abstract class Cab {
        static final double MINIMUM_FARE = 100;
        double km;

        Cab(double km) {
            this.km = km;
        }

        abstract double ratePerKm();

        double fare() {
            return Math.max(MINIMUM_FARE, km * ratePerKm());
        }
    }

    static class Mini extends Cab {
        Mini(double km) {
            super(km);
        }

        double ratePerKm() {
            return 10;
        }
    }

    static class Sedan extends Cab implements NightService {
        Sedan(double km) {
            super(km);
        }

        double ratePerKm() {
            return 14;
        }
    }

    static class Suv extends Cab implements NightService {
        Suv(double km) {
            super(km);
        }

        double ratePerKm() {
            return 18;
        }
    }

    static Cab createCab(String type, double km) {
        switch (type) {
            case "MINI":
                return new Mini(km);
            case "SEDAN":
                return new Sedan(km);
            default:
                return new Suv(km);
        }
    }

    static void cabFares(Scanner in) {
        int n = Integer.parseInt(in.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            String[] parts = in.nextLine().trim().split("\\s+");
            Cab cab = createCab(parts[0], Double.parseDouble(parts[1]));
            boolean night = parts[2].equals("NIGHT");
            if (night && !(cab instanceof NightService)) {
                System.out.println(parts[0] + ": night service not available");
                continue;
            }
            double fare = night ? cab.fare() * (1 + NightService.NIGHT_SURCHARGE) : cab.fare();
            System.out.printf("%s: %.2f%n", parts[0], fare);
            total += fare;
        }
        System.out.printf("Total: %.2f%n", total);
    }

    interface Saver {
        double SAVER_FACTOR = 0.75;
    }

    static abstract class Appliance {
        double hours;

        Appliance(double hours) {
            this.hours = hours;
        }

        abstract double powerWatts();

        double units() {
            return powerWatts() * hours / 1000;
        }
    }

    static class Fridge extends Appliance {
        Fridge(double hours) {
            super(hours);
        }

        double powerWatts() {
            return 150;
        }
    }

    static class Ac extends Appliance implements Saver {
        Ac(double hours) {
            super(hours);
        }

        double powerWatts() {
            return 1500;
        }
    }

    static class Tv extends Appliance {
        Tv(double hours) {
            super(hours);
        }

        double powerWatts() {
            return 100;
        }
    }

    static class Washer extends Appliance implements Saver {
        Washer(double hours) {
            super(hours);
        }

        double powerWatts() {
            return 500;
        }
    }

    static Appliance createAppliance(String type, double hours) {
        switch (type) {
            case "FRIDGE":
                return new Fridge(hours);
            case "AC":
                return new Ac(hours);
            case "TV":
                return new Tv(hours);
            default:
                return new Washer(hours);
        }
    }

    static void energyReport(Scanner in) {
        int n = Integer.parseInt(in.nextLine().trim());
        double total = 0;
        for (int i = 0; i < n; i++) {
            String[] parts = in.nextLine().trim().split("\\s+");
            Appliance appliance = createAppliance(parts[0], Double.parseDouble(parts[1]));
            boolean saver = parts.length == 3;
            if (saver && !(appliance instanceof Saver)) {
                System.out.println(parts[0] + ": saver mode not supported");
                continue;
            }
            double units = saver ? appliance.units() * Saver.SAVER_FACTOR : appliance.units();
            System.out.printf("%s: Units=%.2f Cost=%.2f%n", parts[0], units, units * 8);
            total += units * 8;
        }
        System.out.printf("Total Cost: %.2f%n", total);
    }

    public static void main(String[] args) {
        System.out.println("1. Movie Ticket Counter");
        movieTickets(new Scanner("3\nREGULAR 3\nPREMIUM 2\nRECLINER 1\n"));

        System.out.println("\n2. Parcel Shipping Desk");
        parcelDesk(new Scanner("3\nSTANDARD 3 500\nEXPRESS 2 1000\nFRAGILE 4 2000\n"));

        System.out.println("\n3. College Fee Counter");
        collegeFees(new Scanner("3\nDAY_SCHOLAR Asha\nHOSTELLER Ravi\nSCHOLAR Neha\n"));

        System.out.println("\n4. City Cab Fare Meter");
        cabFares(new Scanner("4\nMINI 8 DAY\nSEDAN 10 NIGHT\nSUV 20 DAY\nMINI 5 NIGHT\n"));

        System.out.println("\n5. Home Appliance Energy Report");
        energyReport(new Scanner("4\nFRIDGE 24\nAC 8 SAVER\nTV 5\nWASHER 2 SAVER\n"));
    }
}
