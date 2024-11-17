import java.io.*;
import java.util.*;
class Student {
    private String name;
    private String rollNumber;
    private String grade;
    public Student(String name, String rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRollNumber() {
        return rollNumber;
    }
    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }
    @Override
    public String toString() {
        return "Name: " + name + ", Roll Number: " + rollNumber + ", Grade: " + grade;
    }
}
class StudentManagementSystem {
    private List<Student> students = new ArrayList<>();
    public void addStudent(String name, String rollNumber, String grade) {
        students.add(new Student(name, rollNumber, grade));
    }
    public boolean removeStudent(String rollNumber) {
        return students.removeIf(student -> student.getRollNumber().equals(rollNumber));
    }
    public Student searchStudent(String rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber().equals(rollNumber)) {
                return student;
            }
        }
        return null;
    }
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }
    public void saveStudentsToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("students.dat"))) {
            out.writeObject(students);
            System.out.println("Students saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving students to file.");
        }
    }
    public void loadStudentsFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("students.dat"))) {
            students = (List<Student>) in.readObject();
            System.out.println("Students loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading students from file.");
        }
    }
}
public class StudentManagementApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentManagementSystem sms = new StudentManagementSystem();
    public static void main(String[] args) {
        sms.loadStudentsFromFile();
        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add a new student");
            System.out.println("2. Remove a student");
            System.out.println("3. Search for a student");
            System.out.println("4. Display all students");
            System.out.println("5. Save and exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addNewStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    sms.displayAllStudents();
                    break;
                case 5:
                    sms.saveStudentsToFile();
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void addNewStudent() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter roll number: ");
        String rollNumber = scanner.nextLine();
        System.out.print("Enter grade: ");
        String grade = scanner.nextLine();
        if (name.isEmpty() || rollNumber.isEmpty() || grade.isEmpty()) {
            System.out.println("All fields must be filled.");
        } else {
            sms.addStudent(name, rollNumber, grade);
            System.out.println("Student added successfully.");
        }
    }
    private static void removeStudent() {
        System.out.print("Enter roll number of the student to remove: ");
        String rollNumber = scanner.nextLine();
        if (sms.removeStudent(rollNumber)) {
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student not found.");
        }
    }
    private static void searchStudent() {
        System.out.print("Enter roll number to search: ");
        String rollNumber = scanner.nextLine();
        Student student = sms.searchStudent(rollNumber);
        if (student != null) {
            System.out.println("Student found: " + student);
        } else {
            System.out.println("Student not found.");
        }
    }
}