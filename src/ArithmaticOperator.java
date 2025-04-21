class arithMaticOperators{
    public static void main(String[] args) {
        //arithmatic operators


        //addition  (+)
        int mySal = 21200;
        int myBonus = 1200;
        int total = mySal + myBonus;
        System.out.println("addition of salary and bonus are : " + total);


        // substraction (-)
        int empSal = 25000;
        int empPf = 3600;
        int empTax = 200;
        int totalOutcome = empSal - empPf - empTax;
        System.out.println("Employee on hand Salary : " + totalOutcome);

        //Multiplication (*)
        int officeSal = 21200;
        int WFHSal = 20;
        int totalIncome = officeSal * WFHSal;
        System.out.println("My OnHand Salary is : " + totalIncome);

        //Devision (/)
        int ConsumeSal = totalOutcome /3;
        System.out.println("Consumed salary : " + ConsumeSal);

        //Modulas=Remainder (%)
        int AllOverSal = ConsumeSal % totalOutcome;
        System.out.println("All Over Pending Salary : " + AllOverSal);

        //DeepDive Into Addition

        double a = 25.6;
        int b = 25;
        double c = a + b; // directly it will convert to double because data are to be loss
        System.out.println(c); // o/p 50.6

        //lets do with float and long

        float d = 10.50f;
        long e =  21215451121l;
        float fq = d + e; // It will convert to float because flaot has store maximum value
                          // float has scientific notations thats why
        System.out.println(fq);
        
        //Deepdive into division
        float ab = 106.6f;
        int ba = 10;
        float v = ab / ba;
        System.out.println(v); //Op : 10.66
        //you seen that precison are losed because we are uis

    }
}