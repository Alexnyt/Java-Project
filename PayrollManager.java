import java.io.*;
import java.util.*;

class StaffMember {

    private int id;
    private String fullName;
    private double basePay;

    public StaffMember(int id, String fullName, double basePay) {
        this.id = id;
        this.fullName = fullName;
        this.basePay = basePay;
    }

    public int getId() {
        return id;
    }

    public double computeHRA() {
        return basePay * 0.25;   // 25% HRA (different from friend's 20%)
    }

    public double computeDA() {
        return basePay * 0.12;   // 12% DA (different from friend's 10%)
    }

    public double computeNetSalary() {
        return basePay + computeHRA() + computeDA();
    }

    public String formatForStorage() {
        return id + "|" + fullName + "|" + basePay;
    }

    public static StaffMember parseFromLine(String line) {
        String[] parts = line.split("\\|");
        return new StaffMember(
                Integer.parseInt(parts[0]),
                parts[1],
                Double.parseDouble(parts[2])
        );
    }

    public void printDetails() {
        System.out.println("\nID: " + id);
        System.out.println("Name: " + fullName);
        System.out.println("Base Salary: " + basePay);
        System.out.println("HRA (25%): " + computeHRA());
        System.out.println("DA (12%): " + computeDA());
        System.out.println("Final Salary: " + computeNetSalary());
        System.out.println("===============================");
    }
}

public class PayrollManager {

    private static final String DATA_FILE = "staff_data.txt";
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int option;

        do {
            System.out.println("\n--- Payroll Manager ---");
            System.out.println("1. Register Staff");
            System.out.println("2. View All Staff");
            System.out.println("3. Find Staff by ID");
            System.out.println("4. Quit");
            System.out.print("Choose option: ");

            while (!input.hasNextInt()) {
                System.out.println("Invalid choice. Enter number.");
                input.next();
            }

            option = input.nextInt();
            input.nextLine();

            switch (option) {
                case 1:
                    registerStaff();
                    break;
                case 2:
                    showAllStaff();
                    break;
                case 3:
                    findStaff();
                    break;
                case 4:
                    System.out.println("Program terminated.");
                    break;
                default:
                    System.out.println("Invalid selection.");
            }

        } while (option != 4);
    }

    private static void registerStaff() {
        try {
            System.out.print("Enter ID: ");
            int id = input.nextInt();
            input.nextLine();

            System.out.print("Enter Name: ");
            String name = input.nextLine();

            System.out.print("Enter Base Salary: ");
            double salary = input.nextDouble();
            input.nextLine();

            StaffMember staff = new StaffMember(id, name, salary);

            FileWriter writer = new FileWriter(DATA_FILE, true);
            writer.write(staff.formatForStorage() + "\n");
            writer.close();

            System.out.println("Staff record saved.");

        } catch (Exception e) {
            System.out.println("Error occurred while saving data.");
            input.nextLine();
        }
    }

    private static void showAllStaff() {
        File file = new File(DATA_FILE);

        if (!file.exists()) {
            System.out.println("No staff records available.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = reader.readLine()) != null) {
                StaffMember staff = StaffMember.parseFromLine(line);
                staff.printDetails();
            }

        } catch (IOException e) {
            System.out.println("Unable to read records.");
        }
    }

    private static void findStaff() {
        System.out.print("Enter ID to search: ");
        int searchId = input.nextInt();
        input.nextLine();

        File file = new File(DATA_FILE);

        if (!file.exists()) {
            System.out.println("No records found.");
            return;
        }

        boolean exists = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = reader.readLine()) != null) {
                StaffMember staff = StaffMember.parseFromLine(line);
                if (staff.getId() == searchId) {
                    staff.printDetails();
                    exists = true;
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Error accessing file.");
        }

        if (!exists) {
            System.out.println("Staff member not found.");
        }
    }
}
    public int getId() {
        return id;
    }
}
