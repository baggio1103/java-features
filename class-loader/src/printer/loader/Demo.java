package printer.loader;

import java.io.File;
import java.io.Serializable;

import static java.util.Objects.requireNonNull;

public class Demo implements Serializable {

    public static void main(String[] args) {
        String modulePath = args[0];
        var loader = new PrinterLoader(modulePath, ClassLoader.getSystemClassLoader());
        File directory = new File(modulePath);
        System.out.println(modulePath);
        for (String className : requireNonNull(directory.list())) {
            try {
                Class<?> clazz = loader.loadClass(className);
                if (!clazz.isInterface()) {
                    var execute = clazz.getConstructor().newInstance();
                    var method = execute.getClass().getMethod("print");
                    method.invoke(execute);
                }
            } catch (Exception e) {
                System.out.println("Exception happened: " + e.getMessage());
            }
        }
    }

}
