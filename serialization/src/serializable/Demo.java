package serializable;

import java.io.*;

public class Demo {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        var employee = new Employee("Baggio", 22);
        var university = new University("Harvard");
        employee.setEmail("baggio");
        employee.setUniversity(university);
        var outputStream = new ObjectOutputStream(new FileOutputStream("employee.txt"));
        outputStream.writeObject(employee);

        var inputStream = new ObjectInputStream(new FileInputStream("employee.txt"));
        var desEmployee = (Employee) inputStream.readObject();
        System.out.println(desEmployee);
    }

}
