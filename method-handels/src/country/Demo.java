package country;

import java.lang.invoke.MethodHandles;

import static java.lang.invoke.MethodHandles.Lookup;

public class Demo {

    private static final Lookup lookup = MethodHandles.lookup();

    private static Lookup privateLookup;

    static {
        try {
            privateLookup = MethodHandles.privateLookupIn(Country.class, lookup);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }

}
