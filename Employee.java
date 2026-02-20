public class Employee {

    private int id;
    private String name;
    private double basic;
    private double hra;
    private double da;
    private double tax;
    private double netSalary;

    public Employee(int id, String name, double basic) {
        this.id = id;
        this.name = name;
        this.basic = basic;
        computeSalary();
    }

    private void computeSalary() {
        hra = 0.25 * basic;   // 25%
        da = 0.15 * basic;    // 15%
        tax = 0.05 * basic;   // 5% deduction
        netSalary = basic + hra + da - tax;
    }

    public String formatForFile() {
        return id + "," + name + "," + basic + "," + netSalary;
    }

    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Basic Salary: " + basic);
        System.out.println("Net Salary: " + netSalary);
        System.out.println("----------------------------");
    }

    public int getId() {
        return id;
    }
}