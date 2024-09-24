import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Employee {
    private int id;
    private String name;
    private String position;
    private double salary;

    public Employee(int id, String name, String position, double salary) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("| %-5d | %-20s | %-15s | $%-9.2f |", id, name, position, salary);
    }
}

class EmployeeService {
    private List<Employee> employees = new ArrayList<>();
    private int nextId = 1;

    public void addEmployee(String name, String position, double salary) {
        for (Employee emp : employees) {
            if (emp.getName().equalsIgnoreCase(name)) {
                System.out.println("An employee with this name already exists.");
                return;
            }
        }
        Employee employee = new Employee(nextId++, name, position, salary);
        employees.add(employee);
        System.out.println("Added: " + employee);
    }

    public void updateEmployee(int id, String name, String position, double salary) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                emp.setName(name);
                emp.setPosition(position);
                emp.setSalary(salary);
                System.out.println("Updated: " + emp);
                return;
            }
        }
        System.out.println("No employee found with ID " + id + ".");
    }

    public void deleteEmployee(int id) {
        if (employees.isEmpty()) {
            System.out.println("No employees to delete.");
            return;
        }
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                employees.remove(emp);
                System.out.println("Deleted employee with ID " + id + ".");
                return;
            }
        }
        System.out.println("No employee found with ID " + id + ".");
    }

    public void listEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            printTableHeader();
            for (Employee emp : employees) {
                System.out.println(emp);
            }
            printTableFooter();
        }
    }

    private void printTableHeader() {
        System.out.println("\n+------+--------------------+-----------------+------------+");
        System.out.println("|  ID  |        Name        |     Position    |   Salary   |");
        System.out.println("+------+--------------------+-----------------+------------+");
    }

    private void printTableFooter() {
        System.out.println("+------+--------------------+-----------------+------------+");
    }
}

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        EmployeeService service = new EmployeeService();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===============================");
            System.out.println("   Employee Management System  ");
            System.out.println("===============================");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. List Employees");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter position: ");
                    String position = scanner.nextLine();
                    System.out.print("Enter salary: ");
                    double salary = scanner.nextDouble();
                    service.addEmployee(name, position, salary);
                    break;

                case 2:
                    System.out.print("Enter employee ID to update: ");
                    int idToUpdate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new position: ");
                    String newPosition = scanner.nextLine();
                    System.out.print("Enter new salary: ");
                    double newSalary = scanner.nextDouble();
                    service.updateEmployee(idToUpdate, newName, newPosition, newSalary);
                    break;

                case 3:
                    System.out.print("Enter employee ID to delete: ");
                    int idToDelete = scanner.nextInt();
                    service.deleteEmployee(idToDelete);
                    break;

                case 4:
                    service.listEmployees();
                    break;

                case 5:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Please select a valid option.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
