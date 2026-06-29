public class Student {
    private String name;
    private double grade;

    // Constructor
    public Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }

    // Display student info neatly
    @Override
    public String toString() {
        return String.format("Student: %-20s | Grade: %.2f", name, grade);
    }
}
