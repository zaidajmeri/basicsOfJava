package Scanner_In_Java;

import java.util.Scanner;

public class MADLIBS_Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String adjective1;
        String noun1;
        String adjective2;
        String verb1;
        String adjective3;

        System.out.print("Enter an adjective (Description) : ");
        adjective1 = sc.nextLine();
        System.out.print("Enter a noun (animal or person) : ");
        noun1 = sc.nextLine();
        System.out.print("Enter an adjective (Description) : ");
        adjective2 = sc.nextLine();
        System.out.println("Enter a verb end with - ing (action) : ");
        verb1 = sc.nextLine();
        System.out.print("Enter an adjective (Description) : ");
        adjective3 = sc.nextLine();



        System.out.println("\nToday i went to a " + adjective1 + " zoo.");
        System.out.println("In a exhibit, I saw a " + noun1 + ".");
        System.out.println(noun1 + " was " + adjective2 + " and " + verb1 + "!");
        System.out.println("I was " + adjective3 + "!");

        sc.close();
    }
}
