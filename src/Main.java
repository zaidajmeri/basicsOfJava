package Scanner_In_Java;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Your Name : ");
        String name = scanner.nextLine(); //nextLine() method reads String characters

        System.out.print("Enter Your Age : ");
        int age = scanner.nextInt(); //nextInt() method reads Int number

        System.out.print("Enter your CGPA : ");
        double cgpa = scanner.nextDouble(); //reads fraction number

        System.out.print("Are you a Student (true/false) ");
        boolean isStudent = scanner.nextBoolean();


        System.out.println("Hello " + name);
        System.out.println("You are " + age + " years old");
        System.out.println("Nice! your CGPA is " + cgpa);
        System.out.println("Student : " + isStudent );

        if (isStudent){
            System.out.println("You are enrolled as a student");
        }
        else {
            System.out.println("You are not enrolled");
        }

        scanner.close();

    }
}
