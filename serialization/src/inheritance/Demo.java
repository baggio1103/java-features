package inheritance;

import java.io.*;

public class Demo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        var man = new Man("baggio", Gender.MALE);
        var outputStream = new ObjectOutputStream(new FileOutputStream("baggio.txt"));
        outputStream.writeObject(man);
        System.out.println(man);

        var inputStream = new ObjectInputStream(new FileInputStream("baggio.txt"));
        var deserializedMan = (Man) inputStream.readObject();
        System.out.println(deserializedMan);
    }

}
