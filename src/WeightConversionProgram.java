package Projects;

import java.util.Scanner;

public class WeightConversionProgram {
    //weight conversion program
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double weight;
        double newWeight;
        int choice;

        System.out.println("Weight Conversion Program");
        System.out.println("1: Convert lbs to kgs");
        System.out.println("2: Convert kgs to lbs");

        System.out.print("Choose an option: ");
        choice = sc.nextInt();


        if(choice == 1){
            System.out.print("Enter the weight in LBS: ");
            weight = sc.nextDouble();
            newWeight = weight * 0.453592;
            System.out.printf("The new weight in KGS is: %.2f " , newWeight);
        }
        else if(choice == 2){
            System.out.print("Enter the weight in KGS: ");
            weight = sc.nextDouble();
            newWeight = weight * 2.20462;
            System.out.printf("The new weight in LBS is: %.2f " , newWeight);
        }
        else {
            System.out.println("That was not a valid choice");
        }

        sc.close();
    }
    //declare variable



    //welcome message


    //prompt for user choice

    //option 1 convert lbs to kgs

    //option 2 convert kgs to lbs

    //else print not a valid choice





}
