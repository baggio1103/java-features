package country;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.Arrays;
import java.util.List;

import static java.lang.invoke.MethodHandles.Lookup;

public class Demo {

    private static final Lookup lookup = MethodHandles.lookup();

    private static Lookup privateLookup;

    private static final MethodType setter = MethodType.methodType(void.class, String.class);

    private static final MethodType getter =  MethodType.methodType(String.class);

    private static final MethodType constructor = MethodType.methodType(String.class, int.class);

    static {
        try {
            privateLookup = MethodHandles.privateLookupIn(Country.class, lookup);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

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

    // Method handle for setter
    private static void invokeSetCountry(Country country, String word) {
        MethodType methodType = MethodType.methodType(void.class, String.class);
        try {
            var methodHandle = lookup.findVirtual(Country.class, "setName", methodType);
            methodHandle.invoke(country, word);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }


    // Method handler method getter using method - findVirtual() that uses a methodType
    // to find any method defined in side a given class - tClass
    @SuppressWarnings("unchecked")
    private static <R, T> R invokeGetterCountry(Class<T> tClass, T object, Class<R> rClass, String methodName) {
        try {
            var methodType = MethodType.methodType(rClass);
            var virtualMethodHandle = lookup.findVirtual(tClass, methodName, methodType);
            var result = virtualMethodHandle.invoke(object);
            return (R) result;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method handle for getter using findGetter() - it find a fields by a given name
    private static String invokeGetterCountry(Country country, String fieldName) {
        try {
            var methodHandle = lookup.findGetter(Country.class, fieldName, String.class);
            return (String) methodHandle.invoke(country);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method handle for field setter. findSetter() creates a methodHandler that
    // gives a write access to a non static field. Since it is a field, not a method,
    // it does not require a MethodType
    private static void invokeFieldSetter(Country country, String name) {
        String fieldName = "name";
        try {
            MethodHandle nameFieldHandler = lookup.findSetter(Country.class, fieldName, String.class);
            nameFieldHandler.invoke(country, name);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    // Method Handle for private fields
    private static <T, V> void invokePrivateField(T t, V value) {
        var privateFieldName = "population";
        try {
            var privateFieldHandler = privateLookup.findSetter(t.getClass(), privateFieldName, int.class);
            privateFieldHandler.invoke(t, value);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // Method handle for no arg Constructor
    private static <T> T invokeNoArgConstructor(Class<T> clazz) {
        var noArgConstructor = MethodType.methodType(void.class);
        try {
            var methodHandle = lookup.findConstructor(clazz, noArgConstructor);
           return (T) methodHandle.invoke();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // Method handle for no arg Constructor
    private static <T> T invokeArgConstructor(Class<T> tClass, List<?> arguments, Class<?>... pTypes) {
        var argConstructor = MethodType.methodType(void.class, pTypes);
        try {
            var methodHandle = lookup.findConstructor(tClass, argConstructor);
            return (T) methodHandle.invokeWithArguments(arguments);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static <T, R> R invokeStaticMethod(Class<T> tClass, String methodName, Class<R> rClass) {
        try {
            var staticMethodType = MethodType.methodType(rClass);
            var staticMethodHandle = lookup.findStatic(tClass, methodName, staticMethodType);
           return (R) staticMethodHandle.invoke();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

}
