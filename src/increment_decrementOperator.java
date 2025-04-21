public class increment_decrementOperator {
    public static void main(String[] args){
       // Increment Decrement Operators
        //++ Increment
        //-- Decrement
        // there are two types postfix and prefix
        /// postfix is a++ and prefix is ++a
        // In PostFix value first Used after then Value Incre/Decrement
        // In Prefix firstly value Incre/Decrement then after it will use

        //Postfix Increment
        int a = 1;
        int b = a++ + a;
        System.out.println(b); //op - 3

        //Postfix Decrement
        int dec = 1;
        int dec1 = dec + dec--;
        System.out.println(dec1);

        //Prefix Increment
        int preInc = 1;
        int preInc1 =  preInc + ++preInc;
        System.out.println(preInc1); //op 3

        //Prefix Decrement
        int preDec = 1;
        System.out.println(--preDec );

    }
}
