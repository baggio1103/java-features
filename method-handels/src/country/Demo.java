package country;

import java.util.Arrays;
import java.util.List;

import static country.CountryMethodHandleApi.*;

public class Demo {

    public static void main(String... args) {
        var wakanda = new Country();
        invokeSetCountry(wakanda, "Wakanda");
        System.out.println(wakanda);

        var rohan = new Country();
        invokeFieldSetter(rohan, "Rohan");
        System.out.println(rohan);

        var noArg = invokeNoArgConstructor(Country.class);
        System.out.println(noArg);

        var argConstructor = invokeArgConstructor(Country.class, List.of("Wakanda", 10000), String.class, int.class);
        System.out.println(argConstructor);

        var rivendel = new Country();
        invokeFieldSetter(rivendel, "Rivendel");
        invokePrivateField(rivendel, 123456789);
        System.out.println(rivendel);

        // getter Method handle using findGetter method - it finds the necessary getterMethod by its fieldName
        System.out.println("Result of invokeGetterCountry:  " + invokeGetterCountry(rivendel, "name"));

        // getter Method handle using findVirtual method - it finds the necessary method by its name
        System.out.println(invokeGetterCountry(Country.class, rivendel, String.class, "getName"));


        // Invoking a static method
        var result = invokeStaticMethod(Country.class, "getDetails", String[].class);
        System.out.println(Arrays.toString(result));

    }


}
