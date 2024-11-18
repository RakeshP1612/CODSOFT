import java.util.Scanner;
public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of subjects: ");
        int numberOfSubjects = scanner.nextInt();
        int[] marks = new int[numberOfSubjects];
        int totalMarks = 0;
        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + " (out of 100): ");
            marks[i] = scanner.nextInt();
            if (marks[i] < 0 || marks[i] > 100) {
                System.out.println("Invalid marks! Please enter a value between 0 and 100.");
                i--;
                continue;
            }
            totalMarks += marks[i];
        }
        double averagePercentage = (double) totalMarks / numberOfSubjects;
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 75) {
            grade = 'B';
        } else if (averagePercentage >= 50) {
            grade = 'C';
        } else if (averagePercentage >= 35) {
            grade = 'D';
        } else {
            grade = 'F';
        }
        System.out.println("\n--- Results ---");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);
        scanner.close();
    }
}
