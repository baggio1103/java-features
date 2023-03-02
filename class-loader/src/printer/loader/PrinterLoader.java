package printer.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class PrinterLoader extends ClassLoader {

        /**
         *  E.g. /bin/printer/functionality/IPrint.class ->
         *  printer/functionality/IPrint.class = binaryClassName
        */
        private final String pathToBin;

        // Inner cache to check if class has been loaded before
        private final Map<String, Class<?>> loadedClassMap;

        public PrinterLoader(String pathToBin, ClassLoader parent) {
            super(parent);
            this.pathToBin = pathToBin;
            loadedClassMap = new HashMap<>();
        }

        @Override
        public Class<?> findClass(String className) throws ClassNotFoundException {
            var loadClassName = Arrays.stream(pathToBin.split("/")).skip(1)
                    .reduce("", (a, b) -> a + "." + b).substring(1) + "." + className.split(".class")[0];
            var stringBuffer = new StringBuffer(loadClassName);
            var result = loadedClassMap.entrySet().stream()
                    .filter(entry -> Arrays.asList(entry.getKey().split(", ")).contains(loadClassName))
                    .findAny().map(Map.Entry::getValue);
            if (result.isPresent()) {
                return result.get();
            }
            try {
                byte[] bytes = fetchClassFromFS(pathToBin + "/" + className);
                var loadedClass = defineClass(loadClassName, bytes, 0, bytes.length);
                Arrays.stream(loadedClass.getGenericInterfaces()).forEach(interfaceName -> {
                    stringBuffer.append(", ").append(interfaceName.toString().replace("interface ", ""));
                });
                loadedClassMap.put(stringBuffer.toString(), loadedClass);
                return loadedClass;
            } catch (IOException ex) {
                return super.findClass(className);
            }
        }

        private byte[] fetchClassFromFS(String path) throws IOException {
            InputStream inputStream = new FileInputStream(path);
            // Get the size of the file
            long length = new File(path).length();

            // Create the byte array to hold the data
            byte[] bytes = new byte[(int)length];

            // Read in the bytes
            int offset = 0;
            int numRead;
            while (offset < bytes.length
                    && (numRead = inputStream.read(bytes, offset, bytes.length - offset)) >= 0
            ) {
                offset += numRead;
            }
            // Ensure all the bytes have been read in
            if (offset < bytes.length) {
                throw new IOException("Could not completely read file " + path);
            }
            inputStream.close();
            return bytes;
        }

    }
