import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class StudentGradeTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the name of the exam and subject
        System.out.print("Enter the name of the exam: ");
        String examName = scanner.nextLine();

        System.out.print("Enter the name of the subject: ");
        String subjectName = scanner.nextLine();

        // Initialize ArrayList to store student names and grades
        ArrayList<String> studentNames = new ArrayList<>();
        ArrayList<Integer> grades = new ArrayList<>();

        // Get the number of students from the user (at least 5)
        int numStudents;
        do {
            System.out.print("Enter the number of students (at least 5): ");
            numStudents = scanner.nextInt();
        } while (numStudents < 5);
        scanner.nextLine(); // Consume the newline character

        // Input names and grades for each student
        for (int i = 1; i <= numStudents; i++) {
            System.out.print("Enter the name of student " + i + ": ");
            String studentName = scanner.nextLine();
            studentNames.add(studentName);

            System.out.print("Enter the grade for student " + studentName + ": ");
            int grade = scanner.nextInt();
            grades.add(grade);
            scanner.nextLine(); // Consume the newline character
        }

        // Calculate average, highest, and lowest grades
        double average = calculateAverage(grades);
        int highest = calculateHighest(grades);
        int lowest = calculateLowest(grades);

        // Display overall report
        System.out.println("\n+---------------------------------------------+");
        System.out.println("|                 Overall Report               |");
        System.out.println("+---------------------------------------------+");
        System.out.printf("| Exam: %-38s |\n", examName);
        System.out.printf("| Subject: %-35s |\n", subjectName);
        System.out.println("+---------------------------------------------+");
        System.out.printf("| Average grade: %-30.2f |\n", average);
        System.out.printf("| Highest grade: %-30d |\n", highest);
        System.out.printf("| Lowest grade: %-31d |\n", lowest);
        System.out.println("+---------------------------------------------+");

        // Display top students (up to 10 or top 3)
        System.out.println("\n+---------------------------------------------+");
        System.out.println("|             Top Performing Students          |");
        System.out.println("+---------------------------------------------+");

        // Sort students based on grades
        ArrayList<Student> studentList = new ArrayList<>();
        for (int i = 0; i < numStudents; i++) {
            studentList.add(new Student(studentNames.get(i), grades.get(i)));
        }
        Collections.sort(studentList, Comparator.reverseOrder());

        int topStudentsCount = Math.min(numStudents, 10); // Show top 10 or all if less than 10
        for (int i = 0; i < topStudentsCount; i++) {
            System.out.printf("| %-20s: %-27d |\n", studentList.get(i).getName(), studentList.get(i).getGrade());
        }
        System.out.println("+---------------------------------------------+");
    }

    // Method to calculate average grade
    private static double calculateAverage(ArrayList<Integer> grades) {
        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.size();
    }

    // Method to calculate highest grade
    private static int calculateHighest(ArrayList<Integer> grades) {
        return Collections.max(grades);
    }

    // Method to calculate lowest grade
    private static int calculateLowest(ArrayList<Integer> grades) {
        return Collections.min(grades);
    }

    // Class to represent a Student with name and grade
    private static class Student implements Comparable<Student> {
        private String name;
        private int grade;

        public Student(String name, int grade) {
            this.name = name;
            this.grade = grade;
        }

        public String getName() {
            return name;
        }

        public int getGrade() {
            return grade;
        }

        @Override
        public int compareTo(Student other) {
            return Integer.compare(this.grade, other.grade);
        }
    }
}
