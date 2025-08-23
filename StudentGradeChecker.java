import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    double score;

    Student(String name, double score) {
        this.name = name;
        this.score = score;
    }
}

public class StudentGradeManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        // Input student details
        for (int i = 0; i < n; i++) {
            System.out.print("Enter name of student " + (i + 1) + ": ");
            String name = sc.nextLine();

            System.out.print("Enter score of " + name + ": ");
            double score = sc.nextDouble();
            sc.nextLine(); // consume newline

            students.add(new Student(name, score));
        }

        // Calculate average, highest, lowest
        double sum = 0, highest = Double.MIN_VALUE, lowest = Double.MAX_VALUE;
        String highestName = "", lowestName = "";

        for (Student s : students) {
            sum += s.score;
            if (s.score > highest) {
                highest = s.score;
                highestName = s.name;
            }
            if (s.score < lowest) {
                lowest = s.score;
                lowestName = s.name;
            }
        }

        double average = sum / students.size();

        // Display summary
        System.out.println("\n===== Student Grade Report =====");
        for (Student s : students) {
            System.out.printf("%-15s : %.2f%n", s.name, s.score);
        }
        System.out.println("------------------------------");
        System.out.printf("Average Score : %.2f%n", average);
        System.out.printf("Highest Score : %.2f (by %s)%n", highest, highestName);
        System.out.printf("Lowest Score  : %.2f (by %s)%n", lowest, lowestName);

        sc.close();
    }
}