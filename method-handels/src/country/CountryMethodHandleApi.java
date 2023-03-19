package country;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.List;

import static java.lang.invoke.MethodHandles.Lookup;

public class CountryMethodHandleApi {

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

    // Method handle for setter
    public static void invokeSetCountry(Country country, String word) {
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
    public static <R, T> R invokeGetterCountry(Class<T> tClass, T object, Class<R> rClass, String methodName) {
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
    public static String invokeGetterCountry(Country country, String fieldName) {
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
    public static void invokeFieldSetter(Country country, String name) {
        String fieldName = "name";
        try {
            MethodHandle nameFieldHandler = lookup.findSetter(Country.class, fieldName, String.class);
            nameFieldHandler.invoke(country, name);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    // Method Handle for private fields
    public static <T, V> void invokePrivateField(T t, V value) {
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
    public static <T> T invokeNoArgConstructor(Class<T> clazz) {
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
    public static <T> T invokeArgConstructor(Class<T> tClass, List<?> arguments, Class<?>... pTypes) {
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
    public static <T, R> R invokeStaticMethod(Class<T> tClass, String methodName, Class<R> rClass) {
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
