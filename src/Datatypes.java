import java.sql.SQLOutput;

class Datatypes{
    public static void main(String[] args){
        //Primitive Datatypes
        //byte, short, int, long

        //byte
        byte age = 127; //Byte only store -127 to 127 value
        //System.out.println(age);
        System.out.println("\n Byte Value Range");
        System.out.println(Byte.MIN_VALUE);
        System.out.println((Byte.MAX_VALUE));

        //short
        short age1 = 32767; //short only store value between -32768 to 32767 ranges
        System.out.println(age1);
        System.out.println("\nShort Value Range");
        System.out.println(Short.MIN_VALUE);
        System.out.println(Short.MAX_VALUE);

        //Integer(int)
        int age2 = 32768; // Int can store value between -2147483648 to 2147483647 ranges
        System.out.println(age2);
        System.out.println("\nInteger Value Range");
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);

        //long
        long age3= 2147483648l; //Long can store value more that this prev datatype ranges -9223372036854775808 to 9223372036854775807
        //it is compulsory to put "l" after number
        System.out.println("\n" + age3);
        System.out.println("long value range");
        System.out.println(Long.MIN_VALUE);
        System.out.println(Long.MAX_VALUE);

        //Decimal
        //Float
        float sal = 25000.154545456464f; //without f in float this will give error, it is compulsory to pu "f"
        System.out.println(sal);

        float floatmin = Float.MIN_VALUE;
        float floatmax = Float.MAX_VALUE;
        System.out.println("\n Float Value are ");
        System.out.println(floatmin);
        System.out.println(floatmax);

        //double
        double mysal = 2100.545645784646645646464; //Double ranges are greater then float
        System.out.println("\n Double Value are ");
        System.out.println(mysal);

        double min = Double.MIN_VALUE;
        double max = Double.MAX_VALUE;
        System.out.println("Minimum Value are :" + min + " Maximum Value are :" + max);



    }
}