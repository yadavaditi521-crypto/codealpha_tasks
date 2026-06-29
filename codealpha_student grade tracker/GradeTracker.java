import java.util.ArrayList;
import java.util.Scanner;

public class GradeTracker {
    // Using ArrayList to dynamically store and manage student objects
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("   Welcome to the Student Grade Tracker   ");
        System.out.println("=========================================");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = getIntegerInput("Enter your choice (1-4): ");

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displaySummaryReport();
                    break;
                case 3:
                    displayAnalytics();
                    break;
                case 4:
                    System.out.println("\nExiting program. Thank you!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 4.");
            }
        }
        scanner.close();
    }

    // Displays the main user interface options
    private static void printMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Add Student and Grade");
        System.out.println("2. Display Summary Report of All Students");
        System.out.println("3. View Analytics (Average, Highest, Lowest)");
        System.out.println("4. Exit");
    }

    // Feature 1: Input and manage student data
    private static void addStudent() {
        System.out.print("\nEnter student name: ");
        String name = scanner.nextLine().trim();
        
        while (name.isEmpty()) {
            System.out.print("Name cannot be empty. Enter student name: ");
            name = scanner.nextLine().trim();
        }

        double grade = -1;
        while (grade < 0 || grade > 100) {
            System.out.print("Enter student grade (0 - 100): ");
            if (scanner.hasNextDouble()) {
                grade = scanner.nextDouble();
                if (grade < 0 || grade > 100) {
                    System.out.println("Invalid grade! Grade must be between 0 and 100.");
                }
            } else {
                System.out.println("Invalid input! Please enter a numerical grade.");
                scanner.next(); // Clear invalid token
            }
        }
        scanner.nextLine(); // Consume remaining newline

        studentList.add(new Student(name, grade));
        System.out.println("Successfully added " + name + " with a grade of " + grade);
    }

    // Feature 2: Display summary report
    private static void displaySummaryReport() {
        System.out.println("\n--- STUDENT SUMMARY REPORT ---");
        if (studentList.isEmpty()) {
            System.out.println("No student records found. Try adding some students first!");
            return;
        }

        for (Student student : studentList) {
            System.out.println(student);
        }
        System.out.println("Total Students: " + studentList.size());
    }

    // Feature 3: Calculate and show average, highest, and lowest scores
    private static void displayAnalytics() {
        System.out.println("\n--- GRADE PERFORMANCE ANALYTICS ---");
        if (studentList.isEmpty()) {
            System.out.println("No data available to calculate analytics.");
            return;
        }

        double totalSum = 0;
        double highest = studentList.get(0).getGrade();
        double lowest = studentList.get(0).getGrade();
        
        Student highestStudent = studentList.get(0);
        Student lowestStudent = studentList.get(0);

        for (Student s : studentList) {
            double currentGrade = s.getGrade();
            totalSum += currentGrade;

            if (currentGrade > highest) {
                highest = currentGrade;
                highestStudent = s;
            }
            if (currentGrade < lowest) {
                lowest = currentGrade;
                lowestStudent = s;
            }
        }

        double average = totalSum / studentList.size();

        // Output results formatted cleanly
        System.out.printf("Class Average : %.2f\n", average);
        System.out.printf("Highest Score : %.2f (%s)\n", highest, highestStudent.getName());
        System.out.printf("Lowest Score  : %.2f (%s)\n", lowest, lowestStudent.getName());
    }

    // Helper method to bulletproof integer inputs from users
    private static int getIntegerInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid option! Please enter a number.");
            System.out.print(prompt);
            scanner.next();
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // Clear scanner buffer
        return value;
    }
}
